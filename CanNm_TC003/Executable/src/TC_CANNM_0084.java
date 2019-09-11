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

public class TC_CANNM_0084 extends TC_CANNM {

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

		System.out.println("Test Case: TC_CANNM_0084 Start");
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

		for (int i=0; i < 1; i++)	{
			int v_NetworkHandle;
			v_NetworkHandle = i;
			//v_ExpCanIfTransmit = func.mCanNmDefaultCanIfTransmitSettings(v_ChannelIndex);
			
			System.out.println( "\nChannel:\t"+v_NetworkHandle);
			// Step 3: CanNmRepeatMessageTime > 0 ? proceed , fail
		//	if (cfg.chConf.get(0).CanNmRepeatMessageTime > 0) {
				// Step 4&5
				v_ChannelIndex = func.mCanNmNetworkRequest("VP05", v_NetworkHandle, NM_E_OK);
			
				// Step 6
				//func.mNmNetworkMode(v_ChannelIndex);
				
				// Step 7 & 8
				func.mCanNmGetState("VP08", v_NetworkHandle, Nm_StateType.NM_STATE_REPEAT_MESSAGE, Nm_ModeType.NM_MODE_NETWORK, Nm_ReturnType.NM_E_OK);
				
				// Step 9 & 10
				v_ChannelIndex = func.mCanNmNetworkRelease("VP10", v_NetworkHandle, NM_E_OK);
				
				// Step 11
				for (int count1=0; count1 < 30; count1++)	{
					func.mCanNmMainFunction();
				}
				
				// Step 12 & 13
				func.mCanNmGetState("VP13", v_NetworkHandle, Nm_StateType.NM_STATE_PREPARE_BUS_SLEEP, Nm_ModeType.NM_MODE_PREPARE_BUS_SLEEP, Nm_ReturnType.NM_E_OK);
				
				// Step 14
				for (int count1=0; count1 < 30; count1++)	{
					func.mCanNmMainFunction();
				}
				
				// Step 15
				//func.mNmPrepareBusSleepMode(v_ChannelIndex);
				
				// Step 16 & 17
				func.mCanNmGetState("VP17", v_NetworkHandle, Nm_StateType.NM_STATE_PREPARE_BUS_SLEEP,	Nm_ModeType.NM_MODE_PREPARE_BUS_SLEEP, Nm_ReturnType.NM_E_OK);
				
				// Step 18 & 19
				v_ChannelIndex = func.mCanNmNetworkRequest("VP19", v_NetworkHandle, NM_E_OK);
				
				// Step 20
				//func.mCanIf_Transmit(CTCanNmTxPduRef, PduInfoPtr);
				
				// Step 21 & 22
				func.mCanNmGetState("VP22", v_NetworkHandle, Nm_StateType.NM_STATE_REPEAT_MESSAGE,	Nm_ModeType.NM_MODE_NETWORK, Nm_ReturnType.NM_E_OK);
				

		}

		endTime = System.currentTimeMillis();

		return ((endTime - startTime) / 1000.0);

	}
}
