package genericCheckpointing.util;

import java.io.BufferedWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import genericCheckpointing.server.SerStrategy;

public class XMLSerialization implements SerStrategy {

		@Override
	public void processInput(SerializableObject sObject, BufferedWriter bw) 
		{
			try{
			Field [] fields=sObject.getClass().getDeclaredFields();
			
			bw.write("<DPSerialization>\n");
			bw.write("<complexType xsi:type=\""+sObject.getClass().getCanonicalName()+"\">\n");
			for(Field f: fields)
			{
				Method m=sObject.getClass().getMethod(("get"+f.getName()));
					bw.write("<"+f.getName()+" xsi:type=xsd:"+f.getType().getSimpleName()+">"+m.invoke(sObject)+"</"+f.getName()+">\n");
			}
			bw.write("</DPSerialization>\n");
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
			
		}
    
    
}
