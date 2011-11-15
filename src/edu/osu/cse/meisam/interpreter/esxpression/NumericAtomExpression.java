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
/**
 * @author Meisam Fathi Salmi <fathi@cse.ohio-state.edu>
 * 
 */
public class NumericAtomExpression extends LeafExpression {

    /**
     * 
     */
    private final int val;

    /**
     * @param val
     */
    public NumericAtomExpression(final int val) {
        this.val = val;

    }

    /**
     * @param lexval
     */
    public NumericAtomExpression(final String lexval) {
        try {
            this.val = Integer.parseInt(lexval);
        } catch (final NumberFormatException e) {
            throw new InterPreterException(lexval
                    + " cannot be parsed into an integer number.");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return Integer.toString(this.val);
    }

    /**
     * @return the val
     */
    public int getVal() {
        return this.val;
    }

}
