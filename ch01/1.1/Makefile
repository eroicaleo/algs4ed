.PHONY: clean

CFLAGS = -classpath /home/yang/programming/algs4ed/algs4.jar:/home/yang/programming/algs4ed/stdlib.jar

all : BinarySearch TestArray

% : %.java
	export CLASSPATH="/home/yang/programming/algs4ed/algs4.jar:/home/yang/programming/algs4ed/stdlib.jar"
	javac $(CFLAGS) $^

clean:
	rm -rf *.class
