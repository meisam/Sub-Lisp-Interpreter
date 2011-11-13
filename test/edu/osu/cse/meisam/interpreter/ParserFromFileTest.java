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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import junit.framework.Assert;

/**
 * @author Meisam Fathi Salmi <fathi@cse.ohio-state.edu>
 * 
 */
public class ParserFromFileTest extends TestStub {

    private boolean isParserPassingTestFile(final File fileName) {
        return fileName.getName().startsWith("pass-lex-parse-")
                && isTestFile(fileName);
    }

    private boolean isParserFailingTestFile(final File fileName) {
        return isTestFile(fileName)
                && (fileName.getName().startsWith("pass-lex-fail-parse-") || fileName
                        .getName().startsWith("fail-lex-"));
    }

    private Collection getParserPassingTestFiles() {
        final File[] allFiles = getFiles(TestStub.TEST_DIR);
        final Vector testFiles = new Vector(allFiles.length / 2);

        for (int i = 0; i < allFiles.length; i++) {
            if (isParserPassingTestFile(allFiles[i])) {
                testFiles.add(allFiles[i]);
            }
        }
        return testFiles;
    }

    public void testParserByPassingTests() {
        final Collection testFiles = getParserPassingTestFiles();
        for (final Iterator iterator = testFiles.iterator(); iterator.hasNext();) {
            final File testFile = (File) iterator.next();
            verifyAllTokens(testFile);
        }
    }

    private void verifyAllTokens(final File testFile) {
        try {
            final String fileContent = readfromFile(testFile);
            final InputProvider inputProvider = new StringInputProvider(
                    fileContent);
            final Lexer lexer = new Lexer(inputProvider);
            final Parser parser = new Parser(lexer);

            parser.parseNextSExpresion();
            final ParseTree parseTree = parser.getParseTree();

            final ParseTree[] infixOrderNodes = infixOrder(parseTree);
            try {

                final String pathname = getParserExpectedOutputFileName(testFile);

                final File lexerExpected = new File(pathname);
                final BufferedReader reader = new BufferedReader(
                        new FileReader(lexerExpected));
                int line = 0;
                for (int i = 0; i < infixOrderNodes.length; i++) {
                    final String nodeType = reader.readLine();
                    line++;
                    Assert.assertEquals(testFile.getName() + ":" + line,
                            nodeType, infixOrderNodes[i].getClass()
                                    .getSimpleName());

                    final String nodeVal = reader.readLine();
                    line++;
                    Assert.assertEquals(testFile.getName() + "@ Line " + line,
                            nodeVal, infixOrderNodes[i].toString());
                }
                reader.close();

            } catch (final LexerExeption ex) {
                fail("In " + testFile + ": " + ex.getMessage());
            }

        } catch (final IOException e) {
            fail(e.getMessage());
        }
    }

    private ParseTree[] infixOrder(final ParseTree parseTree) {
        if (parseTree == null) {
            return new ParseTree[0];
        } else if (parseTree instanceof InternalNode) {
            final InternalNode node = (InternalNode) parseTree;
            final ParseTree[] leftNodes = infixOrder(node.getLeftTree());
            final ParseTree[] rightNodes = infixOrder(node.getRightTree());

            final int n1 = leftNodes.length;
            final int n2 = rightNodes.length;

            final ParseTree[] allNodes = new ParseTree[n1 + n2 + 1];
            System.arraycopy(leftNodes, 0, allNodes, 0, n1);
            allNodes[n1] = parseTree;
            System.arraycopy(rightNodes, 0, allNodes, n1 + 1, n2);

            return allNodes;
        }
        final ParseTree[] singleElementArray = new ParseTree[1];
        singleElementArray[0] = parseTree;
        return singleElementArray;
    }

    private String getParserExpectedOutputFileName(final File testFile) {
        final String pathname = testFile.getAbsolutePath().replace("input",
                "parser");
        return pathname;
    }

}
