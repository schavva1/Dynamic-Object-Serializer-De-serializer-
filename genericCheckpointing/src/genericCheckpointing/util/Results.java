package genericCheckpointing.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	private static ArrayList<String> results;
	private static BufferedWriter writer;

	public Results() {
		
	}

	/**
	 * @param outputFile
	 */
	public Results(String outputFile) {
		try {
			writer = new BufferedWriter(new FileWriter(outputFile, true));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return BufferedWriter writer object
	 */
	public BufferedWriter getWriter( ) {
		return writer;
	}

	/**
	 * @param writer to set the file writer object
	 */
	public void setWriter(BufferedWriter writer) {
		Results.writer = writer;
	}

	/**
	 * @param s has the string which is to be stored in the result array
	 */
	public void storeNewResult(String s) {
		results.add(s);
	}

	/**
	 * @param s has the string which is to be written to the file
	 * @throws IOException
	 */
	@Override
	public void writeToFile(String s) throws IOException {
		writer.write(s);
	}

	/**
	 * @throws IOException
	 */
	@Override
	public void close(){
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param s has the string which is to be written to the standard output
	 */
	@Override
	public void writeToStdout(String s) {
		System.out.println(s);
	}

	/**
	 * @return string object converted to string
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}
