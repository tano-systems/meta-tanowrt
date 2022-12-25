#!/usr/bin/env python3
###############################################################################
# Name:        package_signing.py
#
# Copyright (c) 2019 Sierra Wireless Inc.
#
# Purpose:     Replicates the signing performed in AndroidSigning.jar
#
# Usage example:
# python3 package_signing.py -f py_signing.json -k verity -u boot.img -s boot.img.signed
#
#                edit py_signing.json for configuration
#
###############################################################################


import subprocess
import argparse
import hashlib
import json
import os
from shutil import copyfile

try:
    from colorama import init as colorama_init
    from termcolor import colored
except ImportError:
    def colorama_init():
        return

    def colored(msg, color=False, attrs=[]):
        return msg

class Debug:

    mode = False

    def __init__(self,config):
        self.__config=config

    def set(self, m):
        self.mode = m

    def test(self):
        return self.mode

class DebugPrint():

    __dm = Debug(False)

    def __init__(self, config):
        self.__dm=config

    def output(self, out):
        if self.__dm.test() == "True":
            print (out)

class Key:

    def __init__(self, config, tmpoutput = "."):
        self.__config = config
        self.tmpoutput = tmpoutput

    def config(self, k):
        return self.__config[k]

    def key_type(self):
        return self.config("key_type")

    def __sign_image_openssl(self, target_file, signature_file, arg_key_name):

        # The signature is an PrK encryption of the SHA256 digest that results from the header file. OpenSSL can
        # perform both operations (ie calculating digest then encrypting) in a single command.
        #keyconvert_command = "openssl pkcs8 -in {}.pk8 -inform DER -nocrypt -out TEMP.PEM > asn1parse.log".format(
        #        arg_key_name
        #)
        #subprocess.run(keyconvert_command, shell=True, check=True)

        # Build OpenSSL command
        sign_command = "openssl dgst -sha256 -out {} -sign {}.pk8 -keyform DER {}".format(
            signature_file, arg_key_name, target_file
        )
        subprocess.run(sign_command, shell=True, check=True)

    # sign_image:
    #     Generates a signature file from the header file passed as argument. Local and HSM (Azure) signing are supported
    #     and addressed depending on the key set specified for signing.
    def sign_image(self, target_file, signature_file, arg_key_name):
        self.__sign_image_openssl(target_file, signature_file, arg_key_name)

    def __asn1_parse_openssl(self, parsed_file, arg_key_name):

        asn_parse_command = "openssl asn1parse -in {}.x509.pem -out {} > {}/asn1parse.{}.log".format(
            arg_key_name, parsed_file, self.tmpoutput, os.path.basename(arg_key_name)
        )
        subprocess.run(asn_parse_command, shell=True, check=True)

    def parse_asn1_key(self, parsed_file, arg_key_name):
        self.__asn1_parse_openssl(parsed_file, arg_key_name)

    def __save_public_key_file(self, dst_path):
        src_path = self.config("public_key_fpath")
        copyfile(src_path, dst_path)

    def save_public_key(self, dst_path):
        return self.__save_public_key_file(dst_path)


def hash_header(header):
    # Calculates sha256 hash
    header_hash = hashlib.sha256()
    header_hash.update(header)
    return header_hash.digest()


# padding can be turned on and off in .json file
def pad_image_to_4k(src,dest,dp):
    l=os.path.getsize(src)
    with open(dest, "wb") as fpad:
        with open(src, "rb") as fsrc:
            sb=fsrc.read()
            fpad.write(sb)
            if ((l%4096)!=0):
                pad = 4096 - l%4096
                dp.output("Padding {} to 4k alignment with  {} bites".format(src, pad))
                for x in range(0, pad):
                    fpad.write(b"\x00")


def load_config(json_file_path):
    data = None
    with open(json_file_path) as json_file:
        try:
            data = json.load(json_file)
        except json.JSONDecodeError as error:
            print("Unable to read JSON file with error: {}".format(error))

    if "Image Signing Tool Config" in data:
        return data["Image Signing Tool Config"]

    return data


