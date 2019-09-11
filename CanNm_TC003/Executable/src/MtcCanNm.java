import java.awt.AWTException;
import java.awt.Robot;

import autosar.ct.type.Nm_ReturnType;
import autosar.ct.type.Std_ReturnType;

public class MtcCanNm {
	// variables
	public CanNmPort canNmPort;
	public TestNmPort testNmPort;
	public TestCanIfPort testCanIfPort;
	public TestMemoryAccessPort testMemoryAccessPort;
	public CanIfPort canIfPort;
	public CanAdapter adapter;
	byte[] message = null;
	public int delayTime = 200;

	// use for out message
	public class CanNmPort {

		// Sender part
		public void call_CanNm_GetVersionInfo() {

		}

		public void call_CanNm_PassiveStartUp() {
			int[] send_msg = { 1, 0, 0, 0, 0, 0, 0, 0 };
			adapter.writeCanMessage(send_msg);
			return;
		}

		public void call_CanNm_Init(int no, int integerParameter) {
			int[] send_msg = { 2, no, 0, 0, 0, 0, integerParameter / 255, integerParameter % 255 };

			// send & wait
			// System.out.println(no+"\t"+integerParameter/255+","+integerParameter%255);
//			 System.out.print("INIT Tx Msg:\t");
//			 for (int j = 0; j < 8; j++) {
//			 System.out.print(send_msg[j] + ", ");
//			 }
//			 System.out.println();
			adapter.writeCanMessage(send_msg);
			Robot tRobot;
			try {
				tRobot = new Robot();
				tRobot.delay(delayTime);
			} catch (AWTException e) {
				e.printStackTrace();
			}
		}

