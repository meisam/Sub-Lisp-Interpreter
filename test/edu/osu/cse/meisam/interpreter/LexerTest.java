/**
 * Lisp Subinterpreter, an interpreter for a sublanguage of Lisp
 * Copyright (C) 2011  Meisam Fathi Salmi <fathi@cse.ohio-state.edu>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package edu.osu.cse.meisam.interpreter;

import junit.framework.Assert;
import junit.framework.TestCase;
import edu.osu.cse.meisam.interpreter.tokens.LispCloseParentheses;
import edu.osu.cse.meisam.interpreter.tokens.LispDot;
import edu.osu.cse.meisam.interpreter.tokens.LispEOF;
import edu.osu.cse.meisam.interpreter.tokens.LispLiteralAtom;
import edu.osu.cse.meisam.interpreter.tokens.LispNumericAtom;
import edu.osu.cse.meisam.interpreter.tokens.LispOpenParentheses;
import edu.osu.cse.meisam.interpreter.tokens.LispToken;

/**
 * @author Meisam Fathi Salmi <fathi@cse.ohio-state.edu>
 * 
 */
public class LexerTest extends TestCase {

    /**
     * Test method for {@link edu.osu.cse.meisam.interpreter.Lexer#nextToken()}.
     */
    public void testNexTokenWithEmptyInput() {
        InputProvider inputProvider = new StringInputProvider("");
        Lexer lexer = new Lexer(inputProvider);

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNexTokenWithWhiteSpaceInput() {
        InputProvider inputProvider = new StringInputProvider(" ");
        Lexer lexer = new Lexer(inputProvider);

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNexTokenWithtLongWhiteSpaceInput() {
        InputProvider inputProvider = new StringInputProvider(
                "   \t\n  \r \n\r");
        Lexer lexer = new Lexer(inputProvider);

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithSingleDigit() {
        InputProvider inputProvider = new StringInputProvider("1");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispNumericAtom);
        assertEquals("1", first.getLexval());
        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithSingleNumber() {
        InputProvider inputProvider = new StringInputProvider("000");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispNumericAtom);
        assertEquals("000", first.getLexval());
        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithSingleNumberEndWhiteSpace() {
        InputProvider inputProvider = new StringInputProvider("1234  ");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispNumericAtom);
        assertEquals("1234", first.getLexval());
        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithSingleNumberStartWhiteSpace() {
        InputProvider inputProvider = new StringInputProvider("  1234");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispNumericAtom);
        assertEquals("1234", first.getLexval());
        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithDoubleId() {
        InputProvider inputProvider = new StringInputProvider("1234 1");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispNumericAtom);
        assertEquals("1234", first.getLexval());

        LispToken second = lexer.nextToken();
        Assert.assertTrue(second instanceof LispNumericAtom);
        assertEquals("1", second.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithSingleNegativeDigit() {
        InputProvider inputProvider = new StringInputProvider("-1");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispNumericAtom);
        assertEquals("-1", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithSinglePositiveDigit() {
        InputProvider inputProvider = new StringInputProvider("+1");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispNumericAtom);
        assertEquals("1", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithPositiveNumber() {
        InputProvider inputProvider = new StringInputProvider("+1234");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispNumericAtom);
        assertEquals("1234", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithNegativeNumber() {
        InputProvider inputProvider = new StringInputProvider("-1234");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispNumericAtom);
        assertEquals("-1234", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithSpacePositiveNumber() {
        InputProvider inputProvider = new StringInputProvider(" +1234");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispNumericAtom);
        assertEquals("1234", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithSpaceNegativeNumber() {
        InputProvider inputProvider = new StringInputProvider(" -1234");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispNumericAtom);
        assertEquals("-1234", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithMultiSpacePositiveNumber() {
        InputProvider inputProvider = new StringInputProvider(" \t\n\r +1234");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispNumericAtom);
        assertEquals("1234", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithMultiSpaceNegativeNumber() {
        InputProvider inputProvider = new StringInputProvider(" \t\n\r -1234");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispNumericAtom);
        assertEquals("-1234", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithTwoSignedNumber() {
        InputProvider inputProvider = new StringInputProvider(
                " \t\n\r -1234   \t\n\r  +999");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispNumericAtom);
        assertEquals("-1234", first.getLexval());

        LispToken second = lexer.nextToken();
        Assert.assertTrue(second instanceof LispNumericAtom);
        assertEquals("999", second.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithTwoNumber() {
        InputProvider inputProvider = new StringInputProvider(
                " \t\n\r -1234   \t\n\r  999");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispNumericAtom);
        assertEquals("-1234", first.getLexval());

        LispToken second = lexer.nextToken();
        Assert.assertTrue(second instanceof LispNumericAtom);
        assertEquals("999", second.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithSingleLetter() {
        InputProvider inputProvider = new StringInputProvider("A");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispLiteralAtom);
        assertEquals("A", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithSingleId() {
        InputProvider inputProvider = new StringInputProvider("ABC");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispLiteralAtom);
        assertEquals("ABC", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithSingleIdEndWhiteSpace() {
        InputProvider inputProvider = new StringInputProvider("A  ");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispLiteralAtom);
        assertEquals("A", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithSingleIdStartWhiteSpace() {
        InputProvider inputProvider = new StringInputProvider("  ABC");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispLiteralAtom);
        assertEquals("ABC", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenWithDoubleNumbers() {
        InputProvider inputProvider = new StringInputProvider("ABC A");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispLiteralAtom);
        assertEquals("ABC", first.getLexval());

        LispToken second = lexer.nextToken();
        Assert.assertTrue(second instanceof LispLiteralAtom);
        assertEquals("A", second.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenInvalidNumberId() {
        InputProvider inputProvider = new StringInputProvider("42A");
        Lexer lexer = new Lexer(inputProvider);

        try {
            lexer.nextToken();
        } catch (LexerExeption e) {
            Assert.assertTrue(e.getMessage().contains("42A"));
        }
        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenInvalidIdNumber() {
        InputProvider inputProvider = new StringInputProvider("A41");
        Lexer lexer = new Lexer(inputProvider);

        try {
            lexer.nextToken();
        } catch (LexerExeption e) {
            Assert.assertTrue(e.getMessage().contains("A4"));
        }
    }

    public void testNextTokenInvalidIdNumberSpaced() {
        InputProvider inputProvider = new StringInputProvider("     A41     ");
        Lexer lexer = new Lexer(inputProvider);

        try {
            lexer.nextToken();
        } catch (LexerExeption e) {
            Assert.assertTrue(e.getMessage().contains("A4"));
        }
    }

    public void testNextTokenIdNumber() {
        InputProvider inputProvider = new StringInputProvider("  A   1  ");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispLiteralAtom);
        assertEquals("A", first.getLexval());

        LispToken second = lexer.nextToken();
        Assert.assertTrue(second instanceof LispNumericAtom);
        assertEquals("1", second.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenNumberId() {
        InputProvider inputProvider = new StringInputProvider("  1   A  ");
        Lexer lexer = new Lexer(inputProvider);

        LispToken first = lexer.nextToken();
        Assert.assertTrue(first instanceof LispNumericAtom);
        assertEquals("1", first.getLexval());

        LispToken second = lexer.nextToken();
        Assert.assertTrue(second instanceof LispLiteralAtom);
        assertEquals("A", second.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenIdNumberNTimes() {
        InputProvider inputProvider = new StringInputProvider(
                "  A   1 AAA 123 CCC BBB 111 000  ");
        Lexer lexer = new Lexer(inputProvider);

        LispToken currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispLiteralAtom);
        assertEquals("A", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispNumericAtom);
        assertEquals("1", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispLiteralAtom);
        assertEquals("AAA", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispNumericAtom);
        assertEquals("123", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispLiteralAtom);
        assertEquals("CCC", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispLiteralAtom);
        assertEquals("BBB", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispNumericAtom);
        assertEquals("111", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispNumericAtom);
        assertEquals("000", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenPlusOperation() {
        InputProvider inputProvider = new StringInputProvider("+");
        Lexer lexer = new Lexer(inputProvider);
        try {
            lexer.nextToken();
            Assert.fail("Lexer should have reported error");
        } catch (LexerExeption e) {
        }
    }

    public void testNextTokenPlusOperationSpace() {
        InputProvider inputProvider = new StringInputProvider("+ ");
        Lexer lexer = new Lexer(inputProvider);
        try {
            lexer.nextToken();
            Assert.fail("Lexer should have reported error");
        } catch (LexerExeption e) {
        }
    }

    public void testNextTokenPlusOperationSpaces() {
        InputProvider inputProvider = new StringInputProvider("+ \n\r\t");
        Lexer lexer = new Lexer(inputProvider);
        try {
            lexer.nextToken();
            Assert.fail("Lexer should have reported error");
        } catch (LexerExeption e) {
        }
    }

    public void testNextTokenSpacePlusOperation() {
        InputProvider inputProvider = new StringInputProvider(" +");
        Lexer lexer = new Lexer(inputProvider);
        try {
            lexer.nextToken();
            Assert.fail("Lexer should have reported error");
        } catch (LexerExeption e) {
        }
    }

    public void testNextTokenOpenParentheses() {
        InputProvider inputProvider = new StringInputProvider("(");
        Lexer lexer = new Lexer(inputProvider);

        LispToken currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispOpenParentheses);
        assertEquals("(", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenCloseParentheses() {
        InputProvider inputProvider = new StringInputProvider(")");
        Lexer lexer = new Lexer(inputProvider);

        LispToken currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispCloseParentheses);
        assertEquals(")", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenOpenParenthesesSpace() {
        InputProvider inputProvider = new StringInputProvider("( ");
        Lexer lexer = new Lexer(inputProvider);

        LispToken currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispOpenParentheses);
        assertEquals("(", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenCloseParenthesesSpace() {
        InputProvider inputProvider = new StringInputProvider(") ");
        Lexer lexer = new Lexer(inputProvider);

        LispToken currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispCloseParentheses);
        assertEquals(")", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenOpenParenthesesSpaces() {
        InputProvider inputProvider = new StringInputProvider("( \t\n\r");
        Lexer lexer = new Lexer(inputProvider);

        LispToken currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispOpenParentheses);
        assertEquals("(", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenCloseParenthesesSpaces() {
        InputProvider inputProvider = new StringInputProvider(") \t\n\r");
        Lexer lexer = new Lexer(inputProvider);

        LispToken currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispCloseParentheses);
        assertEquals(")", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenSpacesOpenParenthesesSpaces() {
        InputProvider inputProvider = new StringInputProvider(
                " \t\n\r ( \t\n\r");
        Lexer lexer = new Lexer(inputProvider);

        LispToken currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispOpenParentheses);
        assertEquals("(", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenSpacesCloseParenthesesSpaces() {
        InputProvider inputProvider = new StringInputProvider(
                " \t\n\r ) \t\n\r");
        Lexer lexer = new Lexer(inputProvider);

        LispToken currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispCloseParentheses);
        assertEquals(")", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenOpenParenthesesTwo() {
        InputProvider inputProvider = new StringInputProvider("((");
        Lexer lexer = new Lexer(inputProvider);

        LispToken currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispOpenParentheses);
        assertEquals("(", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispOpenParentheses);
        assertEquals("(", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenCloseParenthesesTwo() {
        InputProvider inputProvider = new StringInputProvider("))");
        Lexer lexer = new Lexer(inputProvider);

        LispToken currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispCloseParentheses);
        assertEquals(")", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispCloseParentheses);
        assertEquals(")", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenDot() {
        InputProvider inputProvider = new StringInputProvider(".");
        Lexer lexer = new Lexer(inputProvider);

        LispToken currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispDot);
        assertEquals(".", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenDotSpace() {
        InputProvider inputProvider = new StringInputProvider(". ");
        Lexer lexer = new Lexer(inputProvider);
        LispToken currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispDot);
        assertEquals(".", currentToken.getLexval());
        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenDotMultiSpace() {
        InputProvider inputProvider = new StringInputProvider(". \t\n");
        Lexer lexer = new Lexer(inputProvider);

        LispToken currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispDot);
        assertEquals(".", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenMultiSpaceDot() {
        InputProvider inputProvider = new StringInputProvider(" \t\n .");
        Lexer lexer = new Lexer(inputProvider);

        LispToken currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispDot);
        assertEquals(".", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenMultiSpaceDotMultiSpace() {
        InputProvider inputProvider = new StringInputProvider(" \t\n .  \t\n\r");
        Lexer lexer = new Lexer(inputProvider);

        LispToken currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispDot);
        assertEquals(".", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }

    public void testNextTokenMultiSpaceDotMultiSpaceDot() {
        InputProvider inputProvider = new StringInputProvider(
                " \t\n .  \t\n\r . \t\r\n");
        Lexer lexer = new Lexer(inputProvider);

        LispToken currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispDot);
        assertEquals(".", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LispDot);
        assertEquals(".", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof LispEOF);
    }
}
