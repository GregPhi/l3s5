#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <assert.h>
#include <stdlib.h>
#include <stdio.h>
#include <fcntl.h>

void usage();
int numberOfLine(const char *pathname);
void tail_simpliste(const char *pathname, int ntail);
void tail_efficace(const char *pathname, int ntail);
void display_efficace(int ntail, int file_descriptor);
void tail_utile(const char *pathname, int ntail);
void display_utile(int ntail, int file_descriptor, int pos);

void usage (char *prog) {
   printf("usage : %s [n] tail", prog);
}

/*
 * -> read jusqu'à la fin, donc remettre curseur pour puvoir recommencer une lecture du fichier
 */
int numberOfLine(const char *pathname){
  int file_descriptor;
  int numberLine = 0;
  char buffer[1];
  int readOctet;
  file_descriptor=open(pathname,O_RDONLY);
  while((readOctet=read(file_descriptor,buffer,1))!=0){
    assert(readOctet!=-1);
    if(buffer[0]=='\n'){
      numberLine++;
    }
  }
  close(file_descriptor);
  return numberLine;
}

void tail_simpliste(const char *pathname, int ntail){
  int file_descriptor;
  int startLine = 0;
  int numberLine = 0;
  int currentLine = 0;
  char buffer[1];
  file_descriptor=open(pathname,O_RDONLY);
  assert(file_descriptor!=-1);
  numberLine=numberOfLine(pathname);
  startLine = numberLine-ntail;
  while(read(file_descriptor,buffer,1)!=0){
    if(currentLine>=startLine){
      assert(write(STDOUT_FILENO,buffer,1)!=-1);
    }
    if(buffer[0]=='\n'){
      currentLine++;
    }
  }
  close(file_descriptor);
}

void tail_efficace(const char *pathname, int ntail){
  int file_descriptor;
  struct stat s;
  int test_s;
  int size;
  char buffer[1];
  file_descriptor=open(pathname,O_RDONLY);
  assert(file_descriptor!=-1);
  test_s = stat(pathname,&s);
  assert(test_s!=-1);
  size=s.st_size;
  printf("%d\n", size);
}

void tail_utile(const char *pathname, int ntail){
  int file_descriptor;
  int pos;
  file_descriptor=open(pathname,O_RDONLY);
  assert(file_descriptor!=-1);
  pos=lseek(file_descriptor,0,SEEK_END);
  assert(pos!=-1);
  display_utile(ntail,file_descriptor, pos);
}

void display_utile(int ntail, int file_descriptor, int pos){
  char tab[5];
  int read_caract, i, write_caract;
  if(ntail==0){
    return;
  }
  if(pos<=0){
    return;
  }
  if(pos>5){
    read_caract=pread(file_descriptor,&tab,5,pos-5);
  }else{
    read_caract=pread(file_descriptor,&tab,pos,0);
  }

  assert(read_caract!=-1);
  pos-=read_caract;

  for(i=read_caract;i>=0;i--){
    if(tab[i]=='\n'){
      ntail--;
    }
    if(ntail<0){
      write_caract=write(STDOUT_FILENO,tab+i+1,read_caract-i-1);
      assert(write_caract!=-1);
      return;
    }
  }

  display(ntail,file_descriptor,pos);
  write_caract=write(STDOUT_FILENO,&tab,5);
  assert(write_caract!=-1);
}

int main(int argc, char *argv[]){
  int ntail = 10;

  assert(argc>=2);
  if(argc==2){
    printf("number of line %d\n", ntail);
    printf("tail_simpliste\n");
    tail_simpliste(argv[1],ntail);
    printf("tail_efficace\n");
    tail_efficace(argv[1],ntail);
    printf("tail_utile\n");
    tail_utile(argv[1],ntail);
  }else{
    ntail = atoi(argv[1]);
    printf("number of line %d\n", ntail);
    printf("tail_simpliste\n");
    tail_simpliste(argv[2],ntail);
    printf("tail_efficace\n");
    tail_efficace(argv[2],ntail);
    printf("tail_utile\n");
    tail_utile(argv[2],ntail);
  }
  return EXIT_SUCCESS;
}
