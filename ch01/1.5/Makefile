.PHONY: clean %.class

CFLAGS = -classpath /home/yang/programming/algs4ed/algs4.jar:/home/yang/programming/algs4ed/stdlib.jar

all :  UF QuickFindUF QuickUnionUF WeightedQuickUnionUF 

UF : UF.class

QuickFindUF : QuickFindUF.class

QuickUnionUF : QuickUnionUF.class

WeightedQuickUnionUF : WeightedQuickUnionUF.class

%.class : %.java
	export CLASSPATH=".:/home/yang/programming/algs4ed/algs4.jar:/home/yang/programming/algs4ed/stdlib.jar"
	javac $(CFLAGS) $^

clean:
	rm -rf *.class
