package genericCheckpointing.server;

import java.io.BufferedWriter;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;

public interface StoreI extends StoreRestoreI{

	 void writeObj(MyAllTypesFirst aRecord, String wireFormat,BufferedWriter bf);
     void writeObj(MyAllTypesSecond aRecord, String wireFormat,BufferedWriter bf);
}
