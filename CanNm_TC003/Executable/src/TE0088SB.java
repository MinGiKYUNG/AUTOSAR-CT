import java.io.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

public class TE0088SB{	
/**	 
* @param args	 
* @throws IOException	 
* @throws SAXException	 
* @throws ParserConfigurationException	 
* @throws XPathExpressionException	 
* @throws InvocationTargetException 	 
* @throws NoSuchMethodException 	 
* @throws IllegalAccessException 	 
* @throws InstantiationException 	 
* @throws ClassNotFoundException 	 
* @throws IllegalArgumentException 	 
* @throws SecurityException 	 
*/	
public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, SecurityException, IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {		
	System.out.println("************************************************************");		
	System.out.println("** AUTOSAR Conformance Test Console Viewer		    **");		
	System.out.println("************************************************************");		
	System.out.println("Configuration File:	"+args[0]);		

	// There are test cases.
		double runningTime0 = 0;

	try {			
		File resultFile = new File(args[1]);
		FileOutputStream out = new FileOutputStream(resultFile);
		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?> <TEST_SUITE>";
		str+= "<TIME start=\"2011.3.20 18:20:27\" end=\"2011.3.20 18:21:27\" />";
		str+= "<PARTICIPANTS><MEMBER name=\"aaaa\" role=\"Test Assessor\" e-mail=\"hahah@aaaaa.com\"/>		<MEMBER name=\"aaaa\" role=\"Test Assessor\" e-mail=\"hahah@aaaaa.com\"/>		<MEMBER name=\"aaaa\" role=\"Test Assessor\" e-mail=\"hahah@aaaaa.com\"/>	</PARTICIPANTS>";
		str+= "<TOOL>		<TOOLS tool=\"Attt\" version=\"SVN revision number: 20036\"/>		<TOOLS tool=\"Number of configurations used: 5\" version=\"SVN revision number: 20036\"/>		<TOOLS tool=\"Hardware: Desktop PC, Intel Pentium D CPU E8400 @ 2.80 GHz, 1.00 GB RAM\" version=\"n.a.\"/>		<TOOLS tool=\"Operating system: Microsoft XP Professional\" version=\"2002, Service Pack 2\"/>		<TOOLS tool=\"OpenTTCN (TTCN-3 tool)\" version=\"2.57.6\"/>		<TOOLS tool=\"Java: Java Development Kit (JDK)\" version=\"1.5.0_02\"/>	</TOOL>	<VALIDATION_WORKSHOP>		<ITEMS workproduct=\"CTSPEC\" version=\"1.1.3\"/>		<ITEMS workproduct=\"TTCN-3 Files\" version=\"1.1.1\"/>	</VALIDATION_WORKSHOP>";
		str+= "<TEST_EXECUTION description=\"The script CanNmCheckTN3.bat executed 1for compilation. The script CanNmRunTest.bat is executed for running the test cases. The script CanNmAnalyzeLogs.bat is executed for analyzing the log files.\"/>	<LOG>		<FILE name=\"Logfiles\" svn_rev=\"20320\"/> 	</LOG>";
		str+= "<RESULTS>";
		runningTime0 = TC_CANNM_0088.execute(args[0]);
		if (runningTime0 >= 0)		{
			str+="<TESTCASE ID=\"TC_CANNM_0088\" result=\"PASSED\"/>";
		} else 	{
			str+="<TESTCASE ID=\"TC_CANNM_0088\" result=\"FAILED\"/>";
		}
		str+= "</RESULTS>";
		str+= "</TEST_SUITE>";

		out.write(str.getBytes());			
		out.close();		
	} catch (Exception e) {			
		e.printStackTrace();
	}

		double runningTime= 0 + runningTime0;

		System.out.println("Execution Time:	+" + runningTime);	
	}
}