package genericCheckpointing.driver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import genericCheckpointing.util.MyLogger;
import genericCheckpointing.util.MyLogger.DebugLevel;
import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.Results;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.visitor.PalindromVisitorImpl;
import genericCheckpointing.visitor.PrimeVisitorImpl;
import genericCheckpointing.visitor.VisitorI;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;


public class Driver {

	public static void main(String[] args) {
		argchk(args);
		final String METHOD_TYPE = args[0];
		final int NUM_OF_OBJECTS = Integer.parseInt(args[1]);
		argchk(args);
		String FILE_NAME = "checkpoint.txt";

		if (METHOD_TYPE.equalsIgnoreCase("deser")) {
			FILE_NAME = args[2];
		}

		Results writer = new Results(FILE_NAME);

		ProxyCreator pc = new ProxyCreator();

		StoreRestoreHandler handler = new StoreRestoreHandler();
		handler.setWriter(writer);

		// create a proxy
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
				new Class[] {
						StoreI.class, RestoreI.class
				}, 
				handler
				);

		MyAllTypesFirst myFirst;
		MyAllTypesSecond  mySecond;

		// create a data structure to store the objects being serialized
		Vector<SerializableObject> v_old = new Vector<>();

		if (METHOD_TYPE.equalsIgnoreCase("serdeser")) {
			// NUM_OF_OBJECTS refers to the count for each of MyAllTypesFirst and MyAllTypesSecond
			// passed as "N" from the command line. 
			Random rand = new Random();

			for (int i=1; i<=NUM_OF_OBJECTS; i++) {
				myFirst = new MyAllTypesFirst(rand.nextInt(50 * i), rand.nextLong(), String.valueOf((char) (rand.nextInt(26) + 'a')) + String.valueOf((char) (rand.nextInt(26) + 'a')), rand.nextBoolean(), rand.nextInt(100 * i * i), rand.nextLong());
				mySecond = new MyAllTypesSecond((char) (rand.nextInt(26) + 'a'), rand.nextFloat(), (short) rand.nextInt(150 * i), 30 * rand.nextDouble(), 50 * rand.nextDouble());

				v_old.add(myFirst);
				v_old.add(mySecond);

				//System.out.println(myFirst.toString());
				//System.out.println(mySecond);

				// authID (13 and 17) is not being used in the assignment, but
				// is left for future use. 
				((StoreI) cpointRef).writeObj(myFirst, 13,  "XML");
				((StoreI) cpointRef).writeObj(mySecond, 17, "XML");
			}

			writer.close();
		}


		FileProcessor fp = new FileProcessor(FILE_NAME);
		handler.setReader(fp);

		// create a data structure to store the returned objects
		Vector<SerializableObject> v_new = new Vector<>();

		for (int j=0; j<2*NUM_OF_OBJECTS; j++) {
			SerializableObject sObj = ((RestoreI) cpointRef).readObj("XML");
			if (sObj != null) {
				v_new.add(sObj);
				writer.writeToStdout(sObj.toString()); 
			}

		}

		fp.close();


		int mismatchCount = 0;
		for (int index = 0; index < v_old.size(); index++) {
			SerializableObject v1 = v_old.get(index);
			SerializableObject v2 = v_new.get(index);

			if (!v1.equals(v2) || v1.hashCode() != v2.hashCode()) {
				mismatchCount++;
			}
		}

		if (METHOD_TYPE.equalsIgnoreCase("serdeser")) {
			System.out.println(mismatchCount +" mismatched objects");
		}

		if (METHOD_TYPE.equalsIgnoreCase("serdeser")) {
			PalindromVisitorImpl palV = new PalindromVisitorImpl();
			PrimeVisitorImpl priV = new PrimeVisitorImpl();

			int uniquePrimeCount = 0;
			ArrayList<String> palindromes = new ArrayList<>();

			for (SerializableObject obj : v_new) {
				try {
					uniquePrimeCount = uniquePrimeCount + (int) obj.getClass()
					.getMethod("accept", VisitorI.class)
					.invoke(obj, priV);

					boolean isPalindrome = (boolean) (((int)obj.getClass()
							.getMethod("accept", VisitorI.class)
							.invoke(obj, palV)) == 1) ? true : false;

					if (isPalindrome) {
						String fieldValue = (String) obj.getClass().getMethod("getMyString").invoke(obj); 
						palindromes.add(fieldValue);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			System.out.println("Number of Primes:" + uniquePrimeCount);
			System.out.println("Number of Palindromes:" + palindromes.toString());
		}
	}
	private static void argchk(String[] args){
		File inputfile;
		final String mode = args[0];
		if(args.length == 3) {
			
			if (!(mode.equals("serdeser") || mode.equals("deser"))) {
	            System.err.println("Invalid mode:Must be \"serdeser\" or \"deser\")");
	            System.exit(-1);
			}
			
			try {
				Integer.parseInt(args[1]);
			}
			catch (NumberFormatException e) {
	            System.err.println("NUM_OF_OBJECTS should be an integer");
	            e.printStackTrace();
	            System.exit(-1);
	        }
			inputfile = new File(args[2]);
			if(inputfile.isFile()){
				;
			}
			else if(inputfile.isDirectory()){
				try {
					throw new IOException("Input File entered Is a directory please Enter the File name");
				} catch (IOException e) {
					//MyLogger.writeMessage("Caught hold of exception", DebugLevel.EXCEPTION);
					e.printStackTrace();
				}
			}
			else {
				try {
					throw new IOException("Input File not found please Enter the Correct File name");
				} catch (IOException e) {
					//MyLogger.writeMessage("Caught hold of exception", DebugLevel.EXCEPTION);
					e.printStackTrace();
				}
			}
			
		}
		else {
			try {
				throw new ArrayIndexOutOfBoundsException("Give exactly 3 arguments");
			}catch(ArrayIndexOutOfBoundsException e) {
				//MyLogger.writeMessage("Caught hold of exception", DebugLevel.EXCEPTION);
				e.printStackTrace();
				System.exit(1);
			}finally {}
		}
	}
}