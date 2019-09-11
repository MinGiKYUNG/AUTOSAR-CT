package autosar.ct.conf;

public class ModuleConfig {

	// CanNmModuleConfig::CTCanNmCommonPublishedInformation
	public int CTCanNmModuleId;
	public int CTCanNmSwMajorVersion;
	public int CTCanNmSwMinorVersion;
	public int CTCanNmSwPatchVersion;
	public int CTCanNmVendorId;
	
	// CanNmModuleConfig::CTCanNmDemInformation
	public int CTCanNmDemEventStatusFailed;
	public int CTCanNmECanIfTransmitError;
	public int CTCanNmEInitFailed;
	public int CTCanNmENetworkTimeout;
	
	// CanNmModuleConfig::CTCanNmGeneralConfig
	public int CTCanNmConfigSetIndex;
	public String CTCanNmRxPduData;
	public float CTCanNmTCMaxTimeout;
	public String CTCanNmTxData;
	
	// CanNmModuleConfig::CanNmGlobalConfig
	public boolean CanNmBusLoadReductionEnabled;
	public boolean CanNmBusSynchronizationEnabled;
	public boolean CanNmComControlEnabled;
	public boolean CanNmImmediateRestartEnabled;
	public boolean CanNmImmediateTxconfEnabled;
	public float CanNmMainFunctionPeriod;
	public boolean CanNmNodeDetectionEnabled;
	public boolean CanNmPassiveModeEnabled;
	public boolean CanNmPduRxIndicationEnabled;
	public boolean CanNmRemoteSleepIndEnabled;
	public boolean CanNmRepeatMsgIndEnabled;
	public boolean CanNmStateChangeIndEnabled;
	public boolean CanNmUserDataEnabled;
	public boolean CanNmVersionInfoApi;
}
