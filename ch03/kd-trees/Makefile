.PHONY: clean %.class

UNAME_S := $(shell uname -s)
ifeq ($(UNAME_S),Linux)
		CPATH = "/home/yang/programming"
endif
ifeq ($(UNAME_S),Darwin)
		CPATH = "/Users/yang/Programming"
endif

CFLAGS = -classpath ".:$(CPATH)/algs4ed/algs4.jar:$(CPATH)/algs4ed/stdlib.jar"

all : PointSET

PointSET : PointSET.class

%.class : %.java
	export CLASSPATH=".:/home/yang/programming/algs4ed/algs4.jar:/home/yang/programming/algs4ed/stdlib.jar"
	javac $(CFLAGS) $<

clean:
	rm -rf *.class
