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

// <S> ::= <E>
// <E> ::= atom
// <E> ::= ( <X>
// <X> ::= <E> <Y>
// <X> ::= ) 
// <Y> ::= . <E> )
// <Y> ::= <R> )
// <R> ::= NIL
// <R> ::= <E> <R>

/**
 * @author Meisam Fathi Salmi <fathi@cse.ohio-state.edu>
 * 
 */
public class Parser {

    private static final String DEFAULT_ERROR_MESSAGE = "Input is not a valid lisp program";

    /**
     * 
     */
    private final Lexer lexer;

    /**
     * Current token
     */
    private LispToken token;

    /**
     * @param lexer
     */
    public Parser(final Lexer lexer) {
        this.lexer = lexer;
    }

    public void parse() {
        while (this.lexer.hasMoreTokens()) {
            this.token = this.lexer.nextToken();
            parseE();
        }
    }

    private void move() {
        System.out.println("moving on " + this.token.getLexval());
        this.token = this.lexer.nextToken();
    }

    private void parseE() {
        if (this.token instanceof LispAtom) {
            parseAtom();
        } else if (this.token instanceof LispOpenParentheses) {
            parseOpenParentheses();
            parseX();
        } else { // error
            raiseParserError(Parser.DEFAULT_ERROR_MESSAGE);
        }
    }

    private void parseX() {
        if (this.token instanceof LispCloseParentheses) {
            parseCloseParentheses();
        } else if ((this.token instanceof LispOpenParentheses)
                || (this.token instanceof LispAtom)) { // head of E
            parseE();
            parseY();
        } else { // error
            raiseParserError(Parser.DEFAULT_ERROR_MESSAGE);
        }
    }

    private void parseY() {
        if (this.token instanceof LispDot) {
            parseDot();
            parseE();
            parseCloseParentheses();
        } else {
            parseR();
            parseCloseParentheses();
        }
    }

    private void parseR() {
        if ((this.token instanceof LispAtom)
                || (this.token instanceof LispOpenParentheses)) {
            parseE();
            parseR();
        } else {
            // do nothing
        }
    }

    private void parseAtom() {
        if (this.token instanceof LispAtom) {
            move();
        } else {
            raiseParserError("Looking for a lisp atom but fouund "
                    + this.token.getClass().getSimpleName());
        }
    }

    private void parseOpenParentheses() {
        if (this.token instanceof LispOpenParentheses) {
            move();
        } else {
            raiseParserError("Looking for a ( but fouund "
                    + this.token.getClass().getSimpleName());
        }
    }

    private void parseCloseParentheses() {
        if (this.token instanceof LispCloseParentheses) {
            move();
        } else {
            raiseParserError("Looking for a ) but fouund "
                    + this.token.getClass().getSimpleName());
        }
    }

    private void parseDot() {
        if (this.token instanceof LispDot) {
            move();
        } else {
            raiseParserError("Looking for a . but fouund "
                    + this.token.getClass().getSimpleName());
        }
    }

    private void raiseParserError(final String msg) {
        throw new ParserException(msg);
    }

}
