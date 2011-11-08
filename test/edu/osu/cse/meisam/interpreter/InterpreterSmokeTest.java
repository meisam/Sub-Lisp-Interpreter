package edu.osu.cse.meisam.interpreter;

import junit.framework.TestCase;

public class InterpreterSmokeTest extends TestCase {

    public void testInterpret() {
        final Interpreter interpreter = new Interpreter(System.in, System.out);
        interpreter.interpret();
    }

}
