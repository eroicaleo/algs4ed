.PHONY: clean %.class

UNAME_S := $(shell uname -s)
ifeq ($(UNAME_S),Linux)
		CPATH = "/home/yang/programming"
endif
ifeq ($(UNAME_S),Darwin)
		CPATH = "/Users/yang/Programming"
endif

CFLAGS = -classpath ".:$(CPATH)/algs4ed/algs4.jar:$(CPATH)/algs4ed/stdlib.jar"

all : PointSET RangeSearchVisualizer RectHV NearestNeighborVisualizer KdTree

PointSET : PointSET.class

RangeSearchVisualizer : RangeSearchVisualizer.class RectHV.class

RectHV : RectHV.class

NearestNeighborVisualizer : NearestNeighborVisualizer.class PointSET.class

KdTree : KdTree.class

%.class : %.java
	export CLASSPATH=".:$(CPATH)/algs4ed/algs4.jar:$(CPATH)/algs4ed/stdlib.jar"
	javac $(CFLAGS) $<

clean:
	rm -rf *.class
