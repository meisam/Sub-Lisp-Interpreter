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

package edu.osu.cse.meisam.interpreter.tokens;

/**
 * This class should never be instantiated directly (that's why it is define
 * abstract)
 * 
 * @author Meisam Fathi Salmi <fathi@cse.ohio-state.edu>
 * 
 */
public abstract class Token {

    /**
     * The lexval for this Token
     */
    private final String lexval;

    /**
     * Constructs a new token for the given lexval. <br>
     * According to the specifications of this sub-lisp language, The scanner
     * should be completely case-insensitive. To make the output uniform, the
     * scanner should translate every letter to upper case; the parser/evaluator
     * should work only with upper-case letters
     * 
     * @param lexval
     */
    protected Token(final String lexval) {
        this.lexval = lexval.toUpperCase();
    }

    /**
     * Returns the lexval of this symbol
     * 
     * @return the lexval
     */
    public String getLexval() {
        return this.lexval;
    }

}
