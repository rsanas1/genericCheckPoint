package genericCheckpointing.server;

import java.io.BufferedReader;

import genericCheckpointing.util.SerializableObject;

public interface RestoreI extends StoreRestoreI{
	
	  SerializableObject readObj(String wireFormat, BufferedReader br);
}
