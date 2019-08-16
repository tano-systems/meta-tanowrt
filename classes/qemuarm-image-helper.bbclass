def parse_dtbs(d):
    import re
    kdt=re.sub(r'\b[\w-]+\/', '', d.getVar('KERNEL_DEVICETREE', ''))
    dtbs=""
    dtbcount=1
    for DTB in kdt.split():
        if dtbcount == 1:
            dtbs += DTB+";oftree"
        dtbs += " "+DTB
        dtbcount += 1
    return dtbs
