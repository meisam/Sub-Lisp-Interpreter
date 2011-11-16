package edu.osu.cse.meisam.interpreter;

import junit.framework.TestCase;

public class InterpreterSmokeTest extends TestCase {

    private void evaluateOutputFor(final String input,
            final String expectedoutput) {
        final StringOutputReceiver actualOutput = new StringOutputReceiver();
        final StringInputProvider in = new StringInputProvider(input);
        final Interpreter interpreter = new Interpreter(in, actualOutput);
        interpreter.interpret();
        assertEquals(expectedoutput, actualOutput.getOutput());
    }

    public void testNumericAtom() {
        evaluateOutputFor("5", "5\n");
    }

    public void testSampleExpresion() {
        evaluateOutputFor("(Plus (Minus 4 10) (TIMES 4 5))", "14\n");
    }

    public void testPlusExpresion() {
        evaluateOutputFor("(Plus 3 4)", "7\n");
    }

    public void testMinusExpresion() {
        evaluateOutputFor("(mInuS 3 4)", "-1\n");
    }

    public void testRemindreExpresion() {
        evaluateOutputFor("(REMAINDER 3 4)", "3\n");
    }

    public void testQuotientExpresion() {
        evaluateOutputFor("(QUOTIENT 3 4)", "0\n");
    }

    public void testTimesExpresion() {
        evaluateOutputFor("(Times 3 4)", "12\n");
    }

    public void testEqExpresion() {
        evaluateOutputFor("(EQ 3 4)", "NIL\n");
    }

    public void testEqExpresion2() {
        evaluateOutputFor("(EQ a b)", "NIL\n");
    }

    public void testEqExpresion22() {
        evaluateOutputFor("(EQ (quote a) (quote b))", "NIL\n");
    }

    public void testEqExpresionAtomAtom() {
        evaluateOutputFor("(EQ a a)", "T\n");
    }

    public void testEqExpresionLiteralLiteral() {
        evaluateOutputFor("(EQ (quote a) (quote a))", "T\n");
    }

    public void testEqExpresionTT() {
        evaluateOutputFor("(EQ T T)", "T\n");
    }

    public void testEqExpresion5() {
        evaluateOutputFor("(EQ 5 5)", "T\n");
    }

    public void testLessExpresion() {
        evaluateOutputFor("(LESS 3 4)", "T\n");
    }

    public void testGreaterExpresion() {
        evaluateOutputFor("(Greater 3 4)", "NIL\n");
    }

    public void testTExpresion() {
        evaluateOutputFor("T", "T\n");
    }

    public void testComplicatedExpresion() {
        evaluateOutputFor(
                "(GREATER (PLUS 4 5) (MINUS (TIMES 100 10) (REMAINDER 10 7)))",
                "NIL\n");
    }

    public void testAtomExpresion() {
        evaluateOutputFor("(ATOM 5)", "T\n");
    }

    public void testNullExpresion() {
        evaluateOutputFor("(NULL (GREATER 4 1))", "NIL\n");
    }

    public void testIntExpresion() {
        evaluateOutputFor("(Int (PLUS 4 1))", "T\n");
    }

    public void testNilExpresion() {
        evaluateOutputFor("NIL", "NIL\n");
    }

    public void testCondExpresion() {
        evaluateOutputFor("(COND ((LESS 4 3) 2) ((LESS 5 7) 9))", "9\n");
    }

    public void testQouteExpresion() {
        evaluateOutputFor("(Quote (1 2))", "(1 2)\n");
    }

    public void testQouteExpresion2() {
        evaluateOutputFor("(Quote (1 . 2))", "(1 . 2)\n");
    }

    public void testQouteAtomExpresion() {
        evaluateOutputFor("(Quote 1)", "1\n");
    }

    public void testConsExpresion() {
        evaluateOutputFor("(Cons 3 (Cons 5 7))", "(3 . (5 . 7))\n");
    }

    public void testDefunExpresion() {
        evaluateOutputFor("(Defun x (a b) (PLUS 5 4))", "X\n");
    }

    public void testApplyFunExpresion() {
        evaluateOutputFor("(Defun x (a b) (PLUS a 4)) (x 6 7)", "X\n10\n");
    }

    public void testDefineRecursiveExpresion() {
        evaluateOutputFor(
                "(DEFUN NOTSOSILLY (A B)                                              "
                        + "    (COND                                                            "
                        + "            ((EQ A 0) (PLUS B 1))                                    "
                        + "            ((EQ B 0) (NOTSOSILLY (MINUS A 1) 1))                    "
                        + "            (T (NOTSOSILLY (MINUS A 1) (NOTSOSILLY A (MINUS B 1))))  "
                        + "          ))                                                         "
                        + "(NOTSOSILLY 2 4)                                                     "
                        + "(NOTSOSILLY 3 5)                                                     "
                        + "(NOTSOSILLY 1 1)"
                //
                ,
                //
                "NOTSOSILLY\n" + "11\n" + "253\n" + "3\n");
    }

    public void FIXMEtestInterpret() { // FIXME
        final Interpreter interpreter = new Interpreter(
                new InputStreamProvider(System.in), new StreamOutputReceiver(
                        System.out));
        interpreter.interpret();
    }

}
