package genericCheckpointing.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {

	private static BufferedReader reader;

	public FileProcessor() {
	}

	/**
	 * @param fileName from where the file processing to be done
	 * @throws IOException
	 */
	public FileProcessor(String fileName) {
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return String - A line read from a file
	 * @throws IOException
	 */
	public String readLine() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @throws IOException
	 */
	public void close() {
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return String - object converted to String
	 */
	@Override
	public String toString() {
		return super.toString();
	}

}
