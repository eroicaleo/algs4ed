.PHONY: clean %.class

CFLAGS = -classpath /home/yang/programming/algs4ed/algs4.jar:/home/yang/programming/algs4ed/stdlib.jar

all :  MyAverage BinarySearch TestArray StaticMethods API MyRandomSeq DrawSomething

BinarySearch : BinarySearch.class

TestArray : TestArray.class

StaticMethods : StaticMethods.class

API : API.class

MyRandomSeq : MyRandomSeq.class

MyAverage : MyAverage.class

DrawSomething : DrawSomething.class

%.class : %.java
	export CLASSPATH="/home/yang/programming/algs4ed/algs4.jar:/home/yang/programming/algs4ed/stdlib.jar:."
	javac $(CFLAGS) $^

clean:
	rm -rf *.class
