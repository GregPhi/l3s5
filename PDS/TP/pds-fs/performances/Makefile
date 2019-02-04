CC      = gcc

CFLAGS  = -Wall -Wextra -ansi -pedantic
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g

mcat-scd: mcat-scd.o
	$(CC) $(LDFLAGS) -o $@ $^

%.o: %.c
	$(CC) $(CFLAGS) -c $<

all: clean mcat-scd

file:
	./file.sh

clean:
	rm -f mcat-scd.o
	rm -f mcat-scd

cleanFile:
	rm -f monfichier1*
	rm -f log.log

git: clean cleanFile
