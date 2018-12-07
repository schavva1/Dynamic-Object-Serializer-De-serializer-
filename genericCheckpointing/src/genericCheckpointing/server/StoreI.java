package genericCheckpointing.server;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

/**
 * @author suvar
 *
 */
public interface StoreI extends StoreRestoreI {
    void writeObj(MyAllTypesFirst aRecord, int authID, String wireFormat);
    void writeObj(MyAllTypesSecond bRecord, int authID, String wireFormat);
    void setFileName(String fileName);
    void openFile();
    void closeFile();
}
