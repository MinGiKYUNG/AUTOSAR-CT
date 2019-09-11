package dms.konkuk.autosar.ct.birt;

import org.apache.xalan.serialize.Serializer;
import org.apache.xalan.serialize.SerializerFactory;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.*;



public class XMLValidation {

	public String xmlpath = null;
	public XMLValidation(String xmlpath)
	{
		this.xmlpath = xmlpath;
	}


	public boolean go() {
		boolean temp = true;
//		try{			
//			DOMParser dom = new DOMParser();
//			dom.parse(xmlpath);
//			Document doc = dom.getDocument();
//			NodeList ndlist = doc.getChildNodes();
//			Node nd = ndlist.item(0);
//			//xml 형식 검사 
//			if(Validation(nd))
//			{
//				temp = true;			
//			}
//			else
//			{
//				temp = false;
//			}
//		}catch(Exception e){
//			System.out.println("error : " + e.toString());
//		}
		return temp; 	
	}

	public boolean Validation(Node nd)
	{
		int temp = 0;
		NodeList ndlist = nd.getChildNodes();
		for(int i = 0; i < ndlist.getLength(); i++)
		{
			Node child_nd = ndlist.item(i);
			if(child_nd.getNodeName().equals("element"))
			{								
				temp = temp|1;
			}
			else if(child_nd.getNodeName().equals("resultfile"))
			{
				temp = temp|2;
			}
			else if(child_nd.getNodeName().equals("resultinfo"))
			{
				temp = temp|4;
			}
		}

		if(temp == 7)
		{
			return true;
		}
		else
		{
			return false;
		}
	}



}