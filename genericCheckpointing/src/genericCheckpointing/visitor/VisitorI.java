package genericCheckpointing.visitor;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

/**
 * @author suvar
 *
 */
public interface VisitorI {
	int visit(MyAllTypesFirst first);
	int visit(MyAllTypesSecond second);
}
