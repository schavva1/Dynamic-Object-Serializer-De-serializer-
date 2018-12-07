package genericCheckpointing.server;

import genericCheckpointing.util.SerializableObject;

/**
 * @author suvar
 *
 */
public interface RestoreI extends StoreRestoreI {
    SerializableObject readObj(String wireFormat);
}
