all:
	jflex tokens.flex && byaccj -v -Jclass=Parser parser.y
	javac Letras.java && javac Parser.java

run:
	java Parser test

debug:
	java Parser test true

clean:
	rm -f *.class  *.java* y.tab.c
