/**
 *
 */
package edu.osu.cse.meisam.interpreter;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

/**
 * @author fathi
 * 
 */
public class Interpreter {

    private final Reader in;

    public Interpreter(final InputStream in, final PrintStream out) {
        this.in = new InputStreamReader(new BufferedInputStream(in));
    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        try {
            final Interpreter interpreter = new Interpreter(System.in,
                    System.out);
            interpreter.interpret();
        } catch (final Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }

    public void interpret() {
        throw new RuntimeException("Not implemented yet");
    }

}
