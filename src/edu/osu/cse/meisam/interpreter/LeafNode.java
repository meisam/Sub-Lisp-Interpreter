package edu.osu.cse.meisam.interpreter;

import edu.osu.cse.meisam.interpreter.tokens.Token;

/**
 * @author Meisam Fathi Salmi <fathi@cse.ohio-state.edu>
 * 
 */
public class LeafNode extends ParseTree {

    /**
     * 
     */
    private final Token token;

    /**
     * @param token
     */
    public LeafNode(final Token token) {
        this.token = token;
    }

    /**
     * @return the token
     */
    public Token getToken() {
        return this.token;
    }

    public String toString() {
        return this.token.getClass().getSimpleName() + "["
                + this.token.getLexval() + "]";
    }

}