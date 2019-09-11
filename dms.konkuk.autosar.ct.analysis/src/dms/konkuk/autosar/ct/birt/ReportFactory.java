package dms.konkuk.autosar.ct.birt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Properties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Properties;
import org.apache.xalan.serialize.Serializer;
import org.apache.xalan.serialize.SerializerFactory;
import org.apache.xalan.templates.OutputProperties;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ReportFactory {
	private String Designpath = null;
	private String xmlpath = null;
	private String workspacepath = null;
	
	private static ReportFactory rf = null;
	
	public static ReportFactory getReportFactory()
	{
		if(rf == null)
		{
			rf = new ReportFactory();
		}
		return rf;
	}
	
	public void setxmlpath(String xmlpath)
	{
		this.xmlpath = xmlpath;
	}
	
	public void setworkspacepath(String workspacepath)
	{
		this.workspacepath = workspacepath;
	}


	public String GetReportPath()
	{
		return Designpath;
	}

	public boolean ReportCreate(String flag)
	{
		try {    
			//rptdesign ÀÇ xmlpath º¯°æ 
			DOMParser d_dom = new DOMParser();
			
			
		    d_dom.parse("c:\\ACT\\new_report.rptdesign");
			
			
			Document d_doc = d_dom.getDocument();
			NodeList d_ndlist = d_doc.getChildNodes();
			Node d_nd = d_ndlist.item(0);
			changefilepath(d_nd);

			Element e = d_doc.getDocumentElement();

			Properties props = new Properties();
			props.setProperty("version", "1.0");
			props.setProperty("encoding", "euc-kr");
			props.setProperty("indent", "yes");
			props.setProperty("standalone", "no");

			Serializer serializer = SerializerFactory.getSerializer(OutputProperties.getDefaultMethodProperties("xml"));
			StringWriter writer = new StringWriter();
			serializer.setOutputFormat(props);
			serializer.setWriter(writer);
			serializer.asDOMSerializer().serialize(e);
			
			String temppath = workspacepath+"\\qwmdfienszxjdkesoi";
			File filed= new File(temppath);
			filed.mkdirs();		
			Designpath = temppath+ "\\"+"temp.rptdesign";
			File filef= new File(Designpath);
			OutputStream out = new FileOutputStream(filef); 
			String tempstring = writer.toString();
			out.write(tempstring.getBytes());

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void changefilepath(Node nd)
	{
		NodeList ndlist = nd.getChildNodes();
		for(int i = 0; i < ndlist.getLength(); i++)
		{
			Node child_nd = ndlist.item(i);
			if(child_nd.getNodeName().equals("data-sources"))
			{
				NodeList child_ndlist = child_nd.getChildNodes();
				for(int j = 0; j < child_ndlist.getLength(); j++)
				{
					Node child_child_nd = child_ndlist.item(j);
					if(child_child_nd.getNodeName().equals("oda-data-source"))
					{
						NodeList child_child_ndlist = child_child_nd.getChildNodes();
						for(int k = 0; k < child_child_ndlist.getLength(); k++)
						{
							Node child_child_child_nd = child_child_ndlist.item(k);
							if(child_child_child_nd.getNodeName().equals("property"))
							{
								child_child_child_nd.getChildNodes().item(0).setNodeValue(xmlpath);					
							}
						}

					}							
				}
			}
		}

	}

}