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

import java.util.HashMap;
import java.util.Map;

import edu.osu.cse.meisam.interpreter.esxpression.SExpression;

/**
 * @author Meisam Fathi Salmi <fathi@cse.ohio-state.edu>
 * 
 */
public class ParameterBindings {

    private static final int INITIAL_SIZE = 100;

    /**
     * 
     */
    private final Map bindings;

    /**
     * 
     */
    public ParameterBindings() {
        this.bindings = new HashMap(ParameterBindings.INITIAL_SIZE);
    }

    /**
     * @param actualParam
     * @param value
     */
    public void addBinding(final String actualParam, final SExpression value) {
        this.bindings.put(actualParam, value);
    }

    public SExpression lookup(final String actualParamName) {
        final SExpression boundedVal = (SExpression) this.bindings
                .get(actualParamName);
        if (boundedVal == null) {
            throw new InterPreterException("No binding found for "
                    + actualParamName);
        }
        return boundedVal;
    }
}
