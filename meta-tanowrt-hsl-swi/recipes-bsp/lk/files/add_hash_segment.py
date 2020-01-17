#=============================================================================
#
#  add_hash_segment.py
#
# Description:
#   Add Hash Segment to ELF format Image
#
#   Input :
#          Image
#   Output :
#          Image with Hash Segment
#
#=============================================================================
import os, sys
import shutil
import subprocess
import struct
import re

ConvertLong = 0x00000000FFFFFFFF

MBN_Header_Image_Type = {
	'SBL1' : [21] ,
	'SBL2' : [22] ,
	'QRPM' : [23] ,
	'QPAR' : [33] ,
	'DSP1' : [8 ] ,
	'DSP2' : [12] ,
	'DSP3' : [28] ,
	'FILE' : [34] ,
	'APBL' : [5 ] ,
	'APPS' : [6 ] ,
	'SYST' : [30] ,
	'USER' : [31] ,
	'UAPP' : [32] ,
	'TZON' : [25] ,
	'MBA'  : [0] ,
	'MODEM' : [12] ,
	'EHOSTDL' : [13],
	'CUS0' : [35],
	'CUS1' : [36],
	'empty' : [0],
}

class MBN_Header:
	codeword = 0
	magic = 0
	image_id = 0
	reserved_1 = 0
	reserved_2 = 0
	header_vsn_num = 0
	image_src = 0
	image_dest_ptr = 0
	image_size  = 0
	code_size = 0
	signature_ptr = 0
	signature_size = 0
	cert_chain_ptr = 0
	cert_chain_size = 0
	oem_root_cert_sel  = 0
	oem_num_root_certs = 0
	reserved_5 = 0
	reserved_6 = 0
	reserved_7 = 0
	reserved_8 = 0
	reserved_9 = 0

class Elf_Program_Header:
	p_type = 0
	p_offset = 0
	p_vaddr = 0
	p_paddr = 0
	p_filesz = 0
	p_memsz = 0
	p_flags = 0
	p_align = 0

class Elf_Header:
	ephoff = 0
	eshoff = 0
	flags  = 0
	ehsize = 0
	phentsize = 0
	phnum = 0
	shentsize = 0
	shnum = 0
	shstrndx = 0

class bcolors:
	HEADER    = '\033[95m'
	OKBLUE    = '\033[94m'
	OKGREEN   = '\033[92m'
	WARNING   = '\033[93m'
	FAIL      = '\033[91m'
	ENDC      = '\033[0m'
	BOLD      = '\033[1m'
	UNDERLINE = '\033[4m'

def GetIE (IN,VarName ):
	#Routine to read in Little Endian Form
	Temp, = struct.unpack('<I', IN.read(4))
	Var = Temp & ConvertLong
	return Var

def GetIE2 (IN, VarName1 , VarName2 ):
	Temp, = struct.unpack('<I', IN.read(4))
	Var1 = Temp & 0x000000000000FFFF
	print "%s = %08x %d" % (VarName1,Var1,Var1)
	Var2 = (Temp & 0x00000000FFFF0000)>>16
	print "%s = %08x %d" % (VarName2,Var2,Var2)
	return (Var1,Var2)

def SET_IE(OUT,Var):
	hb = struct.pack( '<I', Var)
	OUT.write(hb)

def SET_IE2(OUT,Var1,Var2):
	#Routine to write in Little Endian Form
	hb = struct.pack( 'hh', Var1,Var2)
	OUT.write(hb)

def CopyMbnHeader (OUT,MbnHeader):
	SET_IE(OUT,MbnHeader.image_id)
	SET_IE(OUT,MbnHeader.header_vsn_num)
	SET_IE(OUT,MbnHeader.image_src)
	SET_IE(OUT,MbnHeader.image_dest_ptr)
	SET_IE(OUT,MbnHeader.image_size)
	SET_IE(OUT,MbnHeader.code_size)
	SET_IE(OUT,MbnHeader.signature_ptr)
	SET_IE(OUT,MbnHeader.signature_size)
	SET_IE(OUT,MbnHeader.cert_chain_ptr)
	SET_IE(OUT,MbnHeader.cert_chain_size)


