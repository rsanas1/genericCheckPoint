package genericCheckpointing.driver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.util.ArrayList;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length!=3)
		{
			System.err.println("\n Invalid number of arguments");
			System.exit(1);
			
		}
		int NUM_OF_OBJECTS=0;
		String mode= args[0];
		String output=args[2];
		try 
		{
			NUM_OF_OBJECTS=Integer.parseInt(args[1]);
		} 
		catch (NumberFormatException e) 
		{
			System.out.println(e.toString());
			System.exit(1);
			
		}
		ProxyCreator pc = new ProxyCreator();
		ArrayList<SerializableObject> serlist=new ArrayList<SerializableObject>();
		ArrayList<SerializableObject> dserlist=new ArrayList<SerializableObject>();
		
		InvocationHandler handler=new StoreRestoreHandler();
		
		
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
									 new Class[] {
									     StoreI.class, RestoreI.class
									 }, 
									 handler
									 );
			
		BufferedWriter bw=null;
		BufferedReader br=null;
		
		
		MyAllTypesFirst myFirst;
		MyAllTypesSecond  mySecond;

		SerializableObject myRecordRet;
		

		switch(args[0])
		{
		case "deser":
			 
			try {
				br=new BufferedReader(new FileReader(args[2]));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

			// create a data structure to store the returned ojects
			for (int j=0; j<2*NUM_OF_OBJECTS; j++) 
			{

			    myRecordRet = ((RestoreI) cpointRef).readObj("XML",br);
			    // FIXME: store myRecordRet in the vector
			    dserlist.add(myRecordRet);
			   
			    
			}
			
			
			
			break;
		case "serdeser":
			int miss=0;
			boolean toggle=false;
			try {
				bw=new BufferedWriter(new FileWriter(args[2]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
				System.exit(1);
			}
			for (int i=0; i<NUM_OF_OBJECTS; i++) 
			{

			    myFirst = new MyAllTypesFirst(i*12+2,i*44+4,"Hey"+i,toggle);
			    mySecond = new MyAllTypesSecond(3.45,(float)4.65,(short)(i+54),'X');
			    serlist.add(myFirst);
			    serlist.add(mySecond);
			    ((StoreI) cpointRef).writeObj(myFirst, "XML",bw);
			    ((StoreI) cpointRef).writeObj(mySecond, "XML",bw);
			    
			    toggle=!toggle;
			}
			
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.toString());
				System.exit(1);
			}
			
			/************************************** READING ******************************/
			
			try {
				br=new BufferedReader(new FileReader(args[2]));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// create a data structure to store the returned objects
			
			for (int j=0; j<2*NUM_OF_OBJECTS; j++) 
			{

			    myRecordRet = ((RestoreI) cpointRef).readObj("XML",br);
			    // FIXME: store myRecordRet in the vector
			    
			    	dserlist.add(myRecordRet);
			    	 System.out.println(myRecordRet.toString());
			   
			   
			}
			
				
				for(int i=0;i<serlist.size() && i < dserlist.size();i++)
				{
					
				if((serlist.get(i).equals(dserlist.get(i))) && 
						serlist.get(i).hashCode()==(dserlist.get(i).hashCode()))
					{
//						System.out.println("MATCH");
//						System.out.println("***"+serlist.get(i).toString());
//						System.out.println("---"+dserlist.get(i).toString());
					}
					else
					{
						miss++;
//						System.out.println("MISMATCH");
//						System.out.println("***"+serlist.get(i).toString());
//						System.out.println("---"+dserlist.get(i).toString());
					}
						
				}
				
				System.out.println(miss+" mismatched objects");
				
			

			break;
		
		
		
		}
		
		

		// FIXME: invoke a method on the handler to close the file (if it hasn't already been closed)
		
		// FIXME: compare and confirm that the serialized and deserialzed objects are equal. 
		// The comparison should use the equals and hashCode methods. Note that hashCode 
		// is used for key-value based data structures
	    
	  }
}

	


