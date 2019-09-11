package dms.konkuk.autosar.ct.codegen;


import java.util.Map;


public class TempSource {
	Map<String,String> sources = new java.util.HashMap<String,String>();
	public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
	StringBuffer[] stringBuffer = new StringBuffer[8];
	protected static String nl;

	public static synchronized TempSource create(String lineSeparator)
	{
		nl = lineSeparator;
		TempSource result = new TempSource();
		nl = null;
		return result;
	}
	
	public String getsource(String cname){
		if(sources.containsKey(cname))
		{
			return sources.get(cname);
		}
		return null;
	}

	public TempSource()
	{
		for(int i = 0; i<8; i++){
			stringBuffer[i] = new StringBuffer();
		}

		stringBuffer[0].append("public class CTEaGeneral {" + NL);
		stringBuffer[0].append("public static String CTEaEepEraseInvoke;"+ NL);
		stringBuffer[0].append("public static String CTEaEepInvalidateInvoke;"+ NL);
		stringBuffer[0].append("public static int CTEaEepSpecifiedEraseCycles;"+ NL);
		stringBuffer[0].append("public static float CTEaMainFuncCyclePeriod;"+ NL);
		stringBuffer[0].append("public static int CTEaOctStrData;"+ NL);
		stringBuffer[0].append("public static float CTEaPollingDelay;"+ NL);
		stringBuffer[0].append("public static float CTEaTSMaxTimeout;"+ NL);
		stringBuffer[0].append("public CTEaGeneral(){}"+ NL);
		stringBuffer[0].append("public void setCTEaEepEraseInvoke(String CTEaEepEraseInvoke){"+ NL);
		stringBuffer[0].append("this.CTEaEepEraseInvoke=CTEaEepEraseInvoke;"+ NL);
		stringBuffer[0].append("}"+ NL);
		stringBuffer[0].append("public String getCTEaEepEraseInvoke(){"+ NL);
		stringBuffer[0].append("return this.CTEaEepEraseInvoke;"+ NL);
		stringBuffer[0].append("}"+ NL);
		stringBuffer[0].append("public void setCTEaEepInvalidateInvoke(String CTEaEepInvalidateInvoke){"+ NL);
		stringBuffer[0].append("this.CTEaEepInvalidateInvoke=CTEaEepInvalidateInvoke;		"+ NL);
		stringBuffer[0].append("}"+ NL);
		stringBuffer[0].append("public String getCTEaEepInvalidateInvoke(){"+ NL);
		stringBuffer[0].append("return this.CTEaEepInvalidateInvoke;"+ NL);
		stringBuffer[0].append("}"+ NL);
		stringBuffer[0].append("public void setCTEaEepSpecifiedEraseCycles(int CTEaEepSpecifiedEraseCycles){"+ NL);
		stringBuffer[0].append("this.CTEaEepSpecifiedEraseCycles=CTEaEepSpecifiedEraseCycles;"+ NL);
		stringBuffer[0].append("}"+ NL);
		stringBuffer[0].append("public int getCTEaEepSpecifiedEraseCycles(){"+ NL);
		stringBuffer[0].append("return this.CTEaEepSpecifiedEraseCycles;"+ NL);
		stringBuffer[0].append("}"+ NL);
		stringBuffer[0].append("public void setCTEaMainFuncCyclePeriod(float CTEaMainFuncCyclePeriod){"+ NL);
		stringBuffer[0].append("this.CTEaMainFuncCyclePeriod=CTEaMainFuncCyclePeriod;"+ NL);
		stringBuffer[0].append("}"+ NL);
		stringBuffer[0].append("public float getCTEaMainFuncCyclePeriod(){"+ NL);
		stringBuffer[0].append("return this.CTEaMainFuncCyclePeriod;"+ NL);
		stringBuffer[0].append("}"+ NL);
		stringBuffer[0].append("public void setCTEaOctStrData(int CTEaOctStrData){"+ NL);
		stringBuffer[0].append("this.CTEaOctStrData=CTEaOctStrData;"+ NL);
		stringBuffer[0].append("}"+ NL);
		stringBuffer[0].append("public int getCTEaOctStrData(){"+ NL);
		stringBuffer[0].append("return this.CTEaOctStrData;"+ NL);
		stringBuffer[0].append("}"+ NL);
		stringBuffer[0].append("public void setCTEaPollingDelay(float CTEaPollingDelay){"+ NL);
		stringBuffer[0].append("this.CTEaPollingDelay=CTEaPollingDelay;"+ NL);
		stringBuffer[0].append("}"+ NL);
		stringBuffer[0].append("public float getCTEaPollingDelay(){"+ NL);
		stringBuffer[0].append("return this.CTEaPollingDelay;"+ NL);
		stringBuffer[0].append("}"+ NL);
		stringBuffer[0].append("public void setCTEaTSMaxTimeout(float CTEaTSMaxTimeout){"+ NL);
		stringBuffer[0].append("this.CTEaTSMaxTimeout=CTEaTSMaxTimeout;"+ NL);
		stringBuffer[0].append("}"+ NL);
		stringBuffer[0].append("public float getCTEaTSMaxTimeout(){"+ NL);
		stringBuffer[0].append("return this.CTEaTSMaxTimeout;"+ NL);
		stringBuffer[0].append("}"+ NL);
		stringBuffer[0].append("}"+ NL);


		stringBuffer[1].append("public class EaBlockConfiguration {"+ NL);
		stringBuffer[1].append("private static int CTEaBlockOffset;"+ NL);
		stringBuffer[1].append("private static int EaBlockNumber;"+ NL);
		stringBuffer[1].append("private static int EaBlockSize;"+ NL);
		stringBuffer[1].append("private static String EaImmediateData;"+ NL);
		stringBuffer[1].append("private static int EaNumberOfWriteCycles;"+ NL);
		stringBuffer[1].append("public EaBlockConfiguration(){}"+ NL);
		stringBuffer[1].append("public void setCTEaBlockOffset(int CTEaBlockOffset){"+ NL);
		stringBuffer[1].append("	this.CTEaBlockOffset=CTEaBlockOffset;}"+ NL);
		stringBuffer[1].append("public void setEaBlockNumber(int EaBlockNumber){"+ NL);
		stringBuffer[1].append("	this.EaBlockNumber=EaBlockNumber;}"+ NL);
		stringBuffer[1].append("public void setEaBlockSize(int EaBlockSize){"+ NL);
		stringBuffer[1].append("	this.EaBlockSize=EaBlockSize;}"+ NL);
		stringBuffer[1].append("public void setEaImmediateData(String EaImmediateData){"+ NL);
		stringBuffer[1].append("this.EaImmediateData=EaImmediateData;}"+ NL);
		stringBuffer[1].append("public void setEaNumberOfWriteCycles(int EaNumberOfWriteCycles){"+ NL);
		stringBuffer[1].append("this.EaNumberOfWriteCycles=EaNumberOfWriteCycles;}"+ NL);
		stringBuffer[1].append("public int getCTEaBlockOffset(){"+ NL);
		stringBuffer[1].append("	return this.CTEaBlockOffset;}"+ NL);
		stringBuffer[1].append("public int getEaBlockNumber(){"+ NL);
		stringBuffer[1].append("return this.EaBlockNumber;}"+ NL);
		stringBuffer[1].append("public int getEaBlockSize(){"+ NL);
		stringBuffer[1].append("return this.EaBlockSize;}"+ NL);
		stringBuffer[1].append("public String getEaImmediateData(){"+ NL);
		stringBuffer[1].append("return this.EaImmediateData;}"+ NL);
		stringBuffer[1].append("public int getEaNumberOfWriteCycles(){"+ NL);
		stringBuffer[1].append("return this.EaNumberOfWriteCycles;}"+ NL);
		stringBuffer[1].append("}"+ NL);

		stringBuffer[2].append("public class EaGeneral {"+ NL);
		stringBuffer[2].append("public static String EaNvmJobEndNotification;"+ NL);
		stringBuffer[2].append("public static String EaNvmJobErrorNotification;"+ NL);
		stringBuffer[2].append("public static String EaPollingMode;"+ NL);
		stringBuffer[2].append("public static String EaSetModeSupported;"+ NL);
		stringBuffer[2].append("public static String EaVersionInfoApi;"+ NL);
		stringBuffer[2].append("public EaGeneral(){}"+ NL);
		stringBuffer[2].append("public void setEaNvmJobEndNotification(String EaNvmJobEndNotification){"+ NL);
		stringBuffer[2].append("this.EaNvmJobEndNotification=EaNvmJobEndNotification;}"+ NL);
		stringBuffer[2].append("public String getEaNvmJobEndNotification(){"+ NL);
		stringBuffer[2].append("	return this.EaNvmJobEndNotification;}"+ NL);
		stringBuffer[2].append("public void setEaNvmJobErrorNotification(String EaNvmJobErrorNotification){"+ NL);
		stringBuffer[2].append("	this.EaNvmJobErrorNotification=EaNvmJobErrorNotification;}"+ NL);
		stringBuffer[2].append("public String getEaNvmJobErrorNotification(){"+ NL);
		stringBuffer[2].append("	return this.EaNvmJobErrorNotification;}"+ NL);
		stringBuffer[2].append("public void setEaPollingMode(String EaPollingMode){"+ NL);
		stringBuffer[2].append("this.EaPollingMode=EaPollingMode;}"+ NL);
		stringBuffer[2].append("public String getEaPollingMode(){"+ NL);
		stringBuffer[2].append("return this.EaPollingMode;}"+ NL);
		stringBuffer[2].append("public void setEaSetModeSupported(String EaSetModeSupported){"+ NL);
		stringBuffer[2].append("	this.EaSetModeSupported=EaSetModeSupported;}"+ NL);
		stringBuffer[2].append("public String getEaSetModeSupported(){"+ NL);
		stringBuffer[2].append("	return this.EaSetModeSupported;}"+ NL);
		stringBuffer[2].append("public void setEaVersionInfoApi(String EaVersionInfoApi){"+ NL);
		stringBuffer[2].append("this.EaVersionInfoApi=EaVersionInfoApi;}"+ NL);
		stringBuffer[2].append("public String getEaVersionInfoApi(){"+ NL);
		stringBuffer[2].append("		return this.EaVersionInfoApi;}"+ NL);
		stringBuffer[2].append("}"+ NL);

		stringBuffer[3].append("public class Std_ReturnType extends TypeWrapper<Integer> {"+ NL);
		stringBuffer[3].append("private String literalText = null;"+ NL);
		stringBuffer[3].append("private static Map<String, Std_ReturnType> literalTextMap = new HashMap<String, Std_ReturnType>();"+ NL);
		stringBuffer[3].append("public final static Std_ReturnType E_OK = new Std_ReturnType("+ NL);
		stringBuffer[3].append("	SymbolDefinitions.E_OK, SymbolDefinitions.E_OK.getLiteralText());"+ NL);
		stringBuffer[3].append("public final static Std_ReturnType E_NOT_OK = new Std_ReturnType("+ NL);
		stringBuffer[3].append("SymbolDefinitions.E_NOT_OK, SymbolDefinitions.E_NOT_OK"+ NL);
		stringBuffer[3].append("	.getLiteralText());"+ NL);
		stringBuffer[3].append("public Std_ReturnType(SymbolDefinitions value, String literalText) {"+ NL);
		stringBuffer[3].append("	super(value.getValue());"+ NL);
		stringBuffer[3].append("	this.literalText = literalText;"+ NL);
		stringBuffer[3].append("	literalTextMap.put(literalText, this);"+ NL);
		stringBuffer[3].append("}"+ NL);
		stringBuffer[3].append("public Std_ReturnType(int value, String literalText) {"+ NL);
		stringBuffer[3].append("super(new Integer(value));"+ NL);
		stringBuffer[3].append("this.literalText = literalText;"+ NL);
		stringBuffer[3].append("literalTextMap.put(literalText, this);"+ NL);
		stringBuffer[3].append("}"+ NL);
		stringBuffer[3].append("public Std_ReturnType(int value) {"+ NL);
		stringBuffer[3].append("super(new Integer(value));"+ NL);
		stringBuffer[3].append("}"+ NL);
		stringBuffer[3].append("public String getLiteralText() {"+ NL);
		stringBuffer[3].append("return this.literalText;"+ NL);
		stringBuffer[3].append("}"+ NL);
		stringBuffer[3].append("public static Std_ReturnType getByLiteralText(String literalText) {"+ NL);
		stringBuffer[3].append("return literalTextMap.get(literalText);"+ NL);
		stringBuffer[3].append("	}"+ NL);
		stringBuffer[3].append("}"+ NL);


		stringBuffer[4].append("public class Std_VersionInfoType {"+ NL);
		stringBuffer[4].append("    protected int vendorId;  "+ NL);  
		stringBuffer[4].append("   protected int moduleId;"+ NL);
		stringBuffer[4].append("   protected int instanceId;"+ NL);
		stringBuffer[4].append("   protected int sw_major_version;"+ NL);
		stringBuffer[4].append("   protected int sw_minor_version;"+ NL);
		stringBuffer[4].append("   protected int sw_patch_version;"+ NL);
		stringBuffer[4].append("		public Std_VersionInfoType() {}"+ NL);
		stringBuffer[4].append("		public Std_VersionInfoType(int vendorId, int instanceId) {"+ NL);
		stringBuffer[4].append("			this.vendorId = vendorId;"+ NL);
		stringBuffer[4].append("			this.instanceId = instanceId;}"+ NL);
		stringBuffer[4].append("		/**"+ NL);
		stringBuffer[4].append("		 * @return the vendorId */"+ NL);
		stringBuffer[4].append("		public int getVendorId() {"+ NL);
		stringBuffer[4].append("			return vendorId;}"+ NL);
		stringBuffer[4].append("		/**"+ NL);
		stringBuffer[4].append("		 * @param vendorId the vendorId to set */"+ NL);
		stringBuffer[4].append("		public void setVendorId(int vendorId) {"+ NL);
		stringBuffer[4].append("			this.vendorId = vendorId;}"+ NL);
		stringBuffer[4].append("		/**"+ NL);
		stringBuffer[4].append("		* @return the moduleId*/"+ NL);
		stringBuffer[4].append("		public int getModuleId() {"+ NL);
		stringBuffer[4].append("			return moduleId;}"+ NL);
		stringBuffer[4].append("		/**"+ NL);
		stringBuffer[4].append("		 * @param moduleId the moduleId to set */"+ NL);
		stringBuffer[4].append("		public void setModuleId(int moduleId) {"+ NL);
		stringBuffer[4].append("			this.moduleId = moduleId;}"+ NL);
		stringBuffer[4].append("		/**"+ NL);
		stringBuffer[4].append("		 * @return the instanceId */"+ NL);
		stringBuffer[4].append("		public int getInstanceId() {"+ NL);
		stringBuffer[4].append("			return instanceId;}"+ NL);
		stringBuffer[4].append("		/**"+ NL);
		stringBuffer[4].append("		 * @param instanceId the instanceId to set */"+ NL);
		stringBuffer[4].append("		public void setInstanceId(int instanceId) {"+ NL);
		stringBuffer[4].append("			this.instanceId = instanceId;}"+ NL);
		stringBuffer[4].append("		/**"+ NL);
		stringBuffer[4].append("		 * @return the sw_major_version */"+ NL);
		stringBuffer[4].append("		public int getSw_major_version() {"+ NL);
		stringBuffer[4].append("			return sw_major_version;}"+ NL);
		stringBuffer[4].append("		/**"+ NL);
		stringBuffer[4].append("		 * @param sw_major_version the sw_major_version to set */"+ NL);
		stringBuffer[4].append("		public void setSw_major_version(int sw_major_version) {"+ NL);
		stringBuffer[4].append("			this.sw_major_version = sw_major_version;}"+ NL);
		stringBuffer[4].append("		/**"+ NL);
		stringBuffer[4].append("		 * @return the sw_minor_version */"+ NL);
		stringBuffer[4].append("		public int getSw_minor_version() {"+ NL);
		stringBuffer[4].append("			return sw_minor_version;}"+ NL);
		stringBuffer[4].append("		/**"+ NL);
		stringBuffer[4].append("	 * @param sw_minor_version the sw_minor_version to set */"+ NL);
		stringBuffer[4].append("		public void setSw_minor_version(int sw_minor_version) {"+ NL);
		stringBuffer[4].append("			this.sw_minor_version = sw_minor_version;}"+ NL);
		stringBuffer[4].append("		/**"+ NL);
		stringBuffer[4].append("		 * @return the sw_patch_version */"+ NL);
		stringBuffer[4].append("		public int getSw_patch_version() {"+ NL);
		stringBuffer[4].append("			return sw_patch_version;}"+ NL);
		stringBuffer[4].append("		/**"+ NL);
		stringBuffer[4].append("		 * @param sw_patch_version the sw_patch_version to set*/"+ NL);
		stringBuffer[4].append("		public void setSw_patch_version(int sw_patch_version) {"+ NL);
		stringBuffer[4].append("			this.sw_patch_version = sw_patch_version;}"+ NL);
		stringBuffer[4].append("	}"+ NL);



		stringBuffer[5].append("		public final class SymbolDefinitions extends TypeWrapper<Integer> {"+ NL);
		stringBuffer[5].append("			private String literalText = null;"+ NL);
		stringBuffer[5].append("			private static Map<String, SymbolDefinitions> literalTextMap = new HashMap<String, SymbolDefinitions>();"+ NL);
		stringBuffer[5].append("			/**"+ NL);
		stringBuffer[5].append("			 * Symbol definition Chapter 8.2.1 of AUTOSAR_SWS_StandardTypes."+ NL);
		stringBuffer[5].append("			 * "+ NL);
		stringBuffer[5].append("			 * @sws STD006"+ NL);
		stringBuffer[5].append("			 */"+ NL);
		stringBuffer[5].append("			public final static SymbolDefinitions E_OK = new SymbolDefinitions(0x00,"+ NL);
		stringBuffer[5].append("					\"E_OK\");"+ NL);
		stringBuffer[5].append("			/**"+ NL);
		stringBuffer[5].append("			 * Symbol definition Chapter 8.2.1 of AUTOSAR_SWS_StandardTypes."+ NL);
		stringBuffer[5].append("			 * "+ NL);
		stringBuffer[5].append("			 * @sws STD006"+ NL);
		stringBuffer[5].append("			 */"+ NL);
		stringBuffer[5].append("			public final static SymbolDefinitions E_NOT_OK = new SymbolDefinitions("+ NL);
		stringBuffer[5].append("					0x01, \"E_NOT_OK\");"+ NL);
		stringBuffer[5].append("			/**"+ NL);
		stringBuffer[5].append("			 * Symbol definition Chapter 8.2.2 of AUTOSAR_SWS_StandardTypes."+ NL);
		stringBuffer[5].append("			 * "+ NL);
		stringBuffer[5].append("		 * @sws STD007"+ NL);
		stringBuffer[5].append("			 */"+ NL);
		stringBuffer[5].append("			public final static SymbolDefinitions STD_HIGH = new SymbolDefinitions("+ NL);
		stringBuffer[5].append("					0x01, \"STD_HIGH\");"+ NL);
		stringBuffer[5].append("			/**"+ NL);
		stringBuffer[5].append("			 * Symbol definition Chapter 8.2.2 of AUTOSAR_SWS_StandardTypes."+ NL);
		stringBuffer[5].append("			 * "+ NL);
		stringBuffer[5].append("			 * @sws STD007"+ NL);
		stringBuffer[5].append("			 */"+ NL);
		stringBuffer[5].append("			public final static SymbolDefinitions STD_LOW = new SymbolDefinitions(0x00,"+ NL);
		stringBuffer[5].append("					\"STD_LOW\");"+ NL);
		stringBuffer[5].append("			/**"+ NL);
		stringBuffer[5].append("			 * Symbol definition Chapter 8.2.3 of AUTOSAR_SWS_StandardTypes."+ NL);
		stringBuffer[5].append("			 * "+ NL);
		stringBuffer[5].append("			 * @sws STD013"+ NL);
		stringBuffer[5].append("			 */"+ NL);
		stringBuffer[5].append("			public final static SymbolDefinitions STD_ACTIVE = new SymbolDefinitions("+ NL);
		stringBuffer[5].append("					0x00, \"STD_ACTIVE\");"+ NL);
		stringBuffer[5].append("			/**"+ NL);
		stringBuffer[5].append("			 * Symbol definition Chapter 8.2.3 of AUTOSAR_SWS_StandardTypes."+ NL);
		stringBuffer[5].append("			 * "+ NL);
		stringBuffer[5].append("			 * @sws STD013"+ NL);
		stringBuffer[5].append("			 */"+ NL);
		stringBuffer[5].append("			public final static SymbolDefinitions STD_IDLE = new SymbolDefinitions("+ NL);
		stringBuffer[5].append("					0x00, \"STD_IDLE\");"+ NL);
		stringBuffer[5].append("			/**"+ NL);
		stringBuffer[5].append("			 * Symbol definition Chapter 8.2.4 of AUTOSAR_SWS_StandardTypes."+ NL);
		stringBuffer[5].append("			 * "+ NL);
		stringBuffer[5].append("			 * @sws STD010"+ NL);
		stringBuffer[5].append("			 */"+ NL);
		stringBuffer[5].append("			public final static SymbolDefinitions STD_ON = new SymbolDefinitions(0x00,"+ NL);
		stringBuffer[5].append("					\"STD_ON\");"+ NL);
		stringBuffer[5].append("			/**"+ NL);
		stringBuffer[5].append("			 * Symbol definition Chapter 8.2.4 of AUTOSAR_SWS_StandardTypes."+ NL);
		stringBuffer[5].append("			 * "+ NL);
		stringBuffer[5].append("			 * @sws STD010"+ NL);
		stringBuffer[5].append("			 */"+ NL);
		stringBuffer[5].append("			public final static SymbolDefinitions STD_OFF = new SymbolDefinitions(0x01,"+ NL);
		stringBuffer[5].append("					\"STD_OFF\");"+ NL);
		stringBuffer[5].append("			/**"+ NL);
		stringBuffer[5].append("			 * Constructs a Std_Symbol and assigns a value to it. Private constructor"+ NL);
		stringBuffer[5].append("			 * prevents additional definitions from the outside."+ NL);
		stringBuffer[5].append("			 * "+ NL);
		stringBuffer[5].append("			 * @param value"+ NL);
		stringBuffer[5].append("			 */"+ NL);
		stringBuffer[5].append("			private SymbolDefinitions(int value, String literalText) {"+ NL);
		stringBuffer[5].append("				super(new Integer(value));"+ NL);
		stringBuffer[5].append("				this.literalText = literalText;"+ NL);
		stringBuffer[5].append("				literalTextMap.put(literalText, this);"+ NL);
		stringBuffer[5].append("			}"+ NL);
		stringBuffer[5].append("			public String getLiteralText() {"+ NL);
		stringBuffer[5].append("				return this.literalText;"+ NL);
		stringBuffer[5].append("			}"+ NL);
		stringBuffer[5].append("			public static SymbolDefinitions getByLiteralText(String literalText) {"+ NL);
		stringBuffer[5].append("				return literalTextMap.get(literalText);"+ NL);
		stringBuffer[5].append("			}"+ NL);
		stringBuffer[5].append("		}"+ NL);

		stringBuffer[6].append("public class ServiceId {"+ NL);
		stringBuffer[6].append("private int value;"+ NL);
		stringBuffer[6].append("public ServiceId(final int value) {"+ NL);
		stringBuffer[6].append("this.value = value;"+ NL);
		stringBuffer[6].append("}"+ NL);
		stringBuffer[6].append("public final int getValue() {"+ NL);
		stringBuffer[6].append("return value;"+ NL);
		stringBuffer[6].append("}"+ NL);
		stringBuffer[6].append("}"+ NL);

		stringBuffer[7].append("public class TypeWrapper<T> {"+ NL);
		stringBuffer[7].append("private T value;"+ NL);
		stringBuffer[7].append("public TypeWrapper() {"+ NL);
		stringBuffer[7].append("}"+ NL);
		stringBuffer[7].append("public TypeWrapper(T value) {"+ NL);
		stringBuffer[7].append("	this.value = value;"+ NL);
		stringBuffer[7].append("}"+ NL);
		stringBuffer[7].append("public T getValue() {"+ NL);
		stringBuffer[7].append("return this.value;"+ NL);
		stringBuffer[7].append("	}"+ NL);
		stringBuffer[7].append("	public void setValue(T value) {"+ NL);
		stringBuffer[7].append("this.value = value;"+ NL);
		stringBuffer[7].append("	}"+ NL);
		stringBuffer[7].append("	}"+ NL);

		sources.put("CTEaGeneral",stringBuffer[0].toString());
		sources.put("EaBlockConfiguration",stringBuffer[1].toString());
		sources.put("EaGeneral",stringBuffer[2].toString());
		sources.put("Std_ReturnType",stringBuffer[3].toString());
		sources.put("Std_VersionInfoType",stringBuffer[4].toString());
		sources.put("SymbolDefinitions",stringBuffer[5].toString());
		sources.put("Serviceid",stringBuffer[6].toString());
		sources.put("TypeWrapper",stringBuffer[7].toString());
	}

}
