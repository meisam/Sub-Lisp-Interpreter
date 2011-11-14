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
import edu.osu.cse.meisam.interpreter.tokens.LiteralAtom;
import edu.osu.cse.meisam.interpreter.tokens.NumericAtom;
import edu.osu.cse.meisam.interpreter.tokens.Token;

/**
 * @author Meisam Fathi Salmi <fathi@cse.ohio-state.edu>
 * 
 */
public class ParserTest extends TestCase {

    public void testSmokeParser() {
        final String input = "(PluS 5 4)";
        final InputProvider inputProvider = new StringInputProvider(input);
        final Lexer lexer = new Lexer(inputProvider);
        final Parser parser = new Parser(lexer);

        final ParseTree parseTree = parser.parseNextSExpresion();

        Assert.assertNotNull(parseTree);

        Assert.assertTrue(parseTree instanceof InternalNode);
        final InternalNode internalNode = (InternalNode) parseTree;

        final ParseTree leftTree = internalNode.getLeftTree();
        Assert.assertNotNull(leftTree);
        Assert.assertTrue(leftTree instanceof LeafNode);
        final Token token = ((LeafNode) leftTree).getToken();
        Assert.assertNotNull(token);
        Assert.assertEquals("PLUS", token.getLexval());

        final ParseTree rightTree = internalNode.getRightTree();
        Assert.assertNotNull(rightTree);
        Assert.assertTrue(rightTree instanceof InternalNode);

        final ParseTree leftTree_5 = ((InternalNode) rightTree).getLeftTree();
        Assert.assertTrue(leftTree_5 instanceof LeafNode);
        final Token token_5 = ((LeafNode) leftTree_5).getToken();
        Assert.assertTrue(token_5 instanceof NumericAtom);
        Assert.assertEquals(token_5.getLexval(), "5");

        final ParseTree rightTree_4 = ((InternalNode) rightTree).getRightTree();
        Assert.assertNotNull(rightTree_4);
        Assert.assertTrue(rightTree_4 instanceof InternalNode);

        final ParseTree leftTree_4 = ((InternalNode) rightTree_4).getLeftTree();
        Assert.assertTrue(leftTree_4 instanceof LeafNode);
        final Token token_4 = ((LeafNode) leftTree_4).getToken();
        Assert.assertTrue(token_4 instanceof NumericAtom);
        Assert.assertEquals(token_4.getLexval(), "4");

        final ParseTree rightTree_nill = ((InternalNode) rightTree_4)
                .getRightTree();
        Assert.assertNotNull(rightTree_nill);
        Assert.assertEquals(InternalNode.NILL_LEAF, rightTree_nill);

    }

    public void testSmoke2Parser() {
        final String input = "(ConS 5 (4 3))";
        final InputProvider inputProvider = new StringInputProvider(input);
        final Lexer lexer = new Lexer(inputProvider);
        final Parser parser = new Parser(lexer);

        final ParseTree actualParseTree = parser.parseNextSExpresion();

        final ParseTree expectedParseTree = new InternalNode( //
                new LeafNode(new LiteralAtom("cons")), //
                new InternalNode( //
                        new LeafNode(new NumericAtom("5")),//
                        new InternalNode(//
                                new LeafNode(new NumericAtom("4")),//
                                new LeafNode(new NumericAtom("3")),//
                                false),//
                        false), //
                false//
        );
        assertEquals(expectedParseTree, actualParseTree);

    }

    public void testSmokeParserDot() {
        final String input = "(4 . 3)";
        final InputProvider inputProvider = new StringInputProvider(input);
        final Lexer lexer = new Lexer(inputProvider);
        final Parser parser = new Parser(lexer);

        final ParseTree actualParseTree = parser.parseNextSExpresion();

        final ParseTree expectedParseTree = new InternalNode(//
                new LeafNode(new NumericAtom("4")), //
                new LeafNode(new NumericAtom("3")), //
                true //
        );
        assertEquals(expectedParseTree, actualParseTree);
    }

}
