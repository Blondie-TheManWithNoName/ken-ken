all: compile run

compile: clean
	@echo "Compiling..."
	@cd src && javac -d ../out Main.java
	@mkdir -p out/META-INF
	@echo "Manifest-Version: 1.0\nMain-Class: Main" > out/META-INF/MANIFEST.MF
	@cp -r sprites out/sprites
	@echo "Done."

run:
	@echo "Running..."
	@cd out && java Main
	@echo "Program finished."

build:
	@echo "Building..."
	@cd out && jar cvfm ../../EXE/KenKen.jar META-INF/MANIFEST.MF -C . .
	@echo "Done."

clean:
	@echo "Cleaning..."
	@rm -rf out
	@echo "Done."

test: clean
ifeq ($(MAKECMDGOALS),test)
ifndef FILE
$(error FILE is not set)
else
ifeq ($(wildcard src/tests/$(FILE).java),)
$(error there is no such file src/tests/$(FILE).java)
endif
endif
endif
	@echo "Compiling $(FILE)..."
	@cd src && javac -d ../out -cp .:../lib/junit-4.13.1.jar tests/$(FILE).java
	@cp -r sprites out/sprites
	@echo "Done."
	@echo "Running test for $(FILE)...\n"
	@cd out && java -cp .:../lib/junit-4.13.1.jar:../lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore tests.$(FILE)

game: clean
ifeq ($(MAKECMDGOALS),game)
ifndef FILE
$(error FILE is not set)
else
ifeq ($(wildcard src/testgames/$(FILE).java),)
$(error there is no such file src/testgames/$(FILE).java)
endif
endif
endif
	@echo "Compiling $(FILE)..."
	@cd src && javac -d ../out testgames/$(FILE).java
	@mkdir -p out/META-INF
	@echo "Manifest-Version: 1.0\nMain-Class: testgames.$(FILE)" > out/META-INF/MANIFEST.MF
	@cp -r sprites out/sprites
	@echo "Building test game for $(FILE)...\n"
	@cd out && jar cvfm ../../EXE/testgames/$(FILE).jar META-INF/MANIFEST.MF -C . .
	@echo "Done."

h help usage:
	@echo "Usage: make [all]"
	@echo "   [all]: Compile and run the program."
	@echo "\nUsage: make [compile|run|build|clean]"
	@echo "   compile: Compile the source code."
	@echo "   run: Run the program (must be compiled first)."
	@echo "   build: Build the program into a JAR file (must be compiled first)."
	@echo "   clean: Deletes the out/ folder."
	@echo "\nUsage: make test FILE=<test_file>"
	@echo "   test: Run a test."
	@echo "\tExample: make test FILE=OperationSubtractionTest"
	@echo "\nUsage: make game FILE=<game_file>"
	@echo "   game: Builds a test game."
	@echo "\tExample: make game FILE=ProposeKenKenTestGame"
