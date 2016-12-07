all: classes manifest run

classes:
	javac controller/*.java
	javac model/*.java
	javac view/*.java
	javac testing/FunctionalTests.java

manifest: $(classes)
	echo "Main-class: model/Namcap" > Manifest.txt

run: $(classes)
	echo "#!/bin/bash" > run
	echo "java -jar model/Namcap.jar" >> run
	jar cfm model/Namcap.jar Manifest.txt view/*.class model/*.class controller/*.class assets
	chmod 754 run

clean:
	$(RM) view/*.class model/*.class controller/*.class testing/*.class run Manifest.txt model/Namcap.jar high_score
