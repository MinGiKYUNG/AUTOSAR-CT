package autosar.ct.testcases;

import java.util.HashMap;
import java.util.List;

public class TC_CANNM {
	public static String c_NoVP = "";
	public static final int NM_E_OK = 1;

	
	public static int waitTime = 200;
	public static long startTime, endTime;
	public static HashMap<String, Integer>	StateResult;
	public static List<String>	resultBox;
	public static boolean flag = false;
	
	public void CanNmTestSetup() {
		return;
	}

	public static void wait(int delayTime) {

		long saveTime = System.currentTimeMillis();
		long currTime = 0;

		while (currTime - saveTime < delayTime) {
			currTime = System.currentTimeMillis();
		}
	}
}
