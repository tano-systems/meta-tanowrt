# Running image inside Oracle VirtualBox

Tested with `tanowrt-image-full` image builded for `qemux86-64` machine.

## Building a VMDK image

The Yocto project provides support for building VMDK (VMware Disk) images, which are also recognized by VirtualBox.

By default VMDK images automatically produced for the folowing machines:
- `qemux86`;
- `qemux86-screen`;
- `qemux86-64`;
- `qemux86-64-screen`.

For example, build a `tanowrt-image-full` image for `qemux86-64` machine:
```
$ MACHINE=qemux86-64 bitbake tanowrt-image-full
```

When the build is complete, the VMDK image file will be located in folder
```
./tanowrt-glibc/deploy/images/qemux86-64/tanowrt-image-full-qemux86-64.wic.vmdk
```

## Running the VMDK image

From the Oracle VM VirtualBox Manager, create a new Virtual Machine

Name and operating system:
1. Name: tanowrt-qemux86-64
2. Type: Linux
3. Version: Other Linux (64-bit)
4. Click "Next"

Memory size:
1. Select: 256 MB (or more)
2. Click "Next"

Hard disk:
1. Select: Do not add a virtual hard disk
2. Select "Create", then "Continue"

From the Oracle VM VirtualBox Manager, change VM settings:
1. Select System on the left pane, then tick on Enable EFI option
2. Under Storage > Add new storage controller > Add SATA Controller
3. Under Storage > Controller: SATA > Add Hard Disk
4. Select: Choose existing disk
5. Virtual hard disk file: .../tanowrt-image-full-qemux86-64.wic.vmdk
6. Click "OK"

From the Oracle VM VirtualBox Manager, start the VM.

You may then login as user root with password root.


## Acknowledgments

This text is based on article "Running a Yocto image inside VirtualBox"[^1] from http://gmacario.github.io.

[^1]: http://gmacario.github.io/howto/gdp/genivi/yocto/virtualbox/embedded/ivi/2015/11/14/running-yocto-image-inside-virtualbox.html
