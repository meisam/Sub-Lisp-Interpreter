/**
 * 
 */
package edu.osu.cse.meisam.interpreter;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;

/**
 * @author fathi
 *
 */
public class Interpreter {

	private Reader in;
	private PrintStream out;

	public Interpreter(InputStream in, PrintStream out){
		this.in = new InputStreamReader( new BufferedInputStream(in));
		this.out = out;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			Interpreter interpreter = new Interpreter(System.in, System.out);
			interpreter.interpret();
		} catch(Exception ex){
			System.out.println("Error: "+ex.getMessage());
		}

	}

	public void interpret() {
		throw new RuntimeException("Not implemented yet");
	}

}
