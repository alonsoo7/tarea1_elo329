JFLAGS = -g -encoding ISO8859_1
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = T1Stage1.java Broker.java Topic.java \
		Publisher.java Subscriber.java \
		Follower.java Component.java

MAIN = T1Stage1

# Compilar
all: classes

classes: $(CLASSES:.java=.class)

# Ejecutar
run: classes
	$(JVM) $(MAIN) $(CONFIG)

# Limpiar archivos .class
clean:
	$(RM) *.class

# Forzar recompilación ignorando marcas de tiempo
force: clean all