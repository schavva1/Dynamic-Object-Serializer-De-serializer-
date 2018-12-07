package genericCheckpointing.util;

import genericCheckpointing.visitor.VisitorI;

public class MyAllTypesFirst extends SerializableObject{

	int myInt;
	long myLong;
	boolean myBool;
	int myOtherInt;
	String myString;
	long myOtherLong;
	
	public MyAllTypesFirst() {
		
	}
	
	public MyAllTypesFirst(int myInt, long myLong, String myString, boolean myBool, int myOtherInt, long myOtherLong) {
		this.myInt = myInt;
		this.myLong = myLong;
		this.myString = myString;
		this.myBool = myBool;
		this.myOtherInt = myOtherInt;
		this.myOtherLong = myOtherLong;
	}

	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}

	public void setMyLong(long myLong) {
		this.myLong = myLong;
	}

	public void setMyString(String myString) {
		this.myString = myString;
	}

	public void setMyBool(boolean myBool) {
		this.myBool = myBool;
	}

	public void setMyOtherInt(int myOtherInt) {
		this.myOtherInt = myOtherInt;
	}

	public void setMyOtherLong(long myOtherLong) {
		this.myOtherLong = myOtherLong;
	}
	
	public int getMyInt() {
		return myInt;
	}

	public long getMyLong() {
		return myLong;
	}

	public boolean getMyBool() {
		return myBool;
	}

	public int getMyOtherInt() {
		return myOtherInt;
	}

	public String getMyString() {
		return myString;
	}

	public long getMyOtherLong() {
		return myOtherLong;
	}

	@Override
	public String toString() {
		return "MyAllTypesFirst [myInt=" + myInt + ", myLong=" + myLong + ", myBool=" + myBool + ", myOtherInt="
				+ myOtherInt + ", myString=" + myString + ", myOtherLong=" + myOtherLong + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (myBool ? 1231 : 1237);
		result = prime * result + myInt;
		result = prime * result + (int) (myLong ^ (myLong >>> 32));
		result = prime * result + myOtherInt;
		result = prime * result + (int) (myOtherLong ^ (myOtherLong >>> 32));
		result = prime * result + ((myString == null) ? 0 : myString.hashCode());
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
		MyAllTypesFirst other = (MyAllTypesFirst) obj;
		if (myBool != other.myBool)
			return false;
		if (myInt != other.myInt)
			return false;
		if (myLong != other.myLong)
			return false;
		if (myOtherInt != other.myOtherInt)
			return false;
		if (myOtherLong != other.myOtherLong)
			return false;
		if (myString == null) {
			if (other.myString != null)
				return false;
		} else if (!myString.equals(other.myString))
			return false;
		return true;
	}
	
	public int accept(VisitorI visitor) {
		return visitor.visit(this);
	}
}
