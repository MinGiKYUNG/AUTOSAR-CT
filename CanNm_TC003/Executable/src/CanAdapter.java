
import java.awt.AWTException;
import java.awt.Robot;

public class CanAdapter {
	static {
		System.loadLibrary("HSCAN8473");
	}

	public native void displayHelloWorld();

	public native int openCanAdapter();

	public native int closeCanAdapter();

	public native byte[] readCanMessage();

	public native int writeCanMessage(int[] message);

	CanAdapter ca;

	public CanAdapter() {

	}

	public void call() {
		 byte[] message = null;
		 // char[] send_message={'s'};
		 String str;
		 int[] send_msg = { 1, 9, 0, 9, 0, 9, 0, 9 };
		
		 // ca.closeCanAdapter();
		 //open
		 System.out.println("open:\t" + openCanAdapter());
		
		 while (true) {
		 // read
		 message = readCanMessage();
		 str = new String(message);
		
		 // write
		 writeCanMessage(send_msg);
		
		 // message
		 System.out.println("Message:\t" + str);

		 // delay
		 Robot tRobot;
		 try {
		 tRobot = new Robot();
		 tRobot.delay(1000);
		 } catch (AWTException e) {
		 e.printStackTrace();
		 }
		
		}
		// ca.closeCanAdapter();
	}

//	int openAdapter(CanAdapter inCA) {
//		ca = inCA;
//		int openRes = ca.openCanAdapter();
//		return openRes;
//	}
//
//	int sendMessage(int[] message) {
//
//		ca.writeCanMessage(message);
//		return 0;
//	}
//
	String receiveMessage() {
		byte[] message = null;
		String str="null";

		message = this.readCanMessage();
		str = new String(message);

		return str;
	}
}
