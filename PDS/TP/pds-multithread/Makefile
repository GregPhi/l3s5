CC      = gcc

CFLAGS  = -Wall -Wextra
CFLAGS += -g
CFLAGS += -pthread

LDFLAGS = -pthread

TOUS := aleazard compteur-gc tri-rapide semaphore
RES := res-tri-seuil-nbthread.dat res-compteur-gp.dat

tout: ${TOUS}

tri-rapide: pile.o tri.o rapide.o main.o
	${CC} ${LDFLAGS} -o $@ $^

semaphore: semaphore.o

%.o: %.c
	${CC} ${CFLAGS} -c $<

%: %.o
	${CC} ${LDFLAGS} -o $@ $^

%: %.c

# Configure des cibles pour l’intégration continue
compteur-gc-ci: CFLAGS+=-Werror
compteur-gc-ci: compteur-gc

tri-rapide-ci: CFLAGS+=-Werror
tri-rapide-ci: tri-rapide

clean:
	rm -f core *.o

realclean: clean
	rm -f ${TOUS} ${RES} res.dat

.PHONY: all clean realclean