def CreateMbnHeaderMDM9x40(ImageName,CodeSize):
	res = MBN_Header()
	res.image_id       = int(MBN_Header_Image_Type[ImageName][0])
	res.header_vsn_num = 3
	res.image_src	   = 0
	res.image_dest_ptr = 0
	res.code_size      = CodeSize
	res.signature_ptr  = res.code_size + res.image_dest_ptr
	res.signature_size = 0
	res.cert_chain_size= 0
	res.cert_chain_ptr = res.signature_ptr + res.signature_size
	res.image_size     = res.code_size + res.signature_size + res.cert_chain_size
	return res

def ReadELFheader(IN):
	res = Elf_Header()
	IN.read(28)
	res.ephoff = GetIE(IN, "ELF Header - ephoff")
	res.eshoff = GetIE(IN, "ELF Header - eshoff")
	res.flags = GetIE(IN, "ELF Header - flags")
	(res.ehsize, res.phentsize)= GetIE2(IN, "ELF Header - ehsize","ELF Header - phentsize")
	(res.phnum, res.shentsize)= GetIE2(IN, "ELF Header - phnum","ELF Header - shentsize")
	(res.shnum, res.shstrndx)= GetIE2(IN, "ELF Header - shnum","ELF Header - shstrndx")
	return res

def CreateElfPHdrSegment0(CodeSize):
	#Create ELF Program header segment 0
	ProgHeader = Elf_Program_Header()
	ProgHeader.p_type   = 0
	ProgHeader.p_offset = 0
	ProgHeader.p_vaddr  = 0
	ProgHeader.p_paddr  = 0
	ProgHeader.p_filesz = CodeSize + 2*32
	ProgHeader.p_memsz  = 0
	ProgHeader.p_flags  = 117440512
	ProgHeader.p_align  = 0
	return ProgHeader


