package autosar.ct.type;

public enum SUTMETHOD {
	CanNm_PassiveStartUp,
	CanNm_Init,
	CanNm_NetworkRequest,
	CanNm_NetworkRelease,
	CanNm_DisableCommunication,
	CanNm_SetUserData,
	CanNm_GetUserData,
	CanNm_GetLocalNodeIdentifier,
	CanNm_RepeatMessageRequest,
	CanNm_GetPduData,
	CanNm_GetState,
	CanNm_GetVersionInfo,
	CanNm_RequestBusSynchronization,
	CanNm_CheckRemoteSleepIndication,
	CanNm_TxConfirmation,
	CanNm_RxIndication,
	CanNm_MainFunction,
	CanNm_EnableCommunication,
	CanNm_GetNodeIdentifier,
	TestNm_RemoteSleepCancellation,
	TestNm_RepeatMessageIndication,
	TestNm_NetworkStartIndication,
	TestNm_RemoteSleepIndication,
	TestNm_PduRxIndication,
	TestNm_PrepareBusSleepMode,
	TestNm_NetworkMode,
	TestNm_StateChangeNotification,
	TestNm_TxTimeoutException,
	TestCanIf_Transmit,
	TestCanIf_SetStdRetVal,
	TestCanIf_SetExpCanTxPduId,
	Dem_ReportErrorStatus,
	Det_ReportError,
	TestMemoryAccess_Allocate,
	TestMemoryAccess_Read,
	TestMemoryAccess_Write,
	CanIf_Transmit,
	Nm_NetworkStartIndication,
	Nm_RemoteSleepCancellation,
	Nm_RepeatMessageIndication,
	Nm_PrepareBusSleepMode,
	Nm_BusSleepMode,
	Nm_NetworkMode,
	Nm_PduRxIndication,
	Nm_StateChangeNotification,
	Nm_TxTimeoutException
}
