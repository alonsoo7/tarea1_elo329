JFLAGS = -g -encoding ISO8859_1  # -encoding in case latin characters are used
JC = javac
JVM= java  # Added by Agustín González
CONFIG = config3.txt
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = T1Stage3.java Broker.java Topic.java \
		Publisher.java Subscriber.java \
	    Recorder.java Component.java Follower.java

MAIN = T1Stage3

default: classes

classes: $(CLASSES:.java=.class)

run: 
	$(JVM) $(MAIN) $(CONFIG)

clean:
	$(RM) *.class