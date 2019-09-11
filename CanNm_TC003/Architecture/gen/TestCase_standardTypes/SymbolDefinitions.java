package Architecture.gen_java.TestCase_standardTypes;

import java.util.*;

		public final class SymbolDefinitions extends TypeWrapper<Integer> {
			private String literalText = null;
			private static Map<String, SymbolDefinitions> literalTextMap = new HashMap<String, SymbolDefinitions>();
			/**
			 * Symbol definition Chapter 8.2.1 of AUTOSAR_SWS_StandardTypes.
			 * 
			 * @sws STD006
			 */
			public final static SymbolDefinitions E_OK = new SymbolDefinitions(0x00,
					"E_OK");
			/**
			 * Symbol definition Chapter 8.2.1 of AUTOSAR_SWS_StandardTypes.
			 * 
			 * @sws STD006
			 */
			public final static SymbolDefinitions E_NOT_OK = new SymbolDefinitions(
					0x01, "E_NOT_OK");
			/**
			 * Symbol definition Chapter 8.2.2 of AUTOSAR_SWS_StandardTypes.
			 * 
		 * @sws STD007
			 */
			public final static SymbolDefinitions STD_HIGH = new SymbolDefinitions(
					0x01, "STD_HIGH");
			/**
			 * Symbol definition Chapter 8.2.2 of AUTOSAR_SWS_StandardTypes.
			 * 
			 * @sws STD007
			 */
			public final static SymbolDefinitions STD_LOW = new SymbolDefinitions(0x00,
					"STD_LOW");
			/**
			 * Symbol definition Chapter 8.2.3 of AUTOSAR_SWS_StandardTypes.
			 * 
			 * @sws STD013
			 */
			public final static SymbolDefinitions STD_ACTIVE = new SymbolDefinitions(
					0x00, "STD_ACTIVE");
			/**
			 * Symbol definition Chapter 8.2.3 of AUTOSAR_SWS_StandardTypes.
			 * 
			 * @sws STD013
			 */
			public final static SymbolDefinitions STD_IDLE = new SymbolDefinitions(
					0x00, "STD_IDLE");
			/**
			 * Symbol definition Chapter 8.2.4 of AUTOSAR_SWS_StandardTypes.
			 * 
			 * @sws STD010
			 */
			public final static SymbolDefinitions STD_ON = new SymbolDefinitions(0x00,
					"STD_ON");
			/**
			 * Symbol definition Chapter 8.2.4 of AUTOSAR_SWS_StandardTypes.
			 * 
			 * @sws STD010
			 */
			public final static SymbolDefinitions STD_OFF = new SymbolDefinitions(0x01,
					"STD_OFF");
			/**
			 * Constructs a Std_Symbol and assigns a value to it. Private constructor
			 * prevents additional definitions from the outside.
			 * 
			 * @param value
			 */
			private SymbolDefinitions(int value, String literalText) {
				super(new Integer(value));
				this.literalText = literalText;
				literalTextMap.put(literalText, this);
			}
			public String getLiteralText() {
				return this.literalText;
			}
			public static SymbolDefinitions getByLiteralText(String literalText) {
				return literalTextMap.get(literalText);
			}
		}


