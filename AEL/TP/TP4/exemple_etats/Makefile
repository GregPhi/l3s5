compil: clean
	mkdir bin/
	jflex src/exemple_etats.lex
	javac -cp src -d bin src/*.java

clean:
	rm -rf bin/
	rm -f src/Yylex.java
	rm -f src/Yylex.java~

exe:
	java -cp bin Decoupe data.txt
