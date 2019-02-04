CC      = gcc

CFLAGS  = -Wall -Wextra -ansi -pedantic
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g

multif: multif.o
	$(CC) $(LDFLAGS) -o $@ $^

course: course.o
	$(CC) $(LDFLAGS) -o $@ $^

observe: observe.o
	$(CC) $(LDFLAGS) -o $@ $^

%.o: %.c
	$(CC) $(CFLAGS) -c $<

all: clean multif course observe

clean:
	rm -f *.o
	rm -f multif
	rm -f course
	rm -f observe
