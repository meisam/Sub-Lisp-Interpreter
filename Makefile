# This is a very simple makefile for building the Lisp interpreter
# project when using Java on stdsun. Feel free to add any improvements:
# e.g. pattern rules, automatic tracking of dependencies, etc. There
# is a lot of info about "make" on the web.

# Tools
JAVAC = javac
RM = rm -rf
ECHO = echo
MKDIR = mkdir

# Directories
SRC_DIR = src
DIST_DIR = dist

# Java compiler flags
JAVAFLAGS = -g:none -d $(DIST_DIR) -sourcepath $(SRC_DIR) -source 1.2 -target 1.2

# Creating a .class file
COMPILE = $(JAVAC) $(JAVAFLAGS)

# One of these should be the "main" class listed in Runfile

MAIN_CLASS = src/edu/osu/cse/meisam/interpreter/Interpreter.java

# The first target is the one that is executed when you invoke
# "make". 

default: init build

init:
	$(MKDIR) $(DIST_DIR)"
 
build:
	$(COMPILE) $(MAIN_CLASS)

clean: 
	$(RM) $(DIST_DIR)
