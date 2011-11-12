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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return this.token.getClass().getSimpleName() + "["
                + this.token.getLexval() + "]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        if (this.token == null) {
            return 0;
        }
        return this.token.hashCode();
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
        if (obj instanceof LeafNode) {
            final LeafNode leaf = (LeafNode) obj;
            if (this.token == null) {
                return ((LeafNode) obj).getToken() == null;
            }
            return this.token.equals(leaf.getToken());
        }
        return false;
    }

}