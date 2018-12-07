package genericCheckpointing.visitor;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

public class PalindromVisitorImpl implements VisitorI {

	public PalindromVisitorImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int visit(MyAllTypesFirst first) {
		if (isPalindrome(first.getMyString())) {
			return 1;
		}
		return 0;
	}

	@Override
	public int visit(MyAllTypesSecond second) {
		return 0;
	}
	
	private boolean isPalindrome(String str) {
		StringBuilder input = new StringBuilder();
		input.append(str);
		
		if (input.toString().equals(input.reverse().toString())) {
			return true;
		}
		
		return false;
		
	}

}
