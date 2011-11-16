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

/**
 * @author Meisam Fathi Salmi <fathi@cse.ohio-state.edu>
 * 
 */
public class BooleanAtomExpression extends LeafExpression {

    /**
     * 
     */
    public final static BooleanAtomExpression T = new BooleanAtomExpression(
            true);

    /**
     * 
     */
    public final static BooleanAtomExpression NIL = new BooleanAtomExpression(
            false);

    /**
     * 
     */
    private final boolean val;

    /**
     * @param val
     */
    private BooleanAtomExpression(final boolean val) {
        this.val = val;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        if (this.val) {
            return "T";
        } else {
            return "NIL";
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return this.val ? 0 : 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BooleanAtomExpression) {
            final boolean otherVal = ((BooleanAtomExpression) obj).val;
            return this.val == otherVal;
        }
        return false;
    }

}
