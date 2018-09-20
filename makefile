#Makefile for JFFCAL001 prac 6

#Define variables for compiler, paths, etc.
JC = javac
JDOC = javadoc
JFLAGS = -gs
SRC = src/
SPATH = -sourcepath ./src
BIN = bin/
CP = -cp ./bin
DEST = -d ./$(BIN)
DOCPATH = -d ./doc

#Suffixes
.SUFFIXES: .java .class .javadoc

#Source files
SRCFILES = src/SumTreeArray.java \
	src/SumTreeSunlight.java

#Class files
CLASSES = $(subst src/, bin/, $(SRCFILES:.java=.class))

#Rule when make is run by default. Runs classes rule
default: classes

#classes rule
classes: $(CLASSES)

#Make default class dependencies on their .java files
./bin/%.class: ./src/%.java
	$(JC) $(CP) $(SPATH) $< $(DEST)

#./bin/SumTreeSunlight.class: ./src/SumTreeArray.java
#	$(JC) $(CP) $(SPATH) ./src/SumTreeSunlight.java $(DEST)

#generate javadocs
docs: 
	$(JDOC) $(DOCPATH) $(SPATH) $(SRC)*.java

#run java apps
run: 
	java $(CP) SumTreeSunlight ${ARGS}

#Delete class and doc files	
clean:
	rm ./bin/*.class
	rm ./doc/*
