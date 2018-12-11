compil: clean
	mkdir bin/
	jflex src/postfixeeavecCom.lex
	javac -cp src -d bin src/*.java

clean:
	rm -rf bin/
	rm -f src/Yylex.java
	rm -f src/Yylex.java~

dec:
	java -cp bin Decoupe dec.txt

dec2:
	java -cp bin Decoupe eval2.txt	

eval:
	java -cp bin Evaluateur eval.txt

eval2:
	java -cp bin Evaluateur eval2.txt
