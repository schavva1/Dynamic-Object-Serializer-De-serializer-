package genericCheckpointing.util;

import java.io.IOException;

public interface FileDisplayInterface {

	/**
	 * @param s - write to the file
	 * @throws IOException
	 */
	public void writeToFile(String s) throws IOException;

	/**
	 * @throws IOException
	 */
	public void close() throws IOException;

}
