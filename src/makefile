srcdir=ui
classpath=../lib/hamcrest-core-1.3.jar:../lib/junit-4.11.jar:../lib/mysql-connector-java-5.1.18-bin.jar:../lib/commons-codec-1.8.jar:.

all: clean compile

junit :
	java -cp $(classpath) org.junit.runner.JUnitCore test.TestMysqlDao

test :
	java -cp $(classpath) ui/FenetrePrincipale

compile :
	javac -cp $(classpath) $(srcdir)/*.java
	javac -cp $(classpath) */*.java

clean :
	-@rm */*.class 
