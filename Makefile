# This is a very simple makefile for building the Lisp interpreter
# project when using Java on stdsun. Feel free to add any improvements:
# e.g. pattern rules, automatic tracking of dependencies, etc. There
# is a lot of info about "make" on the web.

# Tools
JAVAC = javac
JAR = jar
RM = rm -rf
ECHO = echo
MKDIR = mkdir -p
MV = mv

# Directories
SRC_DIR = src
BIN_DIR = bin
DIST_DIR = dist

# Java compiler flags
JAVAFLAGS = -g -d $(BIN_DIR) -sourcepath $(SRC_DIR) -source 1.2 -target 1.2
JAVA_DEBUG_FLAGS = -g -d $(BIN_DIR) -sourcepath $(SRC_DIR) -source 1.2 -target 1.2

# Manifest file
MANIFEST_FILE = manifest

# Deploy name
DEPLOY_NAME = intrpreter.jar

# Jar flags
JARFLAGS = cvfm $(DEPLOY_NAME) $(MANIFEST_FILE) -C $(BIN_DIR) .

# Creating a .class file
COMPILE = $(JAVAC) $(JAVAFLAGS)
COMPILE_TO_DEBUG = $(JAVAC) $(JAVAFLAGS)

# Creating a .jar file
ARCHIVE = $(JAR) $(JARFLAGS)

# One of these should be the "main" class listed in Runfile

MAIN_CLASS = src/edu/osu/cse/meisam/interpreter/Interpreter.java

# The first target is the one that is executed when you invoke
# "make". 

default: deploy

init:
	$(ECHO) "Creating BIN directory"
	$(MKDIR) $(BIN_DIR)
	$(ECHO) "Creating DIST directory"
	$(MKDIR) $(DIST_DIR)
	$(ECHO) "Init finished"
 
build: init
	$(ECHO) "Going to build the project"
	$(ECHO) "Compiling..."
	$(COMPILE) $(MAIN_CLASS)
	$(ECHO) "Build finished"

debug: init
	$(ECHO) "Going to build the project"
	$(ECHO) "Compiling..."
	$(COMPILE_TO_DEBUG) $(MAIN_CLASS)
	$(ECHO) "Build finished"

deploy: build
	$(ECHO) "Going to deploying the project into a jar file"
	$(ARCHIVE)
	$(ECHO) "Moving the deployed jar file to the DIST directory"
	$(MV) $(DEPLOY_NAME) $(DIST_DIR)
	$(ECHO) "Finished deploying the jar file"

clean: 
	$(ECHO) "Going to clean up..."
	$(RM) $(BIN_DIR)
	$(RM) $(DIST_DIR)
	$(ECHO) "Clean up finished"
	