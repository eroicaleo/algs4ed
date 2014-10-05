.PHONY: clean %.class

CFLAGS = -classpath /home/yang/programming/algs4ed/algs4.jar:/home/yang/programming/algs4ed/stdlib.jar

all :  Counter Flip Roll Interval2DTest StringTest Date Accumulator VisualAccumulator

Counter : Counter.class

Flip : Flip.class

Roll : Roll.class

Interval2DTest : Interval2DTest.class

StringTest : StringTest.class

Date : Date.class

Accumulator : Accumulator.class

VisualAccumulator : VisualAccumulator.class

%.class : %.java
	export CLASSPATH=".:/home/yang/programming/algs4ed/algs4.jar:/home/yang/programming/algs4ed/stdlib.jar"
	javac $(CFLAGS) $^

clean:
	rm -rf *.class
