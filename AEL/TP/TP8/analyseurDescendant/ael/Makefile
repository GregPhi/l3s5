compil: clean
	mkdir bin/
	jflex src/condenses_lex/exo2.lex
	javac -cp src -d bin src/ard/Exo2.java

exe:
	java -cp bin/ard Exo2 data.txt

clean:
	rm -rf bin/
	rm -f src/condenses_lex/Yylex.java
	rm -f src/condenses_lex/Yylex.java~

all: clean compil exe
