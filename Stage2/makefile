JFLAGS = -g -encoding ISO8859_1  # -encoding in case latin characters are used
JC = javac
JVM= java  # Added by Agustín González
CONFIG = config.txt
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = T1Stage2.java Broker.java Topic.java \
		Publisher.java Subscriber.java \
		Follower.java Component.java Recorder.java

MAIN = T1Stage2

# Compilar
all: classes

classes: $(CLASSES:.java=.class)

# Ejecutar
run: classes
	$(JVM) $(MAIN) $(CONFIG)

clean:
	$(RM) *.class