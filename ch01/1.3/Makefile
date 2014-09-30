.PHONY: clean %.class

CFLAGS = -classpath /home/yang/programming/algs4ed/algs4.jar:/home/yang/programming/algs4ed/stdlib.jar

all : StackOfStrings LinkedStackOfStrings FixedCapacityStackOfStrings

StackOfStrings : StackOfStrings.class

LinkedStackOfStrings: LinkedStackOfStrings.class

FixedCapacityStackOfStrings: FixedCapacityStackOfStrings.class

%.class : %.java
	export CLASSPATH=".:/home/yang/programming/algs4ed/algs4.jar:/home/yang/programming/algs4ed/stdlib.jar"
	javac $(CFLAGS) $^

clean:
	rm -rf *.class
