package edu.osu.cse.meisam.interpreter;

import edu.osu.cse.meisam.interpreter.tokens.Token;

/**
 * @author Meisam Fathi Salmi <fathi@cse.ohio-state.edu>
 * 
 */
public class LeafNode extends ParseTree {

    public final static LeafNode NILL_LEAF = new LeafNode(null);

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

    public ParseTree getLeftTree() {
        // TODO Auto-generated method stub
        return super.getLeftTree();
    }

    public ParseTree getRightTree() {
        // TODO Auto-generated method stub
        return super.getRightTree();
    }

    public boolean hasLeftTree() {
        return false;
    }

    public boolean hasRightTree() {
        return false;
    }

}