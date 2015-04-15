.PHONY: clean %.class

CFLAGS = -classpath ".:/home/yang/programming/algs4ed/algs4.jar:/home/yang/programming/algs4ed/stdlib.jar"

all :  Transaction UnsortedMinPQ BinaryHeapMaxPQ HeapSort

Transaction : Transaction.class

UnsortedMinPQ : UnsortedMinPQ.class

BinaryHeapMaxPQ : BinaryHeapMaxPQ.class

HeapSort : HeapSort.class

%.class : %.java
	export CLASSPATH=".:/home/yang/programming/algs4ed/algs4.jar:/home/yang/programming/algs4ed/stdlib.jar"
	javac $(CFLAGS) $<

clean:
	rm -rf *.class
