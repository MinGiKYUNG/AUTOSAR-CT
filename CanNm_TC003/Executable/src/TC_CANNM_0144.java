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

public class TC_CANNM_0144 extends TC_CANNM {

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

		System.out.println("Test Case: TC_CANNM_0144 Start");
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
				func.mCanNmGetState("VP05", v_NetworkHandle, Nm_StateType.NM_STATE_BUS_SLEEP, Nm_ModeType.NM_MODE_BUS_SLEEP, Nm_ReturnType.NM_E_OK);
				// Step 6 & 7
				v_ChannelIndex = func.mCanNmNetworkRequest("VP07", v_NetworkHandle, NM_E_OK);
				// Step 8
				func.mNmStateChangeNotification("VP08", v_NetworkHandle, Nm_StateType.NM_STATE_BUS_SLEEP, Nm_StateType.NM_STATE_REPEAT_MESSAGE);
				// Step 9 & 10
				func.mCanNmGetState("VP09", v_NetworkHandle, Nm_StateType.NM_STATE_REPEAT_MESSAGE, Nm_ModeType.NM_MODE_NETWORK, Nm_ReturnType.NM_E_OK);
				// Step 11 & 12
				v_ChannelIndex = func.mCanNmNetworkRelease(vp, v_NetworkHandle, NM_E_OK);
				// Step 13
				for(int count1=0; count1 < 30; count1++)	{
				func.mCanNmMainFunction();
				}
				// Step 14
				func.mNmStateChangeNotification("VP14", v_NetworkHandle, Nm_StateType.NM_STATE_REPEAT_MESSAGE, Nm_StateType.NM_STATE_READY_SLEEP);
				// Step 15 & 16
				func.mCanNmGetState("VP15", v_NetworkHandle, Nm_StateType.NM_STATE_READY_SLEEP,	Nm_ModeType.NM_MODE_NETWORK, Nm_ReturnType.NM_E_OK);
				// Step 17
				for(int count1=0; count1 < 30; count1++)	{
					func.mCanNmMainFunction();
					}
				// Step 18
				func.mNmStateChangeNotification("VP18", v_NetworkHandle, Nm_StateType.NM_STATE_READY_SLEEP, Nm_StateType.NM_STATE_PREPARE_BUS_SLEEP);
				// Step 19 & 20
				func.mCanNmGetState("VP19", v_NetworkHandle, Nm_StateType.NM_STATE_PREPARE_BUS_SLEEP, Nm_ModeType.NM_MODE_PREPARE_BUS_SLEEP, Nm_ReturnType.NM_E_OK);
				// Step 21
				for(int count1=0; count1 < 30; count1++)	{
					func.mCanNmMainFunction();
					}
				// Step 22
				func.mNmStateChangeNotification("VP22", v_NetworkHandle, Nm_StateType.NM_STATE_PREPARE_BUS_SLEEP, Nm_StateType.NM_STATE_BUS_SLEEP);
				// Step 23 & 24
				func.mCanNmGetState("VP23", v_NetworkHandle, Nm_StateType.NM_STATE_BUS_SLEEP, Nm_ModeType.NM_MODE_BUS_SLEEP, Nm_ReturnType.NM_E_OK);
			
		}

		endTime = System.currentTimeMillis();
		
		return ((endTime - startTime) / 1000.0);

	}
}
