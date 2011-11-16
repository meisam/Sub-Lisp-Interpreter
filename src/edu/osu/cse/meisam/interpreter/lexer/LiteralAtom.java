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

package edu.osu.cse.meisam.interpreter.lexer;

/**
 * @author Meisam Fathi Salmi <fathi@cse.ohio-state.edu>
 * 
 */
public final class LiteralAtom extends Atom {

    /**
     * All the keywords in the Lisp
     */
    final static String[] ALL_KEYWORDS = { "DEFUN", "EQ", "ATOM", "NIL", "T",
            "CAR", "CDR", "CAAR" };

    /**
     * Constructs a new Token
     * 
     * @param lexval
     */
    public LiteralAtom(final String lexval) {
        super(lexval);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof LiteralAtom)) {
            return false;
        }
        final LiteralAtom literalAtom = (LiteralAtom) obj;
        if ((getLexval() == null) && (literalAtom.getLexval() == null)) {
            return true;
        }
        return (getLexval() != null)
                && (getLexval().equals(literalAtom.getLexval()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        if (getLexval() == null) {
            return 0;
        } else {
            return getLexval().hashCode();
        }
    }
}