def process_file(image_type, json_file_path, arg_key_name, arg_unsigned_src, arg_signed_output, tmpoutput, dp):

    dp.output(colored("== Processing %s ..." % json_file_path, 'cyan', attrs=['bold']))

    config = load_config(json_file_path)

    IMAGE_DIRECTORY = config["folders"]["images"]
    OUTPUT_DIRECTORY = tmpoutput
    #OUTPUT_DIRECTORY = config["folders"]["output"]

    try:
        os.stat(OUTPUT_DIRECTORY)
    except OSError:
        os.mkdir(OUTPUT_DIRECTORY)

    key = Key(config["key"], tmpoutput)

    # copy the public key to the output dir
    if config["key"]["include_public_key_in_output_dir"] is True:
        dst_path = os.path.join(OUTPUT_DIRECTORY, config["key"]["rename_public_to"])
        key.save_public_key(dst_path)
        dp.output("  Copy the public key to {}".format(dst_path))

    # remove existing system header file
    signed_output_name = os.path.join(OUTPUT_DIRECTORY, arg_signed_output)
    Param1 = int(config["general"]["version_code"], 16)
    for p in config["images"]:
        dp.output(colored("Adding " + arg_unsigned_src + " image ...", 'cyan'))
        image_path = os.path.join(IMAGE_DIRECTORY, arg_unsigned_src)
        dp.output("  Image location: {}".format(image_path))

        # Copy image to output
        if p.get("copy_to_output") is True:
            dst_path = os.path.join(
                OUTPUT_DIRECTORY, os.path.basename(arg_unsigned_src)
            )
            copyfile(image_path, dst_path)
        image_to_be_signed = image_path

    # Calculate signature for binary image files, stored in
    dp.output("Signing image {} with {}.x509.pem key".format(image_path, os.path.basename(arg_key_name)))

    if p.get("padding") is True:
        # If /boot then 4k page size limit already in place
        if image_type=="/boot":
            key.sign_image(image_path, "%s/sig.bin" % tmpoutput, arg_key_name)
        else:
            pad_image_to_4k(image_path, "%s/padded.bin" % tmpoutput, dp);
            key.sign_image("%s/padded.bin" % tmpoutput, "%s/sig.bin" % tmpoutput, arg_key_name)
            image_to_be_signed = "%s/padded.bin" % tmpoutput
    else:
        key.sign_image(image_path, "%s/sig.bin" % tmpoutput, arg_key_name)

    with open("%s/sig.bin" % tmpoutput, "rb") as sig:
        signature = sig.read()

    # Calculate get asn1 encoding
    dp.output("Encoding key {}.pk8 for ASN1".format(arg_key_name))
    key.parse_asn1_key("%s/parsed.bin" % tmpoutput, arg_key_name)
    with open("%s/parsed.bin" % tmpoutput, "rb") as parse:
        parsed_key = parse.read();

    # Build final system header files (add magic and additional images such as DH0)

    with open(signed_output_name, "wb") as temp_signed_output:

        if image_to_be_signed:
            with open(image_to_be_signed, "rb") as fadd:
                originalimage = fadd.read()
                fadd.close()
                if p.get("include_in_final_package") is True:
                    temp_signed_output.write(originalimage)
                imagelength=os.path.getsize(image_to_be_signed)

        #MAGICNUMBER_JAVA_ENCODINGs fake structure encoding from orginal java source
        #/**
        #*    AndroidVerifiedBootSignature DEFINITIONS ::=
        #*    BEGIN
        #*        formatVersion ::= INTEGER
        #*        certificate ::= Certificate
        #*        algorithmIdentifier ::= SEQUENCE {
        #*            algorithm OBJECT IDENTIFIER,
        #*            parameters ANY DEFINED BY algorithm OPTIONAL
        #*        }
        #*        authenticatedAttributes ::= SEQUENCE {
        #*            target CHARACTER STRING,
        #*            length INTEGER
        #*        }
        #*        signature ::= OCTET STRING
        #*     END
        #*/

        # structure encoding
        MAGICNUMBER_JAVA_ENCODING = b"\x30\x82\x05\x24\x02\x01"
        temp_signed_output.write(MAGICNUMBER_JAVA_ENCODING)

        # formatVersoin=01
        MAGICNUMBER_JAVA_ENCODING = b"\x01"
        temp_signed_output.write(MAGICNUMBER_JAVA_ENCODING)

        #certificate (parsed ASN1)
        temp_signed_output.write(parsed_key)

        #Algorithm, Parameters
        MAGICNUMBER_JAVA_ENCODING = b"\x30\x0b\x06\x09\x2a\x86\x48\x86"
        temp_signed_output.write(MAGICNUMBER_JAVA_ENCODING)
        MAGICNUMBER_JAVA_ENCODING = b"\xf7\x0d\x01\x01\x0b\x30\x0d\x13\x05"
        temp_signed_output.write(MAGICNUMBER_JAVA_ENCODING)

        # Target
        targettype=image_type
        temp_signed_output.write(targettype.encode('utf-8'))

        # structure encoding
        MAGICNUMBER_JAVA_ENCODING = b"\x02\x04"
        temp_signed_output.write(MAGICNUMBER_JAVA_ENCODING)

        # Target length
        imagelength=os.path.getsize(image_to_be_signed)
        temp_signed_output.write(imagelength.to_bytes(4, byteorder='big', signed=False))

        #dont include the calculated hash it's not part of the structure, target generates our hash
        #uncomment if you want to include
        #temp_system_header.write(d)

        # structure encoding
        MAGICNUMBER_JAVA_ENCODING = b"\x04\x82\x01\x00"
        temp_signed_output.write(MAGICNUMBER_JAVA_ENCODING)

        #signature
        temp_signed_output.write(signature)

    dp.output(colored("System Header creation for " + image_type +
        " Package Firmware done", 'green'))

def main():
    #     main

    parser = argparse.ArgumentParser()
    parser.add_argument("-t", "--type", action='append', help="Image type", required=True)
    parser.add_argument("-f", "--file", action='append', help="JSON file(s)", required=True)
    parser.add_argument("-k", "--keyname", action='append', help="Base for keyname", required=True)
    parser.add_argument("-u", "--unsigned", action='append', help="File to be signed", required=True)
    parser.add_argument("-s", "--signed", action='append', help="File name for output of signing", required=True)
    parser.add_argument("-v", "--verbose", action='append', help="Debug mode, extra verbose", required=False)
    parser.add_argument("-o", "--tmpoutput", action='store', help="Output directory", required=False, default=".")
    args = parser.parse_args()

    d = Debug("Verbosity")
    dp = DebugPrint(d)

    dm = False
    if args.verbose is not None:
        for debug in args.verbose:
            dm = debug

    d.set(dm)
    dp.output("ANDROID_SIGNATURE: Python Signing Method")
    dp.output("Verbose Mode On : {}".format(d.test()))

    colorama_init()
    for arg_signed_output in args.signed:
        for arg_unsigned_src in args.unsigned:
            for arg_key_name in args.keyname:
                for json_file_path in args.file:
                    for image_type in args.type:
                        process_file(image_type, json_file_path, arg_key_name, arg_unsigned_src, arg_signed_output, args.tmpoutput, dp)

if __name__ == "__main__":
    main()
