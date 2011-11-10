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
import edu.osu.cse.meisam.interpreter.tokens.CloseParentheses;
import edu.osu.cse.meisam.interpreter.tokens.Dot;
import edu.osu.cse.meisam.interpreter.tokens.EOF;
import edu.osu.cse.meisam.interpreter.tokens.LiteralAtom;
import edu.osu.cse.meisam.interpreter.tokens.NumericAtom;
import edu.osu.cse.meisam.interpreter.tokens.OpenParentheses;
import edu.osu.cse.meisam.interpreter.tokens.Token;

/**
 * @author Meisam Fathi Salmi <fathi@cse.ohio-state.edu>
 * 
 */
public class LexerTest extends TestCase {

    /**
     * Test method for {@link edu.osu.cse.meisam.interpreter.Lexer#nextToken()}.
     */
    public void testNexTokenWithEmptyInput() {
        final InputProvider inputProvider = new StringInputProvider("");
        final Lexer lexer = new Lexer(inputProvider);

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNexTokenWithWhiteSpaceInput() {
        final InputProvider inputProvider = new StringInputProvider(" ");
        final Lexer lexer = new Lexer(inputProvider);

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNexTokenWithtLongWhiteSpaceInput() {
        final InputProvider inputProvider = new StringInputProvider(
                "   \t\n  \r \n\r");
        final Lexer lexer = new Lexer(inputProvider);

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithSingleDigit() {
        final InputProvider inputProvider = new StringInputProvider("1");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof NumericAtom);
        assertEquals("1", first.getLexval());
        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithSingleNumber() {
        final InputProvider inputProvider = new StringInputProvider("000");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof NumericAtom);
        assertEquals("000", first.getLexval());
        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithSingleNumberEndWhiteSpace() {
        final InputProvider inputProvider = new StringInputProvider("1234  ");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof NumericAtom);
        assertEquals("1234", first.getLexval());
        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithSingleNumberStartWhiteSpace() {
        final InputProvider inputProvider = new StringInputProvider("  1234");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof NumericAtom);
        assertEquals("1234", first.getLexval());
        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithDoubleId() {
        final InputProvider inputProvider = new StringInputProvider("1234 1");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof NumericAtom);
        assertEquals("1234", first.getLexval());

        final Token second = lexer.nextToken();
        Assert.assertTrue(second instanceof NumericAtom);
        assertEquals("1", second.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithSingleNegativeDigit() {
        final InputProvider inputProvider = new StringInputProvider("-1");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof NumericAtom);
        assertEquals("-1", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithSinglePositiveDigit() {
        final InputProvider inputProvider = new StringInputProvider("+1");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof NumericAtom);
        assertEquals("1", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithPositiveNumber() {
        final InputProvider inputProvider = new StringInputProvider("+1234");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof NumericAtom);
        assertEquals("1234", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithNegativeNumber() {
        final InputProvider inputProvider = new StringInputProvider("-1234");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof NumericAtom);
        assertEquals("-1234", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithSpacePositiveNumber() {
        final InputProvider inputProvider = new StringInputProvider(" +1234");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof NumericAtom);
        assertEquals("1234", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithSpaceNegativeNumber() {
        final InputProvider inputProvider = new StringInputProvider(" -1234");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof NumericAtom);
        assertEquals("-1234", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithMultiSpacePositiveNumber() {
        final InputProvider inputProvider = new StringInputProvider(
                " \t\n\r +1234");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof NumericAtom);
        assertEquals("1234", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithMultiSpaceNegativeNumber() {
        final InputProvider inputProvider = new StringInputProvider(
                " \t\n\r -1234");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof NumericAtom);
        assertEquals("-1234", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithTwoSignedNumber() {
        final InputProvider inputProvider = new StringInputProvider(
                " \t\n\r -1234   \t\n\r  +999");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof NumericAtom);
        assertEquals("-1234", first.getLexval());

        final Token second = lexer.nextToken();
        Assert.assertTrue(second instanceof NumericAtom);
        assertEquals("999", second.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithTwoNumber() {
        final InputProvider inputProvider = new StringInputProvider(
                " \t\n\r -1234   \t\n\r  999");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof NumericAtom);
        assertEquals("-1234", first.getLexval());

        final Token second = lexer.nextToken();
        Assert.assertTrue(second instanceof NumericAtom);
        assertEquals("999", second.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithSingleLetter() {
        final InputProvider inputProvider = new StringInputProvider("A");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof LiteralAtom);
        assertEquals("A", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithSingleId() {
        final InputProvider inputProvider = new StringInputProvider("ABC");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof LiteralAtom);
        assertEquals("ABC", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithSingleIdEndWhiteSpace() {
        final InputProvider inputProvider = new StringInputProvider("A  ");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof LiteralAtom);
        assertEquals("A", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithSingleIdStartWhiteSpace() {
        final InputProvider inputProvider = new StringInputProvider("  ABC");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof LiteralAtom);
        assertEquals("ABC", first.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenWithDoubleNumbers() {
        final InputProvider inputProvider = new StringInputProvider("ABC A");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof LiteralAtom);
        assertEquals("ABC", first.getLexval());

        final Token second = lexer.nextToken();
        Assert.assertTrue(second instanceof LiteralAtom);
        assertEquals("A", second.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenInvalidNumberId() {
        final InputProvider inputProvider = new StringInputProvider("42A");
        final Lexer lexer = new Lexer(inputProvider);

        try {
            lexer.nextToken();
        } catch (final LexcerExeption e) {
            Assert.assertTrue(e.getMessage().contains("42A"));
        }
    }

    public void testNextTokenInvalidIdNumber() {
        final InputProvider inputProvider = new StringInputProvider("A41");
        final Lexer lexer = new Lexer(inputProvider);

        try {
            lexer.nextToken();
        } catch (final LexcerExeption e) {
            Assert.assertTrue(e.getMessage().contains("A4"));
        }
    }

    public void testNextTokenInvalidIdNumberSpaced() {
        final InputProvider inputProvider = new StringInputProvider(
                "     A41     ");
        final Lexer lexer = new Lexer(inputProvider);

        try {
            lexer.nextToken();
        } catch (final LexcerExeption e) {
            Assert.assertTrue(e.getMessage().contains("A4"));
        }
    }

    public void testNextTokenIdNumber() {
        final InputProvider inputProvider = new StringInputProvider("  A   1  ");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof LiteralAtom);
        assertEquals("A", first.getLexval());

        final Token second = lexer.nextToken();
        Assert.assertTrue(second instanceof NumericAtom);
        assertEquals("1", second.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenNumberId() {
        final InputProvider inputProvider = new StringInputProvider("  1   A  ");
        final Lexer lexer = new Lexer(inputProvider);

        final Token first = lexer.nextToken();
        Assert.assertTrue(first instanceof NumericAtom);
        assertEquals("1", first.getLexval());

        final Token second = lexer.nextToken();
        Assert.assertTrue(second instanceof LiteralAtom);
        assertEquals("A", second.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenIdNumberNTimes() {
        final InputProvider inputProvider = new StringInputProvider(
                "  A   1 AAA 123 CCC BBB 111 000  ");
        final Lexer lexer = new Lexer(inputProvider);

        Token currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LiteralAtom);
        assertEquals("A", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof NumericAtom);
        assertEquals("1", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LiteralAtom);
        assertEquals("AAA", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof NumericAtom);
        assertEquals("123", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LiteralAtom);
        assertEquals("CCC", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof LiteralAtom);
        assertEquals("BBB", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof NumericAtom);
        assertEquals("111", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof NumericAtom);
        assertEquals("000", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenPlusOperation() {
        final InputProvider inputProvider = new StringInputProvider("+");
        final Lexer lexer = new Lexer(inputProvider);
        try {
            lexer.nextToken();
            Assert.fail("Lexer should have reported error");
        } catch (final LexcerExeption e) {
        }
    }

    public void testNextTokenPlusOperationSpace() {
        final InputProvider inputProvider = new StringInputProvider("+ ");
        final Lexer lexer = new Lexer(inputProvider);
        try {
            lexer.nextToken();
            Assert.fail("Lexer should have reported error");
        } catch (final LexcerExeption e) {
        }
    }

    public void testNextTokenPlusOperationSpaces() {
        final InputProvider inputProvider = new StringInputProvider("+ \n\r\t");
        final Lexer lexer = new Lexer(inputProvider);
        try {
            lexer.nextToken();
            Assert.fail("Lexer should have reported error");
        } catch (final LexcerExeption e) {
        }
    }

    public void testNextTokenSpacePlusOperation() {
        final InputProvider inputProvider = new StringInputProvider(" +");
        final Lexer lexer = new Lexer(inputProvider);
        try {
            lexer.nextToken();
            Assert.fail("Lexer should have reported error");
        } catch (final LexcerExeption e) {
        }
    }

    public void testNextTokenOpenParentheses() {
        final InputProvider inputProvider = new StringInputProvider("(");
        final Lexer lexer = new Lexer(inputProvider);

        final Token currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof OpenParentheses);
        assertEquals("(", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenCloseParentheses() {
        final InputProvider inputProvider = new StringInputProvider(")");
        final Lexer lexer = new Lexer(inputProvider);

        final Token currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof CloseParentheses);
        assertEquals(")", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenOpenParenthesesSpace() {
        final InputProvider inputProvider = new StringInputProvider("( ");
        final Lexer lexer = new Lexer(inputProvider);

        final Token currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof OpenParentheses);
        assertEquals("(", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenCloseParenthesesSpace() {
        final InputProvider inputProvider = new StringInputProvider(") ");
        final Lexer lexer = new Lexer(inputProvider);

        final Token currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof CloseParentheses);
        assertEquals(")", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenOpenParenthesesSpaces() {
        final InputProvider inputProvider = new StringInputProvider("( \t\n\r");
        final Lexer lexer = new Lexer(inputProvider);

        final Token currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof OpenParentheses);
        assertEquals("(", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenCloseParenthesesSpaces() {
        final InputProvider inputProvider = new StringInputProvider(") \t\n\r");
        final Lexer lexer = new Lexer(inputProvider);

        final Token currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof CloseParentheses);
        assertEquals(")", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenSpacesOpenParenthesesSpaces() {
        final InputProvider inputProvider = new StringInputProvider(
                " \t\n\r ( \t\n\r");
        final Lexer lexer = new Lexer(inputProvider);

        final Token currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof OpenParentheses);
        assertEquals("(", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenSpacesCloseParenthesesSpaces() {
        final InputProvider inputProvider = new StringInputProvider(
                " \t\n\r ) \t\n\r");
        final Lexer lexer = new Lexer(inputProvider);

        final Token currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof CloseParentheses);
        assertEquals(")", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenOpenParenthesesTwo() {
        final InputProvider inputProvider = new StringInputProvider("((");
        final Lexer lexer = new Lexer(inputProvider);

        Token currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof OpenParentheses);
        assertEquals("(", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof OpenParentheses);
        assertEquals("(", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenCloseParenthesesTwo() {
        final InputProvider inputProvider = new StringInputProvider("))");
        final Lexer lexer = new Lexer(inputProvider);

        Token currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof CloseParentheses);
        assertEquals(")", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof CloseParentheses);
        assertEquals(")", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenDot() {
        final InputProvider inputProvider = new StringInputProvider(".");
        final Lexer lexer = new Lexer(inputProvider);

        final Token currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof Dot);
        assertEquals(".", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenDotSpace() {
        final InputProvider inputProvider = new StringInputProvider(". ");
        final Lexer lexer = new Lexer(inputProvider);
        final Token currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof Dot);
        assertEquals(".", currentToken.getLexval());
        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenDotMultiSpace() {
        final InputProvider inputProvider = new StringInputProvider(". \t\n");
        final Lexer lexer = new Lexer(inputProvider);

        final Token currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof Dot);
        assertEquals(".", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenMultiSpaceDot() {
        final InputProvider inputProvider = new StringInputProvider(" \t\n .");
        final Lexer lexer = new Lexer(inputProvider);

        final Token currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof Dot);
        assertEquals(".", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenMultiSpaceDotMultiSpace() {
        final InputProvider inputProvider = new StringInputProvider(
                " \t\n .  \t\n\r");
        final Lexer lexer = new Lexer(inputProvider);

        final Token currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof Dot);
        assertEquals(".", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }

    public void testNextTokenMultiSpaceDotMultiSpaceDot() {
        final InputProvider inputProvider = new StringInputProvider(
                " \t\n .  \t\n\r . \t\r\n");
        final Lexer lexer = new Lexer(inputProvider);

        Token currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof Dot);
        assertEquals(".", currentToken.getLexval());

        currentToken = lexer.nextToken();
        Assert.assertTrue(currentToken instanceof Dot);
        assertEquals(".", currentToken.getLexval());

        Assert.assertTrue(lexer.nextToken() instanceof EOF);
    }
}
