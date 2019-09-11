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

public class TC_CANNM_0083 extends TC_CANNM {

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

		System.out.println("Test Case: TC_CANNM_0082 Start");
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
		//while (cfg.Select11("CP03", true, v_ChannelIndex)) {
			int v_NetworkHandle;
			v_NetworkHandle = cfg.CTCanNmChannelIdRef(v_ChannelIndex);
			v_ExpCanIfTransmit = func.mCanNmDefaultCanIfTransmitSettings(v_ChannelIndex);

			func.mCanNmGetState("VP05", v_NetworkHandle, Nm_StateType.NM_STATE_BUS_SLEEP, Nm_ModeType.NM_MODE_BUS_SLEEP, Nm_ReturnType.NM_E_OK);
			v_ChannelIndex = func.mCanNmNetworkRequest("VP07", v_NetworkHandle, NM_E_OK);
			func.mCanNmGetState("VP09", v_NetworkHandle, Nm_StateType.NM_STATE_REPEAT_MESSAGE, Nm_ModeType.NM_MODE_NETWORK, Nm_ReturnType.NM_E_OK);
			//func.mCanNmRxIndication(c_NoVP, cfg.CanNmRxPduId(v_ChannelIndex), v_RxSduPtr);

			// Step 10
			func.mCanNmMainFunction();
			//System.out.println("aa");
			v_ChannelIndex++;
			if (v_ChannelIndex > 2) {
				return 0;
			}
		//}

		endTime = System.currentTimeMillis();

		return ((endTime - startTime) / 1000.0);

	}
}
