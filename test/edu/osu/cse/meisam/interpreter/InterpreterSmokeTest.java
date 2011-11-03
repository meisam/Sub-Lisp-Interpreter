package edu.osu.cse.meisam.interpreter;

import static org.junit.Assert.*;

import org.junit.Test;

public class InterpreterSmokeTest {

    @Test
    public void testInterpret() {
        Interpreter interpreter = new Interpreter(System.in, System.out);
        interpreter.interpret();
    }

}
