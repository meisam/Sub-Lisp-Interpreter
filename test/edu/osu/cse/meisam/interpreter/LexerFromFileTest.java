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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import junit.framework.TestCase;
import edu.osu.cse.meisam.interpreter.tokens.LispEOF;
import edu.osu.cse.meisam.interpreter.tokens.LispToken;

/**
 * This class provides a framework for testing the program by reading the input
 * from test files
 * 
 * @author Meisam Fathi Salmi <fathi@cse.ohio-state.edu>
 * 
 */
public class LexerFromFileTest extends TestCase {

    private static final int DEFAULT_BUFFER_SIZE = 1000;

    private static final String TEST_DIR = "testfiles";

    private String[] getFiles(final String dir) {
        final File directory = new File(dir);
        return directory.list();
    }

    protected Collection getTestFiles() {
        final String[] allFiles = getFiles(LexerFromFileTest.TEST_DIR);
        final Vector testFiles = new Vector(allFiles.length / 2);

        for (int i = 0; i < allFiles.length; i++) {
            if (isTestFile(allFiles[i])) {
                testFiles.add(allFiles[i]);
            }
        }
        return testFiles;
    }

    private boolean isLexerExpectedFile(final String fileName) {
        return fileName.endsWith(".lexer");
    }

    private boolean isTestFile(final String fileName) {
        return fileName.endsWith(".input");
    }

    protected String readfromFile(final String fileName) throws IOException {
        final Reader input = new FileReader(fileName);
        final StringBuffer fileContent = new StringBuffer(
                LexerFromFileTest.DEFAULT_BUFFER_SIZE);

        final char[] buffer = new char[LexerFromFileTest.DEFAULT_BUFFER_SIZE];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            fileContent.append(buffer, 0, n);
        }
        return fileContent.toString();
    }

    public void testSmokeListTestFile() {
        String s;
        try {
            s = readfromFile("testfiles/test1.input");
            System.out.print(s);
        } catch (final IOException e) {
            fail(e.getMessage());
        }
    }

    public void testSmokeReadFile() {
        try {
            final Collection testFiles = getTestFiles();
            for (final Iterator iterator = testFiles.iterator(); iterator
                    .hasNext();) {
                final String fileName = (String) iterator.next();
                System.out.println(fileName);
            }
        } catch (final Exception ex) {
            fail(ex.getMessage());
        }
    }

    public void testLexer() {
        final Collection testFiles = getTestFiles();
        for (final Iterator iterator = testFiles.iterator(); iterator.hasNext();) {
            try {
                final String fileName = (String) iterator.next();
                String fileContent;
                fileContent = readfromFile(LexerFromFileTest.TEST_DIR + "/"
                        + fileName);
                final InputProvider inputProvider = new StringInputProvider(
                        fileContent);
                final Lexer lexer = new Lexer(inputProvider);
                try {
                    printAllTokens(lexer);
                } catch (final LexerExeption ex) {
                    fail("In " + fileName + ": " + ex.getMessage());
                }

            } catch (final IOException e) {
                fail(e.getMessage());
            }
        }
    }

    private void printAllTokens(final Lexer lexer) {
        LispToken token = null;
        do {
            token = lexer.nextToken();
            System.out.println(token.getClass().getSimpleName() + " "
                    + token.getLexval());
        } while (!(token instanceof LispEOF));
    }

}
