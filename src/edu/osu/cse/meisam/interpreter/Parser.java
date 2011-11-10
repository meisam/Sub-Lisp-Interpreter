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

import edu.osu.cse.meisam.interpreter.tokens.LispAtom;
import edu.osu.cse.meisam.interpreter.tokens.LispCloseParentheses;
import edu.osu.cse.meisam.interpreter.tokens.LispDot;
import edu.osu.cse.meisam.interpreter.tokens.LispOpenParentheses;
import edu.osu.cse.meisam.interpreter.tokens.LispToken;

/**
 * @author Meisam Fathi Salmi <fathi@cse.ohio-state.edu>
 * 
 */
public class Parser {

    /**
     * 
     */
    private final Lexer lexer;

    /**
     * @param lexer
     */
    public Parser(final Lexer lexer) {
        this.lexer = lexer;
    }

    public void parse() {
        while (this.lexer.hasMoreTokens()) {
            final LispToken token = this.lexer.nextToken();
            parseE(token);
        }
    }

    private void parseE(LispToken token) {
        if (token instanceof LispAtom) {
            parseAtom(token);
        } else if (token instanceof LispOpenParentheses) {
            consume(token);
            token = this.lexer.nextToken();
            parseX(token);
        } else { // it should be another Expresion
            parseE(token);
            parseY(token);
        }
    }

    private void parseX(final LispToken token) {
        if (token instanceof LispCloseParentheses) {
            consume(token);
        } else {
            parseE(token);
            parseY(token);
        }
    }

    private void parseY(LispToken token) {
        if (token instanceof LispDot) {
            consume(token);
            token = this.lexer.nextToken();
            parseE(token);
            token = this.lexer.nextToken();
            if (!(token instanceof LispCloseParentheses)) {
                throw new ParserException("Expecting to see ), but seeing "
                        + token.getLexval());
            }
            consume(token);
        } else {
            parseR(token);
            token = this.lexer.nextToken();
            if (!(token instanceof LispCloseParentheses)) {
                throw new ParserException("Expecting to see ), but seeing "
                        + token.getLexval());
            }
        }
    }

    private void parseR(final LispToken token) {
        if ((token instanceof LispAtom)
                || (token instanceof LispOpenParentheses)) {
            parseE(token);
            parseR(token);
        } else {
            // do nothing
        }
    }

    private void parseAtom(final LispToken token) {
        consume(token);
        this.lexer.nextToken();
    }

    private void consume(final LispToken token) {
        System.out.println(token.getClass().getSimpleName());
    }
}
