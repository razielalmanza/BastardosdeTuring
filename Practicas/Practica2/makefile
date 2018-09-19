all:
	jflex tokens.flex && byaccj -v -Jclass=Parser parser.y
	javac Letras.java && javac Parser.java

test:
	java Parser test

clean:
	rm -f *.class  *.java* y.tab.c
