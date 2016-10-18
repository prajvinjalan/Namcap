all: classes manifest run

classes:
	javac model/*.java
	javac view/*.java

manifest: $(classes)
	echo "Main-class: view/BoardView" > Manifest.txt

run: $(classes)
	echo "#!/bin/bash" > run
	echo "java -jar view/BoardView.jar" >> run
	jar cfm view/BoardView.jar Manifest.txt view/*.class model/*.class
	chmod 754 run

clean:
	$(RM) view/*.class model/*.class run Manifest.txt view/BoardView.jar