package genericCheckpointing.server;

import java.io.BufferedWriter;

import genericCheckpointing.util.SerializableObject;

public interface SerStrategy 
{
	void processInput(SerializableObject sObject, BufferedWriter bw);
}
