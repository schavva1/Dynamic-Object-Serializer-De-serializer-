package genericCheckpointing.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author suvar
 *
 */
public class FileProcessor {
	
	private File first;
	private FileReader filereader;
	private BufferedReader buffreader;
	private String student_course = null;
	String line = "";

	/**
	 * @param input
	 */
	public FileProcessor(String input) {
		this.student_course = input;
		first = new File(student_course);

		try {
			filereader = new FileReader(first);
			buffreader = new BufferedReader(filereader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "FileProcessor [first=" + first + ", filereader=" + filereader + ", buffreader=" + buffreader
				+ ", student_course=" + student_course + ", line=" + line + ", readline()=" + readline()
				+ ", getbuffreader()=" + getbuffreader() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	/**
	 * @return
	 */
	public synchronized String readline() {
		try {
			line = getbuffreader().readLine();
		} catch(IOException e)  {
			e.printStackTrace();
		}

		return line;
	}

	private BufferedReader getbuffreader () {
		return buffreader;
	}

	public void close() {
		try {
			filereader.close();
			buffreader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}