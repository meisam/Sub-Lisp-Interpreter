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

import java.io.PrintStream;

/**
 * @author Meisam Fathi Salmi <fathi@cse.ohio-state.edu>
 * 
 */
public class StreamOutputReceiver implements OutputReceiver {

    /**
     * 
     */
    private final PrintStream stream;

    /**
     * @param stream
     */
    public StreamOutputReceiver(final PrintStream stream) {
        this.stream = stream;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.osu.cse.meisam.interpreter.OutputReceiver#write(java.lang.String)
     */
    public void print(final String s) {
        this.stream.print(s);

    }

    public void println(final String s) {
        this.stream.println(s);

    }

}
