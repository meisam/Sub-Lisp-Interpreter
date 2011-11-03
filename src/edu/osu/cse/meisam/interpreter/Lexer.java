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

import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;

/**
 * @author Meisam Fathi Salmi <fathi@cse.ohio-state.edu>
 * 
 */
public class Lexer {

    private final Reader in;

    private final PrintStream out;

    /**
     * 
     */
    public Lexer(final Reader in, final PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public String nextToken() {
        try {
            StringBuffer buffer = new StringBuffer(1000);
            while (in.ready()) {
                int nextChar = in.read();
                buffer.append(nextChar);
            }
            return buffer.toString();
        } catch (IOException e) {
            throw new LexerExeption("Cannot read the output");
        }
    }

}
