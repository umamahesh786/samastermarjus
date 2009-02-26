# DrinkMixer Makefile

#
# Paths
#

JAVABIN     = /usr/local/jdk1.2.2/bin
CLASS_DIR	= ./classes
SOURCE_DIR	= ./src
DOC_DIR		= ./docs
CP			= .:$(CLASS_DIR):$(CLASSPATH)

PACKAGES	= drinkMixer.model drinkMixer.model.fileParsers drinkMixer.views drinkMixer.model.drink.comparators drinkMixer.model.drink drinkMixer.event

#
# Executables
#

JAVA		= $(JAVABIN)/java -native 
JAVAC		= $(JAVABIN)/javac
JAVADOC		= $(JAVABIN)/javadoc
JAR			= $(JAVABIN)/jar

#
# Compile Flags
#

JFLAGS 		= -classpath $(CP) 
JCFLAGS		= -classpath $(CP) -sourcepath $(SOURCE_DIR) -d $(CLASS_DIR) 
DOCFLAGS	= -link http://java.sun.com/products/jdk/1.2/docs/api/ -public -d $(DOC_DIR) -sourcepath $(SOURCE_DIR) -classpath $(CP)
DOCTITLE	= -windowtitle 'Drink Mixer API Specification' -doctitle 'Drink Mixer API Specification'

#
#Rules to make Drink Mixer program
#

CLASSES = $(shell cat ./res/bfiles.txt)

default: $(CLASSES)
	
classes/%.class: src/%.java
	$(JAVAC) $(JCFLAGS) $<

classlist:
	find | grep .java > ./res/afiles.txt
	sed s/.java/.class/g ./res/afiles.txt > ./res/bfiles.txt
	sed s/src/classes/g ./res/bfiles.txt > ./res/afiles.txt
	./res/helper.pl ./res/afiles.txt > ./res/bfiles.txt

run:
	$(JAVA) $(JFLAGS) DrinkMixer $(ARG)

javadoc:
	$(JAVADOC) $(DOCFLAGS) $(DOCTITLE) $(PACKAGES) 
	
jar:
	$(JAR) cvf DrinkMixer.jar $(CLASS_DIR)

# Mike is the only one building with jikes right now, don't complain if this 
# doesn't work on your machine. This shouldn't break normal building with javac.
jikes:
	/usr/local/bin/jikes -deprecation -classpath /usr/local/jdk1.2.2/jre/lib/rt.jar:src: -d classes src/DrinkMixer.java

clean:
	rm classes/*.class
	rm -r classes/drinkMixer
