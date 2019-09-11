package autosar.ct.conf;

public class ChannelConfig {
	// CanNmChannelConfig
	public boolean CanNmBusLoadReductionActive;
	public int CanNmMsgCycleOffset;
	public float CanNmMsgCycleTime;
	public float CanNmMsgReducedTime;
	public float CanNmMsgTimeoutTime;
	public int CanNmNodeId;
	public String CanNmPduCbvPosition;
	public String CanNmPduNidPosition;
	public float CanNmRemoteSleepIndTime;
	public float CanNmRepeatMessageTime;
	public float CanNmTimeoutTime;
	public int CanNmUserDataLength;
	public float CanNmWaitBusSleepTime;
	
	// CanNmChannelConfig::CTCanNmChannelConfig
	public int CTCanNmChannelIdRef;
	public int CTCanNmNodeId;
	
	// CanNmChannelConfig::CanNmRxPdu
	public int CanNmRxPduId;
	
	// CanNmChannelConfig::CanNmTxPdu::CTCanNmTxPdu
	public int CTCanNmTxPduRef; 
}
