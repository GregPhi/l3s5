#include <sys/types.h>
#include <sys/wait.h>
#include <semaphore.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int n = 10;
sem_t sems[10];
sem_t towait;

void p(int);

int main() {
	int i, status = 0;
	pid_t pid, wpid;

	sem_init(&towait, 1, 0);
	for (i = 0; i < n; i++) {
		sem_init(&sems[i], 1, 0);
		printf("init sem %d\n", i);
	}

	for (i = 0; i < n; i++) {
		pid = fork();
		if (pid == 0) {
			p(i);
			exit(EXIT_SUCCESS);
		}
	}


	while ((wpid = wait(&status)) > 0);

	printf("%d semaphores used\n", n);
	return 1;
}

void p(int sem_index) {
	int sval;

	printf("a%d\n", sem_index);

	sem_post(&sems[0]);
	sem_getvalue(&sems[0], &sval);
	printf("%d\n", sval);

	if (sval >= n) {
		sem_post(&sems[sem_index]);
	}

	sem_wait(&towait);

	printf("b%d\n", sem_index);
}
