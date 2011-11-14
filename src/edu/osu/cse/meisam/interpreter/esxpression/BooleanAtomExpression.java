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

package edu.osu.cse.meisam.interpreter.esxpression;

import edu.osu.cse.meisam.interpreter.InterPreterException;

/**
 * @author Meisam Fathi Salmi <fathi@cse.ohio-state.edu>
 * 
 */
public class BooleanAtomExpression extends LeafExpression {

    /**
     * 
     */
    private final static String TRUE = "T";

    /**
     * 
     */
    private final static String FALSE = "NIL";

    /**
     * 
     */
    private final boolean val;

    /**
     * @param val
     */
    public BooleanAtomExpression(final boolean val) {
        this.val = val;
    }

    public BooleanAtomExpression(final String string) {
        if (BooleanAtomExpression.TRUE.equals(string)) {
            this.val = true;
        } else if (BooleanAtomExpression.FALSE.equals(string)) {
            this.val = false;
        }
        throw new InterPreterException(string
                + " is an invalid value for a boolean atom");
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "BooleanAtomExpression [val=" + this.val + "]";
    }

}
