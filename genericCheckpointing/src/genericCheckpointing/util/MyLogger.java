package genericCheckpointing.util;

public class MyLogger{

	
    // FIXME: Add more enum values as needed for the assignment
    public static enum DebugLevel {SECURITY, EXCEPTION, CONSTRUCTOR, CONTEXT, NONE };

    private static DebugLevel debugLevel;


    // FIXME: Add switch cases for all the levels
    public static void setDebugValue (int levelIn) {
	switch (levelIn) {
	case 0: debugLevel = DebugLevel.NONE; break;
	case 1: debugLevel = DebugLevel.SECURITY; break;
	case 2: debugLevel = DebugLevel.EXCEPTION; break;
	case 3: debugLevel = DebugLevel.CONSTRUCTOR; break;
	case 4: debugLevel = DebugLevel.CONTEXT; break;
	//default: debugLevel = DebugLevel.NONE; break;
	}
    }

    /**
     * @param levelIn
     */
    public static void setDebugValue (DebugLevel levelIn) {
	debugLevel = levelIn;
    }

    /**
     * @param message
     * @param levelIn
     */
    public static void writeMessage (String     message  ,
                                     DebugLevel levelIn ) {
	if (levelIn == debugLevel)
	    System.out.println(message);
    }

    public String toString() {
	return "The debug level has been set to the following " + debugLevel;
    }
    
}
