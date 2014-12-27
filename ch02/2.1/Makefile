.PHONY: clean %.class

CFLAGS = -classpath ".:/home/yang/programming/algs4ed/algs4.jar:/home/yang/programming/algs4ed/stdlib.jar"

all :  Experiment StringSorter FileSorter Sort SelectionSort InsertionSort

Experiment : Experiment.class Sort.class

StringSorter : StringSorter.class Sort.class

FileSorter : FileSorter.class Sort.class

Sort : Sort.class

SelectionSort : SelectionSort.class

InsertionSort : InsertionSort.class

%.class : %.java
	export CLASSPATH=".:/home/yang/programming/algs4ed/algs4.jar:/home/yang/programming/algs4ed/stdlib.jar"
	javac $(CFLAGS) $<

clean:
	rm -rf *.class