def CreateElfPHdrSegment1(ImageName,CodeSize,TotalNoOfsegments):
	#Create ELF Program header segment 1
	process = subprocess.Popen("readelf -h %s | grep Entry | tail -c 11" % ImageName, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
	output, error = process.communicate()
	vaddr = int(output.strip(), 16)

	ProgHeader = Elf_Program_Header()
	ProgHeader.p_type   = 0
	ProgHeader.p_offset = 4096
	ProgHeader.p_vaddr  = vaddr + 1048576
	ProgHeader.p_paddr  = vaddr + 1048576
	ProgHeader.p_filesz = CodeSize + TotalNoOfsegments*32 + 40
	ProgHeader.p_memsz  = 8192
	ProgHeader.p_flags  = 35651584
	ProgHeader.p_align  = 4096
	return ProgHeader


def WriteElfHeader(IN,ProgHeader):
	SET_IE(IN,ProgHeader.ephoff)
	SET_IE(IN,ProgHeader.eshoff)
	SET_IE(IN,ProgHeader.flags)
	SET_IE2(IN,ProgHeader.ehsize,ProgHeader.phentsize)
	SET_IE2(IN,ProgHeader.phnum,ProgHeader.shentsize)
	SET_IE2(IN,ProgHeader.shnum,ProgHeader.shstrndx)

def WriteElfProgramHeader(IN,ProgHeader):
	SET_IE(IN,ProgHeader.p_type)
	SET_IE(IN,ProgHeader.p_offset)
	SET_IE(IN,ProgHeader.p_vaddr)
	SET_IE(IN,ProgHeader.p_paddr)
	SET_IE(IN,ProgHeader.p_filesz)
	SET_IE(IN,ProgHeader.p_memsz)
	SET_IE(IN,ProgHeader.p_flags)
	SET_IE(IN,ProgHeader.p_align)

def ReadElfProgramHeader(IN):
	ProgHeader = Elf_Program_Header()
	ProgHeader.p_type   = GetIE(IN,"ProgHeader.p_type")
	ProgHeader.p_offset = GetIE(IN,"ProgHeader.p_offset")
	ProgHeader.p_vaddr  = GetIE(IN,"ProgHeader.p_vaddr")
	ProgHeader.p_paddr  = GetIE(IN,"ProgHeader.p_paddr")
	ProgHeader.p_filesz = GetIE(IN,"ProgHeader.p_filesz")
	ProgHeader.p_memsz  = GetIE(IN,"ProgHeader.p_memsz")
	ProgHeader.p_flags  = GetIE(IN,"ProgHeader.p_flags")
	ProgHeader.p_align  = GetIE(IN,"ProgHeader.p_align")
	return ProgHeader

def ContainsHashSegment(file):
	elfheader = ReadELFheader(IN)
	# To read 2 program header
	IN.seek(elfheader.ephoff+32)
	Position = IN.tell()
	ProgHeader = ReadElfProgramHeader(IN)
	if ProgHeader.p_offset == 4096 and ProgHeader.p_type==0:
		return True
	else:
		return False

def CalculateHashSegmentMDM9x40(file):
	#Calculate 32 Bytes hash for segment0
	print (" Calculate Hash Segment")
	hashfilename = "hash_seg0.bin"
	os.system("openssl dgst -sha256 -binary %s > %s" % (file,hashfilename))
	HashFile = open(hashfilename,"rb")
	HashLine = HashFile.read(32)
	HashFile.close()
	os.remove(hashfilename)
	return HashLine

def DecodeELFHeader(ImageNameElf):
	try:
		IN =  open(ImageNameElf,"rwb+")
	except Exception, e:
		print  "\nError: Can't open %s for reading: %s\n" % (ImageNameToSign,e)
		sys.exit(1)
	# Need to decode information from elf header
	elfheader = ReadELFheader(IN)
	IN.seek(elfheader.ephoff)
	# Array to store the location of split files
	OffsetArray = range(elfheader.phnum)
	for i in range (0,elfheader.phnum):
		Position = IN.tell()
		ProgHeader = ReadElfProgramHeader(IN)
		if ( i >= 1 ):
			OffsetArray[i] = ProgHeader.p_offset
	return OffsetArray


def AddHashSegmentToImage(file,ImageType,ScriptPath):
	try:
		shutil.rmtree("Temp",ignore_errors=0)
		os.mkdir("Temp")
	except Exception, error:
		os.mkdir("Temp")

	IN = open(file,'rb')
	#Split file to get segments
	ELFParameterList = getElfParameters(IN)
	CreateMdtFile(ELFParameterList,IN)
	IN = open("Temp/split.mdt", 'rb')
	res = ReadELFheader(IN)
	TotalNoOfsegments = res.phnum+2
	IN.close()
	#Add 2 new Elf program header to ELF header
	os.rename("Temp/split.b00", "Temp/split.b02" )
	os.rename("Temp/split.b01", "Temp/split.b03" )

	#Creating Segment0 and Segment1 for split.b00
	size = os.path.getsize("Temp/split.mdt")
	Segment0 = CreateElfPHdrSegment0(size)
	size = 6144 + 384
	Segment1 = CreateElfPHdrSegment1(file,size,TotalNoOfsegments)

	destination = open("Temp/split.b00" , 'wb')
	IN = open("Temp/split.mdt", 'rb')

	#Read starting bytes and remaing header
	line = IN.read(28)
	IN.seek(0)
	#Read and Update ELF header parameters (24 Bytes)
	res = ReadELFheader(IN)
	res.eshoff = 0 #don't have Section header table
	res.phnum = res.phnum + 2 #Total Segments
	res.shnum = 0 #not sections
	res.shstrndx = 0 #not sections

	destination.write(line)
	WriteElfHeader(destination,res)
	WriteElfProgramHeader(destination,Segment0)
	WriteElfProgramHeader(destination,Segment1)

	size = os.path.getsize("Temp/split.mdt")
	line = IN.read(size - 52)
	destination.write(line)
	destination.close()

	# Create MBN header with Hash Segments for split.b01
	destination = open("Temp/split.b01", 'wb')

	# code_size = segment_num * hash_size(32bytes)
	size = res.phnum*32
	MbnHeader = CreateMbnHeaderMDM9x40(ImageType,size)

	# Update MBN header fields
	process = subprocess.Popen("readelf -h %s | grep Entry | tail -c 11" % file, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
	output, error = process.communicate()
	vaddr = int(output.strip(), 16)
	MbnHeader.image_dest_ptr = vaddr + 1048576 + 40
	MbnHeader.signature_ptr  = MbnHeader.image_dest_ptr + MbnHeader.code_size
	MbnHeader.cert_chain_ptr = MbnHeader.signature_ptr + MbnHeader.signature_size

	CopyMbnHeader (destination,MbnHeader)

	# 1st HashSegment of ELF header
	HashSegment = CalculateHashSegmentMDM9x40("Temp/split.b00")
	destination.write(HashSegment)

	# 2nd HashSegment of cert chain contains \x00
	for i in range (0,32):
		destination.write ("\x00")

	# 3rd HashSegment of .b02
	Hash2  = CalculateHashSegmentMDM9x40("Temp/split.b02")
	destination.write(Hash2)

	# 4th HashSegment of .b03
	Hash3  = CalculateHashSegmentMDM9x40("Temp/split.b03")
	destination.write(Hash3)

	# Last HashSegment contains \x00
	for i in range (0,32):
		destination.write ("\x00")
	destination.close()

def CreateUnsignedImage(ImageFilename):
	if not(os.path.exists(OutputFolder)):
		try:
			os.makedirs(OutputFolder)
		except Exception, error:
			print error
	if '/' in (ImageFilename):
		ImageFilename = ImageFilename.rsplit('/', 1)[-1]
	ImageFilename = ImageFilename[0:ImageFilename.rindex(".")]
	SignedImage = "%s/%s.umbn" %(OutputFolder,ImageFilename);
	destination = open(SignedImage, 'wb')
	OffsetArray = DecodeELFHeader("Temp/split.b00")

	destination = open(SignedImage, 'wb')
	shutil.copyfileobj(open("Temp/split.b00", 'rb'), destination)
	destination.seek(OffsetArray[1])
	shutil.copyfileobj(open("Temp/split.b01", 'rb'), destination)
	destination.seek(OffsetArray[3])
	shutil.copyfileobj(open("Temp/split.b03", 'rb'), destination)
	destination.seek(OffsetArray[2])
	shutil.copyfileobj(open("Temp/split.b02", 'rb'), destination)
	destination.close()

def AddFile(IN,OUT, start, size):
	if size == 0:
		return
	IN.seek(start)
	outFile = open(OUT, 'ab')
	outFile.write(IN.read(size))
	outFile.close()

def check_hash(offset, IN):
	IN.seek(offset + 24)
	flags = struct.unpack('<I', IN.read(4))[0]
	if (flags & (0x7 << 24)) == (0x2 << 24):
		return True
	else:
		return False

def VerifyElfImage(file):
	file.seek(0)
	magic = file.read(4)
	if magic == '\x7fELF':
		return True
	else:
		return False

def getElfParameters(IN):
	hex = "0[xX][\dA-Fa-f]+"
	IN.seek(4)
	parameterList = {}
	command = "readelf -l %s" %(IN.name)
	process = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
	output,error = process.communicate()
	if error != "":
		print bcolors.FAIL + "Error: %s" %(error)+ bcolors.ENDC
		sys.exit(1)
	str = r'\s+\S+\s+(?P<offset>%s)\s+%s\s+(?P<phys>%s)\s+(?P<f_size>%s)\s+(?P<m_size>%s)' % (hex, hex, hex, hex, hex)
	match_entry = re.search(r'Entry point (%s)' % hex, output)
	match_offset = re.search(r'There are (\d+) program headers, starting at offset (\d+)', output)
	if not match_entry is None:
		parameterList['entry_point'] = match_entry.group(1)
	if not match_offset is None:
		parameterList['number_seg'] = int(match_offset.group(1))
		parameterList['pg_start'] = int(match_offset.group(2))
		parameterList['segments'] = []
	for match_offset in re.finditer(str, output):
		parameterList['segments'].append(match_offset.groupdict())
	for i, segment in enumerate(parameterList['segments']):
		segment['hashValue'] = check_hash(parameterList['pg_start'] + (i * 32), IN)
	return parameterList

def CreateMdtFile(ELFParameterList,IN):
	#print IN.tell()
	name = "./Temp/split.mdt"
	IN.seek(0)
	outFile = open(name, 'wb')
	outFile.write(IN.read(52))
	outFile.close()
	AddFile(IN, name, ELFParameterList['pg_start'], 32 * ELFParameterList['number_seg'])

	for segment in ELFParameterList['segments']:
		if segment['hashValue']==True:
			AddFile(IN, name, int(segment['offset'], 16), int(segment['f_size'], 16))
			break

	name = "./Temp/split"

	for i, seg in enumerate(ELFParameterList['segments']):
		start = int(seg['offset'], 16)
		size = int(seg['f_size'], 16)
		if size == 0:
			continue
		output = "%s.b%02d" % (name, i)
		IN.seek(start)
		outFile = open(output, 'wb')
		outFile.write(IN.read(size))
		outFile.close()

def ParseCommandLine():
	global ImageFilename, OutputFolder, ImageType
	line_pattern_build = re.compile(r'(image|build)=(.+)', flags=re.IGNORECASE)
	line_pattern_outputfolder = re.compile(r'(outputfolder|of)=(.+)',flags=re.IGNORECASE)
	line_pattern_imageType = re.compile(r'(imageType|type)=(.+)',flags=re.IGNORECASE)
	for argument in sys.argv:
		match_build = line_pattern_build.search(argument)
		match_outputfolder = line_pattern_outputfolder.search(argument)
		match_imageType = line_pattern_imageType.search(argument)
		if not match_build is None:
			ImageFilename = match_build.groups()[1]
			continue
		if not match_outputfolder is None:
			OutputFolder = match_outputfolder.groups()[1]
			continue
		if not match_imageType is None:
			ImageType = match_imageType.groups()[1]
			continue

	if ImageFilename == "empty":
		print bcolors.FAIL + "\n\nERROR: No image was specified" + bcolors.ENDC
		sys.exit(1)
	if OutputFolder == "empty":
		print bcolors.FAIL + "\n\nERROR: No output folder was specified"+ bcolors.ENDC
		sys.exit(1)

######################################################
# Main function
# To create image with Hash Segment
######################################################
i = None
j = None
ImageFilename = "empty"
OutputFolder = "empty"
ImageType = "empty"
ScriptPath = os.path.split(os.path.realpath(__file__))[0]
print ScriptPath
ParseCommandLine()
try:
	print ImageFilename
	IN = open(ImageFilename,"rb")
except Exception, e:
	print  bcolors.FAIL + "\nError: Can't open Image file for reading: %s\n" % e + bcolors.ENDC

if not VerifyElfImage(IN):
	print  bcolors.FAIL + "\nError: Not a valid elf image" % e + bcolors.ENDC
	sys.exit(1)

if (ContainsHashSegment(ImageFilename) == False):
	AddHashSegmentToImage(ImageFilename,ImageType,ScriptPath)
	CreateUnsignedImage(ImageFilename)
	print bcolors.OKGREEN + "Unsigned image created"+ bcolors.ENDC
else :
	if not(os.path.exists(OutputFolder)):
		try:
			os.makedirs(OutputFolder)
		except Exception, error:
			print error
	shutil.copy2(ImageFilename,OutputFolder)
	print bcolors.OKGREEN + "Image already contains hash segment and is copied to output folder"
