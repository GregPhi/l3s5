compil: clean
	mkdir bin/
	jflex src/exsujet0.lex
	javac -cp src -d bin src/ExSujet0.java

exe:
	java -cp bin ExSujet0 data.txt

pdf:
	jflex -dot src/exsujet0.lex
	dot -T pdf -O dfa-big.dot
	dot -T pdf -O dfa-min.dot
	dot -T pdf -O nfa.dot
	rm -f dfa-big.dot
	rm -f dfa-min.dot
	rm -f nfa.dot

clean:
	rm -rf bin/
	rm -f src/Yylex.java
	rm -f src/Yylex.java~
	rm -f *.dot
	rm -f dfa-big.dot.pdf
	rm -f dfa-min.dot.pdf
	rm -f nfa.dot.pdf

all: clean compil exe pdf
