srcdir=ui
classpath=../lib/hamcrest-core-1.3.jar:../lib/junit-4.11.jar:../lib/mysql-connector-java-5.1.18-bin.jar:.

all: clean compile test

test :
	java -cp $(classpath) ui/FenetrePrincipale

compile :
	javac -cp $(classpath) $(srcdir)/*.java

clean :
	-@rm */*.class 
