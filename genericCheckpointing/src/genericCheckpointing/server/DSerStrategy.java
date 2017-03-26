package genericCheckpointing.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import genericCheckpointing.util.SerializableObject;

public interface DSerStrategy {

	SerializableObject processOutput(BufferedReader br);
}
