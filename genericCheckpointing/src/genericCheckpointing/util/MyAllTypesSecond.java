package genericCheckpointing.util;

import genericCheckpointing.visitor.VisitorI;

/**
 * @author suvar
 *
 */
public class MyAllTypesSecond extends SerializableObject{

	char myCharT;
	float myFloatT;
	short myShortT;
	double myDoubleT;
	double myOtherDoubleT;

	public MyAllTypesSecond() {
		
	}
	
	/**
	 * @param myCharT
	 * @param myFloatT
	 * @param myShortT
	 * @param myDoubleT
	 * @param myOtherDoubleT
	 */
	public MyAllTypesSecond(char myCharT, float myFloatT, short myShortT, double myDoubleT, double myOtherDoubleT) {
		this.myCharT = myCharT;
		this.myFloatT = myFloatT;
		this.myShortT = myShortT;
		this.myDoubleT = myDoubleT;
		this.myOtherDoubleT = myOtherDoubleT;
	}

	public void setMyCharT(char myCharT) {
		this.myCharT = myCharT;
	}

	public void setMyFloatT(float myFloatT) {
		this.myFloatT = myFloatT;
	}

	public void setMyShortT(short myShortT) {
		this.myShortT = myShortT;
	}

	public void setMyDoubleT(double myDoubleT) {
		this.myDoubleT = myDoubleT;
	}

	public void setMyOtherDoubleT(double myOtherDoubleT) {
		this.myOtherDoubleT = myOtherDoubleT;
	}

	public char getMyCharT() {
		return myCharT;
	}

	public float getMyFloatT() {
		return myFloatT;
	}

	public short getMyShortT() {
		return myShortT;
	}

	public double getMyDoubleT() {
		return myDoubleT;
	}

	public double getMyOtherDoubleT() {
		return myOtherDoubleT;
	}

	@Override
	public String toString() {
		return "MyAllTypesSecond [myCharT=" + myCharT + ", myFloatT=" + myFloatT + ", myShortT=" + myShortT
				+ ", myDoubleT=" + myDoubleT + ", myOtherDoubleT=" + myOtherDoubleT + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + myCharT;
		long temp;
		temp = Double.doubleToLongBits(myDoubleT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Float.floatToIntBits(myFloatT);
		temp = Double.doubleToLongBits(myOtherDoubleT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + myShortT;
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
		MyAllTypesSecond other = (MyAllTypesSecond) obj;
		if (myCharT != other.myCharT)
			return false;
		if (Double.doubleToLongBits(myDoubleT) != Double.doubleToLongBits(other.myDoubleT))
			return false;
		if (Float.floatToIntBits(myFloatT) != Float.floatToIntBits(other.myFloatT))
			return false;
		if (Double.doubleToLongBits(myOtherDoubleT) != Double.doubleToLongBits(other.myOtherDoubleT))
			return false;
		if (myShortT != other.myShortT)
			return false;
		return true;
	}
	
	/**
	 * @param visitor
	 * @return
	 */
	public int accept(VisitorI visitor) {
		return visitor.visit(this);
	}
}
