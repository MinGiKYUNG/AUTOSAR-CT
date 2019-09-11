import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import autosar.ct.testcases.TC_CANNM;

public class TRInterface {
	CanAdapter ca;
	MtcCanNm mtc;
	PTCNm ptcNm;

	// for return
	static int mode, state, handle, result;

	public TRInterface() {
		ca = new CanAdapter();
		ca.closeCanAdapter();
		ca.openCanAdapter();

		mtc = new MtcCanNm(ca);
		ptcNm = new PTCNm(ca);
		mtc.initializePort();
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public void triCall(String componentId, String tsiPortId, String SUTAddress, String signatureId,
			HashMap<String, String> parameterList) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException,
			InvocationTargetException {
		if (componentId.equals("MtcCanNm")) {
			if (tsiPortId.equals("CanNmPort")) {
				if (signatureId.equals("CanNm_Init")) {
					int no = 0;
					int integerParameter = 0;
					float floatParameter = 0;
					mtc.canNmPort.call_CanNm_Init(0, 0);
				} else if (signatureId.equals("CanNm_NetworkRequest")) {
					int nh = Integer.parseInt(parameterList.get("NmNetworkHandle"));
					mtc.canNmPort.call_CanNm_NetworkRequest(nh);
				} else if (signatureId.equals("CanNm_NetworkRelease")) {
					int nh = Integer.parseInt(parameterList.get("NmNetworkHandle"));
					mtc.canNmPort.call_CanNm_NetworkRelease(nh);
				} else if (signatureId.equals("CanNm_GetState")) {
					int nh = Integer.parseInt(parameterList.get("NmNetworkHandle"));
					mtc.canNmPort.call_CanNm_GetState(nh);
				} else if (signatureId.equals("CanNm_RxIndication")) {
					mtc.canNmPort.call_CanNm_RxIndication();
				} else if (signatureId.equals("CanNm_MainFunction")) {
					mtc.canNmPort.call_CanNm_MainFunction();
				}
			}
		} else if (componentId.equals("PTCNm")) {
			if (tsiPortId.equals("NmPort")) {
				if (signatureId.equals("Nm_StateChangeNotification")) {
					int handle = Integer.parseInt(parameterList.get("channel"));
					int state1 = Integer.parseInt(parameterList.get("prevState"));
					int state2 = Integer.parseInt(parameterList.get("nextState"));
					ptcNm.nmPort.call_Nm_StateChangeNotification(handle, state1, state2);
					System.out.print("\tSCN():\t"+state1+ " " + state2);
				}
			}
		}
	}

	public void triReply(String componentId, String tsiPortId, String SUTAddress, String signatureId,
			HashMap<String, String> parameterList) {
		if (componentId.equals("MtcCanNm")) {
			if (tsiPortId.equals("CanNmPort")) {
				if (signatureId.equals("CanNm_Init")) {
					String temp = mtc.canNmPort.recv_CanNm_Init();
					result = (int) temp.charAt(2);
				} else if (signatureId.equals("CanNm_NetworkRequest")) {
					String temp = mtc.canNmPort.recv_CanNm_NetworkRequest();
					result = (int) temp.charAt(2);
				} else if (signatureId.equals("CanNm_NetworkRelease")) {
					String temp = mtc.canNmPort.recv_CanNm_NetworkRelease();
					result = (int) temp.charAt(2);

				} else if (signatureId.equals("CanNm_GetState")) {
					String temp = mtc.canNmPort.recv_CanNm_GetState();
					mode = (int) temp.charAt(6);
					state = (int) temp.charAt(7);
					result = (int) temp.charAt(2);
				} else if (signatureId.equals("CanNm_RxIndication")) {
					mtc.canNmPort.call_CanNm_RxIndication();
				} else if (signatureId.equals("CanNm_MainFunction")) {
					mtc.canNmPort.recv_CanNm_MainFunction();
				}
			}
		} else if (componentId.equals("PTCNm")) {
			if (tsiPortId.equals("NmPort")) {
				if (signatureId.equals("Nm_StateChangeNotification")) {
					String temp = ptcNm.nmPort.recv_Nm_StateChangeNotification();
				}
			}
		}

	}
}