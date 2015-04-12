.PHONY: clean %.class

CFLAGS = -classpath ".:/home/yang/programming/algs4ed/algs4.jar:/home/yang/programming/algs4ed/stdlib.jar"

all :  Quick Quick3

Quick : Quick.class

Quick3 : Quick3.class

Quick3cpp : Quick3.cpp
	g++ -g -std=c++11 -Wall Quick3.cpp -o Quick3

%.class : %.java
	export CLASSPATH=".:/home/yang/programming/algs4ed/algs4.jar:/home/yang/programming/algs4ed/stdlib.jar"
	javac $(CFLAGS) $<

clean:
	rm -rf *.class
