@echo off
set JAVABIN=c:\jdk1.2.2\bin

set JAVA=%JAVABIN%\java
set JAVAC=%JAVABIN%\javac
set JAVADOC=%JAVABIN%\javadoc
set JAR=%JAVABIN%\jar

set CLASS_DIR=.\classes
set SOURCE_DIR=.\src
set DOC_DIR=.\docs

set CP=%CLASS_DIR%

set PACKAGES=foo.model foo.model.fileParsers foo.views

set JFLAGS=-classpath %CP%
set JCFLAGS=-classpath %CP% -sourcepath %SOURCE_DIR% -d %CLASS_DIR%
set DOCFLAGS=-link http://java.sun.com/products/jdk/1.2/docs/api/ -public -d %DOC_DIR% -sourcepath %SOURCE_DIR% -classpath %CP%
set DOCTITLE=-windowtitle "Drink Mixer API Specification" -doctitle "Drink Mixer API Specification"

if %1.==. deltree /y classes
if %1.==. mkdir classes
if %1.==. %JAVAC% %JCFLAGS% DrinkMixer.java


if %1.==run. %JAVA% %JFLAGS% DrinkMixer

if %1.==javadoc. %JAVADOC% %DOCFLAGS% %DOCTITLE% %PACKAGES%
