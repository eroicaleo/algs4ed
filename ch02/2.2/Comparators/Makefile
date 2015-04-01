.PHONY: clean %.class

CFLAGS = -classpath ".:/home/yang/programming/algs4ed/algs4.jar:/home/yang/programming/algs4ed/stdlib.jar"

all :  MergeSortComparator Student

MergeSortComparator : MergeSortComparator.class

Student : Student.class MergeSortComparator.class

%.class : %.java
	export CLASSPATH=".:/home/yang/programming/algs4ed/algs4.jar:/home/yang/programming/algs4ed/stdlib.jar"
	javac $(CFLAGS) $<

clean:
	rm -rf *.class
