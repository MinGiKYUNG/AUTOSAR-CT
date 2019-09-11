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

public class TC_CANNM_0105 extends TC_CANNM {

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

		System.out.println("Test Case: TC_CANNM_0131 Start");
		// Preamble
		cfg.CanNmPassiveModeEnabled("C1", false);
		cfg.CanNmBusLoadReductionEnabled("C2");
		cfg.PC11("C3");
		func.mCanNmTestSetup();

		// Testbody

		// Step 1
		func.mCanInit(c_NoVP, cfg);
		v_RxSduPtr = func.mCanNmPrepareDefaultRxPdu();
		v_ChannelIndex = func.mInitCanNmChannel();

		// Step 2&3
		for (; v_ChannelIndex < 1; v_ChannelIndex++)	{
			int v_NetworkHandle;
			v_NetworkHandle = v_ChannelIndex;
			//v_ExpCanIfTransmit = func.mCanNmDefaultCanIfTransmitSettings(v_ChannelIndex);
			
			System.out.println( "\nChannel:\t"+v_NetworkHandle);
			// Step 4 & 5
			func.mCanNmNetworkRequest("VP05", v_NetworkHandle, NM_E_OK);
			// Step 6 & 7
			func.mCanNmGetState("VP07", v_NetworkHandle, Nm_StateType.NM_STATE_REPEAT_MESSAGE, Nm_ModeType.NM_MODE_NETWORK,
					Nm_ReturnType.NM_E_OK);
			// Step 8
			for(int count1=0; count1 < 30; count1++)	{
				func.mCanNmMainFunction();
				}
			
			// Step 9 & 10
			func.mCanNmGetState("VP10", v_NetworkHandle, Nm_StateType.NM_STATE_NORMAL_OPERATION, Nm_ModeType.NM_MODE_NETWORK,
					Nm_ReturnType.NM_E_OK);
			
			// Step 11 & 12
			func.mCanNmNetworkRelease("VP11", v_NetworkHandle, NM_E_OK);
			
			// Step 13 & 14
			func.mCanNmGetState("VP14", v_NetworkHandle, Nm_StateType.NM_STATE_READY_SLEEP, Nm_ModeType.NM_MODE_NETWORK,
					Nm_ReturnType.NM_E_OK);
			
			// Step 15
			for(int count1=0; count1 < 30; count1++)	{
				func.mCanNmMainFunction();
				}
			
			// Step 16
			//func.mCanNmRxIndication(conf, canNmRxPduId, v_RxSduPtr);
			
			// Step 17
			for(int count1=0; count1 < 30; count1++)	{
				func.mCanNmMainFunction();
				}
			
			// Step 18
			//func.mCanIf_Transmit();
			
			// Step 19 & 20
			func.mCanNmNetworkRequest("VP20", v_NetworkHandle, NM_E_OK);
			
			// Step 21 & 22
			func.mCanNmGetState("VP22", v_NetworkHandle, Nm_StateType.NM_STATE_NORMAL_OPERATION, Nm_ModeType.NM_MODE_NETWORK,
					Nm_ReturnType.NM_E_OK);
			
			// Step 23
			for(int count1=0; count1 < 30; count1++)	{
				func.mCanNmMainFunction();
				}
			
			// Step 24
			//func.mCanIf_Transmit();
			
		}
		endTime = System.currentTimeMillis();

		return ((endTime - startTime) / 1000.0);
	}
}
