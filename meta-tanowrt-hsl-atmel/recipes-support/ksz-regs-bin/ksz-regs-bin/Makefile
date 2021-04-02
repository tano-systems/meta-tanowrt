EXE=regs_bin

all: $(EXE)

$(EXE): regs_bin.c
	$(CC) -c -o $(EXE).o $(CFLAGS) regs_bin.c
	$(CC) -o $(EXE) $(LDFLAGS) $(EXE).o

clean:
	$(RM) $(EXE)

install: $(EXE)
	cp -r $(EXE) $(DESTDIR)
