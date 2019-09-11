import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import autosar.ct.conf.CanNmCfg;
import autosar.ct.conf.ChannelConfig;
import autosar.ct.testcases.TC_CANNM;
import autosar.ct.type.Nm_ModeType;
import autosar.ct.type.Nm_ReturnType;
import autosar.ct.type.Nm_StateType;

public class Functions {
	static TRInterface t;
	public static HashMap<String, String> parameterList;
	public static int handle, mode, state, result;
	public static int time;

	public Functions() {
		t = new TRInterface();
	}

	public void mCanNmTestSetup() {
	}

	public int mCanNmPrepareDefaultRxPdu() {
		return 0;
	}

	public int mInitCanNmChannel() {
		return 0;
	}

	public int mCanNmDefaultCanIfTransmitSettings(int v_ChannelIndex) {
		return 0;
	}

	public static void mCanNmMainFunction() throws SecurityException, IllegalArgumentException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		System.out.print("noVP\t");
		// // send
		t.triCall("MtcCanNm", "CanNmPort", null, "CanNm_MainFunction", null);
		//
		// // recv
		t.triReply("MtcCanNm", "CanNmPort", null, "CanNm_MainFunction", null);
		if (t.result == 0) {
			System.out.println("MainFunc()   OK");
		} else
			time = -1;
		return;
	}

	public static void mCanInit(String vp, CanNmCfg cfg) throws SecurityException, IllegalArgumentException,
			ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		// send
		System.out.print(vp);

		// ChannelConfig cc = cfg.chConf.get(0);
		// HashMap<String, String> parameterList = new HashMap<String,
		// String>();
		// parameterList.put("Active",
		// String.valueOf(cc.CanNmBusLoadReductionActive));
		// parameterList.put("NmNetworkHandle", "0000");
		// parameterList.put("NodeId", String.valueOf(cc.CanNmNodeId));
		// parameterList.put("MainFunctionPeriod",
		// String.valueOf(cfg.modConf.CanNmMainFunctionPeriod));
		// parameterList.put("TimeoutTime",
		// String.valueOf(cc.CanNmTimeoutTime));
		// parameterList.put("RepeatMessageTime",
		// String.valueOf(cc.CanNmRepeatMessageTime));
		// parameterList.put("WaitBusSleepTime",
		// String.valueOf(cc.CanNmWaitBusSleepTime));
		// parameterList.put("MessageCycleTime",
		// String.valueOf(cc.CanNmMsgCycleTime));
		// parameterList.put("MessageCycleOffsetTime",
		// String.valueOf(cc.CanNmMsgCycleOffset));
		// parameterList.put("CanIfPduId", String.valueOf(cc.CanNmRxPduId));
		// parameterList.put("PduLength", "0");
		// parameterList.put("NidPosition", cc.CanNmPduNidPosition);
		// parameterList.put("CbvPosition", cc.CanNmPduCbvPosition);
		// t.triCall("MtcCanNm", "CanNmPort", null, "CanNm_Init",
		// parameterList);
		t.triCall("MtcCanNm", "CanNmPort", null, "CanNm_Init", null);

		// recv
		t.triReply("MtcCanNm", "CanNmPort", null, "CanNm_Init", null);

		if (t.result == 48) // TC receive char variable: 48 means '0' 
		{
			System.out.println("Can_Init():\t\tOK");
		} else
			time = -1;
	}

	public static void mCanNmGetState(String vp, int v_NetworkHandle, Nm_StateType nmState,
			Nm_ModeType nmMode, Nm_ReturnType nmEOk) throws SecurityException, IllegalArgumentException,
			ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		// send
		System.out.print(vp);
		parameterList = new HashMap<String, String>();
		parameterList.put("NmNetworkHandle", String.valueOf(v_NetworkHandle));
		t.triCall("MtcCanNm", "CanNmPort", null, "CanNm_GetState", parameterList);
		parameterList.clear();

		// recv
		t.triReply("MtcCanNm", "CanNmPort", null, "CanNm_GetState", null);
		// recv2: mode
		// recv3: state
		if (t.result == 0) {
			mode = t.mode;
			state = t.state;
		} else
			time = -1;
		
		// evaluate
		if ((nmMode.ordinal() == mode) && (nmState.ordinal() == state)) {
			// TC_CANNM.resultBox.add("OK");
			System.out.println("\tGetStatus()  OK\t" + mode + " " + state + "\t/" + nmMode.toString() + "("+ nmMode.ordinal() +"), " + nmState.toString()+
			"("+nmState.ordinal()+")");
		} else {
			// TC_CANNM.resultBox.add("ERROR");
			System.out.println("\tERROR\t" + mode + " " + state +"\t/" + nmMode.toString() + ", " + nmState.toString());
		}
		return;
	}

	public static int mCanNmNetworkRequest(String vp, int v_NetworkHandle, int nmEOk) throws SecurityException,
			IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException {
		// send
		System.out.print(vp);
		parameterList = new HashMap<String, String>();
		parameterList.put("NmNetworkHandle", String.valueOf(v_NetworkHandle));
		t.triCall("MtcCanNm", "CanNmPort", null, "CanNm_NetworkRequest", parameterList);
		parameterList.clear();
		// recv
		// recv1: networkHandle
		t.triReply("MtcCanNm", "CanNmPort", null, "CanNm_NetworkRequest", null);
		if(t.result ==0)
			System.out.println("\tNetRequest() OK");
		else	{
			System.out.println("\tFail");
				time = -1;
		}
		// evaluate
		return 0;
	}

	public void mCanNmRxIndication(String c_NoVP, int canNmRxPduId, int v_RxSduPtr) throws SecurityException,
			IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException {
		t.triCall("MtcCanNm", "CanNmPort", null, "CanNm_RxIndication", null);
		// recv
		t.triReply("MtcCanNm", "CanNmPort", null, "CanNm_RxIndication", null);
		if(t.result ==0)
			System.out.println("\tRxIndication() OK");
		else	{
			System.out.println("\tRxIndication() Fail");
				time = -1;
		}
	}

	public int mCanNmNetworkRelease(Object vp, int v_NetworkHandle, int nmEOk) throws SecurityException, IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		// send
		System.out.print(vp);
		parameterList = new HashMap<String, String>();
		parameterList.put("NmNetworkHandle", String.valueOf(v_NetworkHandle));
		t.triCall("MtcCanNm", "CanNmPort", null, "CanNm_NetworkRelease", parameterList);
		parameterList.clear();
		// recv
		// recv1: networkHandle
		t.triReply("MtcCanNm", "CanNmPort", null, "CanNm_NetworkRelease", null);
		if(t.result ==0)
			System.out.println("\tReqOK");
		else	{
			System.out.println("\tFail");
			time = -1;
		}
		// evaluate
		return 0;
	}

	public void mNmStateChangeNotification(String vp, int v_NetworkHandle, Nm_StateType previousState,
			Nm_StateType nextState) throws SecurityException, IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		System.out.print(vp);
		parameterList = new HashMap<String, String>();
		parameterList.put("channel", String.valueOf(v_NetworkHandle));
		parameterList.put("prevState", String.valueOf(previousState.ordinal()));
		parameterList.put("nextState", String.valueOf(nextState.ordinal()));
		//System.out.print("\tSCN():\t"+previousState.ordinal()+" "+ nextState.ordinal() );
		t.triCall("PTCNm", "NmPort", null, "Nm_StateChangeNotification", parameterList);
		parameterList.clear();
		// recv
		// recv1: networkHandle
		t.triReply("PTCNm", "NmPort", null, "Nm_StateChangeNotification", null);
		if(t.result==0)
			System.out.println("\tOK");
		else	{
			System.out.println("\tFail");
			time = -1;
		}
	//	System.out.println(previousState + " / "+ nextState);
		// evaluate
		return;

	}

	public void mCanNmRepeatMessageRequest(int v_NetworkHandle) {
		// TODO Auto-generated method stub
		
	}

}