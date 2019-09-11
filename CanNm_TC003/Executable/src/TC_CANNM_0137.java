import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import autosar.ct.conf.CanNmCfg;
import autosar.ct.testcases.TC_CANNM;
import autosar.ct.type.Nm_ModeType;
import autosar.ct.type.Nm_ReturnType;
import autosar.ct.type.Nm_StateType;

public class TC_CANNM_0137 extends TC_CANNM {

	private static Object vp;

	public static double execute(String conf) throws ParserConfigurationException, SAXException, IOException,
			XPathExpressionException, SecurityException, IllegalArgumentException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

		// Local Variables
		// int c_MaxPduLen = 8;
		int v_RxSduPtr;
		int v_ChannelIndex;
		int v_ExpCanIfTransmit = 0;

		// Initialize functions & Data Structures
		CanNmCfg cfg = new CanNmCfg(conf);
		Functions func = new Functions();
		startTime = System.currentTimeMillis();

		System.out.println("Test Case: TC_CANNM_0137 Start");
		// Preamble
		cfg.CanNmPassiveModeEnabled("C1", false);
		// C2
		cfg.PC11("C3");
		func.mCanNmTestSetup();

		// Testbody

		// Step 1
		func.mCanInit(c_NoVP, cfg);
		v_RxSduPtr = func.mCanNmPrepareDefaultRxPdu();
		v_ChannelIndex = func.mInitCanNmChannel();

		// Step 2
		for (int i=0; i < 1; i++)	{
			int v_NetworkHandle;
			v_NetworkHandle = i;
			//v_ExpCanIfTransmit = func.mCanNmDefaultCanIfTransmitSettings(v_ChannelIndex);
			
			System.out.println( "\nChannel:\t"+v_NetworkHandle);
			// Step 3: CanNmRepeatMessageTime > 0 ? proceed , fail
		//	if (cfg.chConf.get(0).CanNmRepeatMessageTime > 0) {
				// Step 4&5
				v_ChannelIndex = func.mCanNmNetworkRequest("VP05", v_NetworkHandle, NM_E_OK);
			
				// Step 6 & 7
				v_ChannelIndex  =func.mCanNmNetworkRelease("VP07", v_NetworkHandle, NM_E_OK);
				
				// Step 8
				for (int count1=0; count1 < 30; count1++)	{
					func.mCanNmMainFunction();
				}
				
				// Step 9 & 10
				func.mCanNmGetState("VP09", v_NetworkHandle, Nm_StateType.NM_STATE_PREPARE_BUS_SLEEP, Nm_ModeType.NM_MODE_PREPARE_BUS_SLEEP, Nm_ReturnType.NM_E_OK);
				// Step 11 & 12
				v_ChannelIndex = func.mCanNmNetworkRequest("VP12", v_NetworkHandle, NM_E_OK);
				// Step 13 & 14
				func.mCanNmGetState("VP14", v_NetworkHandle, Nm_StateType.NM_STATE_REPEAT_MESSAGE,	Nm_ModeType.NM_MODE_NETWORK, Nm_ReturnType.NM_E_OK);
				// Step 15
				for (int count2=0; count2 < 30; count2++)	{
					func.mCanNmMainFunction();
				}
				// Step 16
				//func.mCanIf_Transmit();
		}

		endTime = System.currentTimeMillis();
		
		return ((endTime - startTime) / 1000.0);

	}
}
