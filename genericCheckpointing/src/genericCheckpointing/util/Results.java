package genericCheckpointing.util;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import airportSecurityState.airportStates.AirportContextI;
import airportSecurityState.airportStates.Context.pItems;
import genericCheckpointing.util.MyLogger.DebugLevel;

/**
 * @author suvar
 * Class to print results that implements FileDisplayInterface,StdoutDisplayInterface
 */

public class Results implements FileDisplayInterface,StdoutDisplayInterface{

	
	private FileWriter filewriter = null;
	private BufferedWriter buffwrite = null;
	private String text, outputFile1 = null;
	private List<String> result;


	public Results() {
		MyLogger.writeMessage("Results Constructor Called", DebugLevel.CONSTRUCTOR);
		text = null;
		this.outputFile1 = null;
		this.result = new ArrayList<String>();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((outputFile1 == null) ? 0 : outputFile1.hashCode());
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Results other = (Results) obj;
		if (outputFile1 == null) {
			if (other.outputFile1 != null)
				return false;
		} else if (!outputFile1.equals(other.outputFile1))
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}


	public Results(String name1) {	
		text = "";
		this.outputFile1 = name1;
		this.result = new ArrayList<String>();
	}


	/**
	 * @param name
	 * Method to set output file name 
	 */
	public void setFileName(String name) {
		this.outputFile1 = name;
	}

	public void writeToStdout(String s){
		System.out.println(s);		
	}


	/* Method to write to a file  */

	public void writeToFile(){
		try {
			File file = new File(outputFile1);
			if(!file.exists()) {
				file.createNewFile();
			}

			filewriter = new FileWriter(outputFile1);
			buffwrite = new BufferedWriter(filewriter);

			for (String s : result) {
				filewriter.write(s);
			}

		}catch(IOException e){
			System.exit(1);
		}finally {
			try {
				buffwrite.close();
				filewriter.close();
				result.clear();
			}catch(IOException e){

			}
		}
	}

	
	@Override
	public String toString() {
		return "Results [filewriter=" + filewriter + ", buffwrite=" + buffwrite + ", text=" + text + ", outputFile1="
				+ outputFile1 + ", result=" + result + ", hashCode()=" + hashCode() + ", getResults()=" + getResults()
				+ ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}


	/* Method to Store Results */
	/**
	 * @param s
	 */
	public void StoreResults(String s) {
		this.result.add(s + "\n"); 
	}

	public String getResults() {
		System.out.println(this.text);
		return this.text;
	}

	
	public void toString(String s) {
		this.text += s;
	}

	@Override
	public void writeSumToScreen() {
		//MyLogger.writeMessage("Result: " + this.result.toString(), DebugLevel.RESULT);
		//System.out.println("The sum of all the prime numbers is:" + this.result.toString());
	}
	
	public void getInfo(FileProcessor f, AirportContextI context) {
		String read = f.readline();
		 
		while(read != null)
		{
			int num_day = 0;
			int num_pItems = 0;

			String[] line = read.trim().split(";");

			try {
				num_day = Integer.parseInt(line[0].split(":")[1]);
			} catch (NumberFormatException e) {
				MyLogger.writeMessage("Caught hold of exception", DebugLevel.EXCEPTION);
				throw new NumberFormatException("Day count should be +ve Integer value");
			}
			
			String items = "";
			try {
				items = line[1].split(":")[1].toUpperCase();
			} catch (ArrayIndexOutOfBoundsException e) {
				MyLogger.writeMessage("Caught hold of exception", DebugLevel.EXCEPTION);
				throw new ArrayIndexOutOfBoundsException("Item should not be empty");
			}
			
			if (items.isEmpty()) {
				throw new IllegalArgumentException("Item should not be empty");
			}
			
			if (pItems.contains(items)) {
				num_pItems += 1;
			}
			
			context.checkIn(num_day, num_pItems);
			
			read = f.readline();
		}

		f.close();
	}
	
}