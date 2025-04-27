JFLAGS = -g -encoding ISO8859_1  # -encoding in case latin characters are used
JC = javac
JVM= java  # Added by Agustín González
CONFIG = configEXTRA.txt
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = Simulador.java Broker.java Topic.java \
		Publisher.java Subscriber.java \
		Follower.java Recorder.java Monitor.java Component.java Contador.java

MAIN = Simulador

default: classes

classes: $(CLASSES:.java=.class)

run: 
	$(JVM) $(MAIN) $(CONFIG)

clean:
	$(RM) *.class
