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
public class BinaryExpression extends SExpression {

    /**
     * 
     */
    private final SExpression head;

    /**
     * 
     */
    private final SExpression tail;

    /**
     * @param head
     * @param tail
     */
    public BinaryExpression(final SExpression head, final SExpression tail) {
        this.head = head;
        this.tail = tail;
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.osu.cse.meisam.interpreter.esxpression.SExpression#isList()
     */
    public boolean isList() {
        return (this.head instanceof LeafExpression)
                && ((this.tail instanceof NilAtomExpression) || ((this.tail instanceof BinaryExpression) && ((BinaryExpression) this.tail)
                        .isList()));
    }

    /**
     * @return the head
     */
    public SExpression getHead() {
        return this.head;
    }

    /**
     * @return the tail
     */
    public SExpression getTail() {
        return this.tail;
    }
}