		public void call_CanNm_NetworkRequest(int networkHandle) {
			int[] send_msg = { 3, 0, 0, 0, 0, 0, 0, networkHandle };
			adapter.writeCanMessage(send_msg);
//			 System.out.print("NR Tx Msg:\t");
//			 for (int j = 0; j < 8; j++) {
//			 System.out.print(send_msg[j] + ", ");
//			 }
//			 System.out.println();
			Robot tRobot;
			try {
				tRobot = new Robot();
				tRobot.delay(delayTime);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			return;
		}

		public void call_CanNm_NetworkRelease(int networkHandle) {
			int[] send_msg = { 4, 0, 0, 0, 0, 0, 0, networkHandle };
			adapter.writeCanMessage(send_msg);
			Robot tRobot;
			try {
				tRobot = new Robot();
				tRobot.delay(delayTime);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			return;
		}

		public void call_CanNm_DisableCommunication() {
			int[] send_msg = { 5, 0, 0, 0, 0, 0, 0, 0 };
			adapter.writeCanMessage(send_msg);
			return;
		}

		public void call_CanNm_SetUserData() {
			int[] send_msg = { 6, 0, 0, 0, 0, 0, 0, 0 };
			adapter.writeCanMessage(send_msg);
			return;
		}

		public void call_CanNm_GetUserData() {
			int[] send_msg = { 7, 0, 0, 0, 0, 0, 0, 0 };
			adapter.writeCanMessage(send_msg);
			return;
		}

		public void call_CanNm_GetLocalNodeIdentifier() {
			int[] send_msg = { 8, 0, 0, 0, 0, 0, 0, 0 };
			adapter.writeCanMessage(send_msg);
			return;
		}

		public void call_CanNm_RepeatMessageRequest() {
			int[] send_msg = { 9, 0, 0, 0, 0, 0, 0, 0 };
			adapter.writeCanMessage(send_msg);
			return;
		}

		public void call_CanNm_GetPduData() {
			int[] send_msg = { 10, 0, 0, 0, 0, 0, 0, 0 };
			adapter.writeCanMessage(send_msg);
			return;
		}

		public void call_CanNm_GetState(int networkHandle) {
			int[] send_msg = { 11, 0, 0, 0, 0, 0, 0, networkHandle };
//			 System.out.print("State(Send):\t");
//			 for (int j = 0; j < 8; j++) {
//			 System.out.print(send_msg[j] + ", ");
//			 }
//			 System.out.println();
			adapter.writeCanMessage(send_msg);
			Robot tRobot;
			try {
				tRobot = new Robot();
				tRobot.delay(delayTime);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			return;
		}

		public void call_CanNm_GetNodeIdentifier() {
			int[] send_msg = { 19, 0, 0, 0, 0, 0, 0, 0 };
			adapter.writeCanMessage(send_msg);
			return;
		}

		public void call_CanNm_Transmit() {
			return;
		}

		public void call_CanNm_RequestBusSynchronization() {
			int[] send_msg = { 13, 0, 0, 0, 0, 0, 0, 0 };
			adapter.writeCanMessage(send_msg);
			return;
		}

		public void call_CanNm_CheckRemoteSleepIndication() {
			int[] send_msg = { 14, 0, 0, 0, 0, 0, 0, 0 };
			adapter.writeCanMessage(send_msg);
			return;
		}

		public void call_CanNm_TxConfirmation() {
			int[] send_msg = { 15, 0, 0, 0, 0, 0, 0, 0 };
			adapter.writeCanMessage(send_msg);
		}

		public void call_CanNm_RxIndication() {
			int[] send_msg = { 16, 0, 0, 0, 0, 0, 0, 0 };
			adapter.writeCanMessage(send_msg);
		}

		public void call_CanNm_MainFunction() {
			int[] send_msg = { 17, 0, 0, 0, 0, 0, 0, 0 };
			adapter.writeCanMessage(send_msg);
//			 System.out.print("MF(Send):\t");
//			 for (int j = 0; j < 8; j++) {
//			 System.out.print(send_msg[j] + ", ");
//			 }
//			 System.out.println();
			Robot tRobot;
			try {
				tRobot = new Robot();
				tRobot.delay(delayTime);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			return;
		}

		public void call_CanNm_EnableCommunication() {
			int[] send_msg = { 18, 0, 0, 0, 0, 0, 0, 0 };
			adapter.writeCanMessage(send_msg);
			return;
		}

		// Receiver Part
		public String recv_CanNm_GetVersionInfo() {
			return null;

		}

		public String recv_CanNm_PassiveStartUp() {
			return null;

		}

		public String recv_CanNm_Init() {
			message = adapter.readCanMessage();
			message = adapter.readCanMessage();
//			 System.out.println("INIT Rx Msg:\t"+message[0]+","+
//					 message[1]+","+ message[2]+","+ message[3]+","+ message[4]+","+
//					 message[5]+","+ message[6]+","+ message[7]);
			String str = new String(message);
			return str;
		}

		public String recv_CanNm_NetworkRequest() {
			message = adapter.readCanMessage();
//			 System.out.println("NR Rx Msg:\t"+message[0]+","+
//					 message[1]+","+ message[2]+","+ message[3]+","+ message[4]+","+
//					 message[5]+","+ message[6]+","+ message[7]);
			String str = new String(message);
			return str;

		}

		public String recv_CanNm_NetworkRelease() {
			message = adapter.readCanMessage();
			String str = new String(message);
			return str;

		}

		public String recv_CanNm_DisableCommunication() {
			return null;

		}

		public String recv_CanNm_SetUserData() {
			return null;

		}

		public String recv_CanNm_GetUserData() {
			return null;

		}

		public String recv_CanNm_GetLocalNodeIdentifier() {
			return null;

		}

		public String recv_CanNm_RepeatMessageRequest() {
			return null;
		}

		public String recv_CanNm_GetPduData() {
			return null;

		}

		public String recv_CanNm_GetState() {
			message = adapter.readCanMessage();

//			 System.out.println("CAN Rx Msg:\t"+message[0]+","+
//			 message[1]+","+ message[2]+","+ message[3]+","+ message[4]+","+
//			 message[5]+","+ message[6]+","+ message[7]);
			String str = new String(message);
			return str;
		}

		public String recv_CanNm_GetNodeIdentifier() {
			return null;

		}

		public String recv_CanNm_Transmit() {
			return null;

		}

		public String recv_CanNm_RequestBusSynchronization() {
			return null;

		}

		public String recv_CanNm_CheckRemoteSleepIndication() {
			return null;

		}

		public String recv_CanNm_TxConfirmation() {
			return null;

		}

		public String recv_CanNm_RxIndication() {
			return null;

		}

		public String recv_CanNm_MainFunction() {
			message = adapter.readCanMessage();
			message = adapter.readCanMessage();
//			 System.out.println("MF Rx Msg:\t"+message[0]+","+
//					 message[1]+","+ message[2]+","+ message[3]+","+ message[4]+","+
//					 message[5]+","+ message[6]+","+ message[7]);
			String str = new String(message);
			return str;
		}

		public String recv_CanNm_EnableCommunication() {
			return null;

		}
	}

	// use for inout message
	public class TestNmPort {
		public void TestNm_RemoteSleepCancellation() {
		}

		public void TestNm_RepeatMessageIndication() {
		}

		public void TestNm_NetworkStartIndication() {
		}

		public void TestNm_RemoteSleepIndication() {
		}

		public void TestNm_PduRxIndication() {
		}

		public void TestNm_PrepareBusSleepMode() {
		}

		public void TestNm_BusSleepMode() {
		}

		public void TestNm_NetworkMode() {
		}

		public void TestNm_StateChangeNotification() {
		}

		public void TestNm_TxTimeoutException() {
		}
	}

	// use for inout message
	public class TestCanIfPort {
		public void TestCanIf_Transmit() {
		}

		public void TestCanIf_SetStdRetVal() {
		}

		public void TestCanIf_SetExpCanTxPduId() {
		}
	}

	// use for out message
	public class TestMemoryAccessPort {
		public void TestMemoryAccess_Allocate() {
		}

		public void TestMemoryAccess_Read() {
		}

		public void TestMemoryAccess_Write() {
		}
	}

	// use for in message
	

	// use for in message
	public class CanIfPort {
		public void CanIf_Transmit() {
		}
	}

	public MtcCanNm(CanAdapter ca) {
		adapter = ca;
		int[] send_msg = { 0, 0, 0, 0, 0, 0, 0, 0 };
		adapter.writeCanMessage(send_msg);
	}

	public void initializePort() {
		canNmPort = new CanNmPort();
		testNmPort = new TestNmPort();
		testCanIfPort = new TestCanIfPort();
		testMemoryAccessPort = new TestMemoryAccessPort();
		canIfPort = new CanIfPort();
	}
}
