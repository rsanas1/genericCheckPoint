package genericCheckpointing.xmlStoreRestore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import genericCheckpointing.server.DSerStrategy;
import genericCheckpointing.server.SerStrategy;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.XMLDSerialization;
import genericCheckpointing.util.XMLSerialization;

public class StoreRestoreHandler implements InvocationHandler {

	BufferedWriter bw;
	BufferedReader br;
	
	
	 
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		
		if(method.getName().contains("writeObj"))
		{
			if(args[1].toString().equals("XML"))
			{
				bw=(BufferedWriter) args[2];
				serializeData((SerializableObject)args[0], new XMLSerialization());
				
				
			}
			
			
		}
		else if(method.getName().contains("readObj"))
		{
			br= (BufferedReader) args[1];
			Object s=null;
			try {
				
				s= dserializeData(new XMLDSerialization());
				System.out.println(s);
				return s;
			/*	if(s.equals(null))
				{
					System.out.println("Found NULL");
					System.exit(1);
				} */
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
				System.out.println(s.toString());
			}  
			
			
		/*	String endDP="</DPSerialization>";
			
			String firstLine=br.readLine();
			if(firstLine.equals(null))
				return null;
			if(firstLine.equalsIgnoreCase("<DPSerialization>"))
			{
				String line=br.readLine();
				String [] tokens;
				if(line.contains("complexType"))
				{
					Class c=null;
					SerializableObject sobj=null;
					tokens=line.split("\"");
					try {
						c=Class.forName(tokens[1]);
						
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						System.out.println("error "+e.toString());
					}
					try {
						sobj= (SerializableObject) c.newInstance();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("error<2> "+e.toString());
					}
					line=br.readLine();
					
					while(!(line.equalsIgnoreCase(endDP)))
					{
						if(line.equalsIgnoreCase("</complexType>"))
						{
							line=br.readLine();
							continue;
						}
						tokens=line.split("<| ");
						String [] tokens1=tokens[2].split("xsd:|>|<");
						Method m=null;
								Class dataTypeClass=null;
								
								switch(tokens1[1])
								{
								case "int": 
											dataTypeClass=Integer.TYPE;
											try {
												m = c.getMethod(("set"+tokens[1]), new Class[] { dataTypeClass });
												//System.out.println("set"+tokens[1]+"Value "+tokens1[2]);
											} catch (Exception e) {
												System.out.println(e.toString());
											}
											m.invoke(sobj,Integer.parseInt(tokens1[2]));
											break;
								case "long":
											dataTypeClass=Long.TYPE;
											try {
												m = c.getMethod(("set"+tokens[1]), new Class[] { dataTypeClass });
												//System.out.println("set"+tokens[1]+" Value "+tokens1[2]);
											} catch (Exception e) {
												// TODO Auto-generated catch block
												System.out.println("create "+e.toString());
											}
											m.invoke(sobj,Long.parseLong(tokens1[2]));
											break;
								case "String":
											dataTypeClass=String.class;
											try {
												m = c.getMethod(("set"+tokens[1]), new Class[] { dataTypeClass });
												//System.out.println("set"+tokens[1]+" Value "+tokens1[2]);
											} catch (Exception e) {
												// TODO Auto-generated catch block
												System.out.println("create "+e.toString());
											}
											m.invoke(sobj,tokens1[2]);
											break;
								case "boolean":
											dataTypeClass=Boolean.TYPE;
											try {
												m = c.getMethod(("set"+tokens[1]), new Class[] { dataTypeClass });
												//System.out.println("set"+tokens[1]+" Value "+tokens1[2]);
											} catch (Exception e) {
												// TODO Auto-generated catch block
												System.out.println("create "+e.toString());
											}
											m.invoke(sobj,Boolean.parseBoolean(tokens1[2]));
											break;
								case "double": 
											dataTypeClass=Double.TYPE;
											try {
													m = c.getMethod(("set"+tokens[1]), new Class[] { dataTypeClass });
												} 
											catch (Exception e) {
													System.out.println(e.toString());
												}
											m.invoke(sobj,Double.parseDouble(tokens1[2]));
											break;
								case "float":
									dataTypeClass=Float.TYPE;
									try {
											m = c.getMethod(("set"+tokens[1]), new Class[] { dataTypeClass });
										} 
										catch (Exception e) 
										{
											System.out.println(e.toString());
										}
									
									m.invoke(sobj,Float.parseFloat(tokens1[2]));
									break;
								case "short":
									dataTypeClass=Short.TYPE;
									try 
									{
										m = c.getMethod(("set"+tokens[1]), new Class[] { dataTypeClass });
									} 
									catch (Exception e) {
										System.out.println(e.toString());
									}
									m.invoke(sobj,Short.parseShort(tokens1[2]));
									break;
								case "char":
									dataTypeClass=Character.TYPE;
									try {
										m = c.getMethod(("set"+tokens[1]), new Class[] { dataTypeClass });
									} catch (Exception e) {
										// TODO Auto-generated catch block
										System.out.println(e.toString());
									}
									m.invoke(sobj,tokens1[2].charAt(0));
									break;
								
								default :
									System.out.println("UnKnown Datatype");
									System.exit(1);
								}
						
						
						line=br.readLine();
					}
					
					return sobj;
				}
			} */
			
		}
		
		return null;
	}
	
	 public void serializeData(SerializableObject sObject, SerStrategy sStrategy) 
	 {
         sStrategy.processInput(sObject,bw);
	 }
	 
	 public  SerializableObject dserializeData(DSerStrategy sStrategy)
	 {
		 SerializableObject s= sStrategy.processOutput(br);
		 System.out.println(s.toString());
		 return s;
	 }
	
	

}
