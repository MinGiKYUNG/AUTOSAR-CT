import java.awt.AWTException;
import java.awt.Robot;


public class PTCNm {
	public CanAdapter adapter;
	public byte[] message = null;
	NmPort nmPort;
	public int delayTime = 200;
	
	public PTCNm(CanAdapter ca) {
		adapter = ca;
		nmPort = new NmPort();
	}
	
	class NmPort {
		// send
		public void call_Nm_NetworkStartIndication() {
		}

		public void call_Nm_RemoteSleepIndication() {
		}

		public void call_Nm_RemoteSleepCancellation() {
		}

		public void call_Nm_PduRxIndication() {
		}

		public void call_Nm_PrepareBusSleepMode() {
		}

		public void call_Nm_BusSleepMode() {
		}

		public void call_Nm_NetworkMode() {
		}

		public void call_Nm_StateChangeNotification(int handle, int prevState, int nextState ) {
			int[] send_msg = { 45, 0, 0, 0, 0, handle, prevState, nextState };
//			System.out.print("SCN Tx Msg:\t"); 
//			for (int j = 0; j < 8; j++) {
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

		public void call_Nm_TxTimeoutException() {
		}

		public void call_Nm_RepeatMessageIndication() {
		}
		
		// recv
		public void recv_Nm_NetworkStartIndication() {
		}

		public void recv_Nm_RemoteSleepIndication() {
		}

		public void recv_Nm_RemoteSleepCancellation() {
		}

		public void recv_Nm_PduRxIndication() {
		}

		public void recv_Nm_PrepareBusSleepMode() {
		}

		public void recv_Nm_BusSleepMode() {
		}

		public void recv_Nm_NetworkMode() {
		}

		public String recv_Nm_StateChangeNotification() {
			message = adapter.readCanMessage();
			message = adapter.readCanMessage();
//			 System.out.println("SCN Rx Msg:\t"+message[0]+","+
//					 message[1]+","+ message[2]+","+ message[3]+","+ message[4]+","+
//					 message[5]+","+ message[6]+","+ message[7]);
			String str = new String(message);
			return str;
		}

		public void recv_Nm_TxTimeoutException() {
		}

		public void recv_Nm_RepeatMessageIndication() {
		}
	}
}
