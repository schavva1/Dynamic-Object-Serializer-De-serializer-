package genericCheckpointing.visitor;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

/**
 * @author suvar
 *
 */
public class PrimeVisitorImpl implements VisitorI {

	public PrimeVisitorImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int visit(MyAllTypesFirst first) {
		
		int count = 0;
		
		if (first.getMyInt() == first.getMyOtherInt()) {
			return count;
		}
		
		if (isPrime(first.getMyInt())) {
			count += 1;
		}
		
		if (isPrime(first.getMyOtherInt())) {
			count += 1;
		}
		
		return count;
	}

	@Override
	public int visit(MyAllTypesSecond second) {
		return 0;
	}
	
	/**
	 * @param num
	 * @return
	 */
	private boolean isPrime(int num) {
        boolean flag = false;
        for(int i = 2; i <= num/2; ++i)
        {
            if(num % i == 0)
            {
                flag = true;
                break;
            }
        }

        if (!flag)
            return true;
        else
            return false;
	}

	@Override
	public String toString() {
		return "PrimeVisitorImpl [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	

}
