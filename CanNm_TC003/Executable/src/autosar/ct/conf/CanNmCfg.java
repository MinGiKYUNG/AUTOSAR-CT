package autosar.ct.conf;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CanNmCfg {
	DocumentBuilderFactory domFactory;
	DocumentBuilder builder;
	Document doc;
	XPathFactory factory;
	XPath xpath;
	XPathExpression expr;
	Object result;
	NodeList nodes;
	int sizeOfCanNmChannelConfig = 0;
	public ModuleConfig modConf;
	public ArrayList<ChannelConfig> chConf;

	public CanNmCfg(String conf) throws ParserConfigurationException,
			SAXException, IOException, XPathExpressionException {

		modConf = new ModuleConfig();
		chConf = new ArrayList<ChannelConfig>();

		domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		builder = domFactory.newDocumentBuilder();
		doc = builder.parse(conf);

		factory = XPathFactory.newInstance();
		xpath = factory.newXPath();
		expr = xpath.compile("//CanNmGlobalConfig");
		result = expr.evaluate(doc, XPathConstants.NODESET);
		nodes = (NodeList) result;

		config();
	}

	public boolean ItrSelect11(String CheckPoint, boolean value,
			int v_ChannelIndex) {
		return true;
	}

	public int CTCanNmChannelIdRef(int v_ChannelIndex) {
		return v_ChannelIndex;
	}

	public boolean CanNmPassiveModeEnabled(String position, boolean condition) {
		for (int i = 0; i < nodes.getLength(); i++) {
			NamedNodeMap element = nodes.item(i).getAttributes();
			sizeOfCanNmChannelConfig = element.getLength();
			for (int j = 0; j < element.getLength(); j++) {
				if (element.item(j).getNodeName()
						.matches("CanNmPassiveModeEnabled")) {
					if (element.item(j).getNodeValue()
							.matches(String.valueOf(condition))) {
						// System.out.println("C1:yes");
						return true;
					} else {
						// System.out.println("C1:no...");
						return false;
					}
				}
			}
		}
		return false;
	}

	public boolean CanNmBusLoadReductionEnabled(String string) {
		for (int i = 0; i < nodes.getLength(); i++) {
			NamedNodeMap element = nodes.item(i).getAttributes();
			for (int j = 0; j < element.getLength(); j++) {
				if (element.item(j).getNodeName()
						.matches("CanNmBusLoadReductionEnabled")) {
					if (element.item(j).getNodeValue().matches("true")) {
						// System.out.println("C2:yes");
						return true;
					} else {
						// System.out.println("C2:no...");
						return false;
					}
				}
			}
		}
		return false;

	}

	public boolean PC11(String string) throws XPathExpressionException {
		expr = xpath.compile("//CanNmChannelConfig");
		result = expr.evaluate(doc, XPathConstants.NODESET);
		nodes = (NodeList) result;
		if (nodes.getLength() > 0) {
			// System.out.println("C3:yes");
			return true;
		} else {
			System.out.println("C3:no...");
			return false;
		}
	}

	public boolean Select11(String string, boolean b, int v_ChannelIndex) {
		int v_MaxChannels = sizeOfCanNmChannelConfig;

		for (int p_Channel = v_ChannelIndex; p_Channel < v_MaxChannels; p_Channel++) {
			if (CanNmBusLoadReductionActive(p_Channel)
					&& (CanNmRepeatMessageTime(p_Channel) > 0.0)
					&& (CanNmMsgCycleOffset(p_Channel) != CanNmMsgReducedTime(p_Channel)))
				return true;
			else {
				// log message needed
			}
		}
		return false;
	}

	private double CanNmMsgCycleOffset(int p_Channel) {
		// TODO Auto-generated method stub
		return 0;
	}

	private double CanNmMsgReducedTime(int p_Channel) {
		// TODO Auto-generated method stub
		return 0;
	}

	private double CanNmRepeatMessageTime(int p_Channel) {
		// TODO Auto-generated method stub
		return 0;
	}

	private boolean CanNmBusLoadReductionActive(int p_Channel) {
		// TODO Auto-generated method stub
		return false;
	}

	public int CanNmRxPduId(int v_ChannelIndex) throws XPathExpressionException {
		int rxPduId = 0;
		expr = xpath.compile("//CanNmChannelConfig/CanNmRxPdu");
		result = expr.evaluate(doc, XPathConstants.NODESET);
		nodes = (NodeList) result;
		for (int i = 0; i < nodes.getLength(); i++) {
			NamedNodeMap element = nodes.item(i).getAttributes();
			for (int j = 0; j < element.getLength(); j++) {
				if (element.item(j).getNodeName().matches("CanNmRxPduId")) {
					rxPduId = Integer.valueOf(element.item(j).getNodeValue());
				}
			}
		}
		return rxPduId;
	}

	public boolean config() throws XPathExpressionException {
		// CanNmModuleConfig::CTCanNmCommonPublishedInformation
		expr = xpath
				.compile("//CanNmModuleConfig/CTCanNmCommonPublishedInformation");
		result = expr.evaluate(doc, XPathConstants.NODESET);
		nodes = (NodeList) result;
		for (int i = 0; i < nodes.getLength(); i++) {
			NamedNodeMap element = nodes.item(i).getAttributes();
			for (int j = 0; j < element.getLength(); j++) {
				// System.out.println(element.item(j).getNodeName());
				if (element.item(j).getNodeName().matches("CTCanNmModuleId")) {
					modConf.CTCanNmModuleId = Integer.valueOf(element.item(j)
							.getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CTCanNmSwMajorVersion")) {
					modConf.CTCanNmSwMajorVersion = Integer.valueOf(element
							.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CTCanNmSwMinorVersion")) {
					modConf.CTCanNmSwMinorVersion = Integer.valueOf(element
							.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CTCanNmSwPatchVersion")) {
					modConf.CTCanNmSwPatchVersion = Integer.valueOf(element
							.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CTCanNmVendorId")) {
					modConf.CTCanNmVendorId = Integer.valueOf(element.item(j)
							.getNodeValue());
				}
			}
		}
//		System.out.println(modConf.CTCanNmModuleId + "\t"
//				+ modConf.CTCanNmSwMajorVersion + "\t"
//				+ modConf.CTCanNmSwMinorVersion + "\t"
//				+ modConf.CTCanNmSwPatchVersion + "\t"
//				+ modConf.CTCanNmVendorId);

		// CanNmModuleConfig::CTCanNmDemInformation
		expr = xpath.compile("//CanNmModuleConfig/CTCanNmDemInformation");
		result = expr.evaluate(doc, XPathConstants.NODESET);
		nodes = (NodeList) result;
		for (int i = 0; i < nodes.getLength(); i++) {
			NamedNodeMap element = nodes.item(i).getAttributes();
			for (int j = 0; j < element.getLength(); j++) {
				if (element.item(j).getNodeName()
						.matches("CTCanNmDemEventStatusFailed")) {
					modConf.CTCanNmDemEventStatusFailed = Integer
							.valueOf(element.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CTCanNmECanIfTransmitError")) {
					modConf.CTCanNmECanIfTransmitError = Integer
							.valueOf(element.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CTCanNmEInitFailed")) {
					modConf.CTCanNmEInitFailed = Integer.valueOf(element
							.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CTCanNmENetworkTimeout")) {
					modConf.CTCanNmENetworkTimeout = Integer.valueOf(element
							.item(j).getNodeValue());
				}
			}
		}
//		System.out.println(modConf.CTCanNmDemEventStatusFailed + "\t"
//				+ modConf.CTCanNmECanIfTransmitError + "\t"
//				+ modConf.CTCanNmEInitFailed + "\t"
//				+ modConf.CTCanNmENetworkTimeout);

		// CanNmModuleConfig::CTCanNmGeneralConfig
		expr = xpath.compile("//CanNmModuleConfig/CTCanNmGeneralConfig");
		result = expr.evaluate(doc, XPathConstants.NODESET);
		nodes = (NodeList) result;
		for (int i = 0; i < nodes.getLength(); i++) {
			NamedNodeMap element = nodes.item(i).getAttributes();
			for (int j = 0; j < element.getLength(); j++) {
				if (element.item(j).getNodeName()
						.matches("CTCanNmConfigSetIndex")) {
					modConf.CTCanNmConfigSetIndex = Integer.valueOf(element
							.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CTCanNmRxPduData")) {
					modConf.CTCanNmRxPduData = element.item(j).getNodeValue();
				} else if (element.item(j).getNodeName()
						.matches("CTCanNmTCMaxTimeout")) {
					modConf.CTCanNmTCMaxTimeout = Float.valueOf(element.item(j)
							.getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CTCanNmTxData")) {
					modConf.CTCanNmTxData = element.item(j).getNodeValue();
				}
			}
		}
//		System.out.println(modConf.CTCanNmConfigSetIndex + "\t"
//				+ modConf.CTCanNmRxPduData + "\t" + modConf.CTCanNmTCMaxTimeout
//				+ "\t" + modConf.CTCanNmTxData);

		// CanNmModuleConfig::CanNmGlobalConfig
		expr = xpath.compile("//CanNmModuleConfig/CanNmGlobalConfig");
		result = expr.evaluate(doc, XPathConstants.NODESET);
		nodes = (NodeList) result;
		for (int i = 0; i < nodes.getLength(); i++) {
			NamedNodeMap element = nodes.item(i).getAttributes();
			for (int j = 0; j < element.getLength(); j++) {
				if (element.item(j).getNodeName()
						.matches("CanNmBusLoadReductionEnabled")) {
					modConf.CanNmBusLoadReductionEnabled = Boolean
							.valueOf(element.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmBusSynchronizationEnabled")) {
					modConf.CanNmBusSynchronizationEnabled = Boolean
							.valueOf(element.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmComControlEnabled")) {
					modConf.CanNmComControlEnabled = Boolean.valueOf(element
							.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmImmediateRestartEnabled")) {
					modConf.CanNmImmediateRestartEnabled = Boolean
							.valueOf(element.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmImmediateTxconfEnabled")) {
					modConf.CanNmImmediateTxconfEnabled = Boolean
							.valueOf(element.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmMainFunctionPeriod")) {
					modConf.CanNmMainFunctionPeriod = Float.valueOf(element
							.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmNodeDetectionEnabled")) {
					modConf.CanNmNodeDetectionEnabled = Boolean.valueOf(element
							.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmPassiveModeEnabled")) {
					modConf.CanNmPassiveModeEnabled = Boolean.valueOf(element
							.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmPduRxIndicationEnabled")) {
					modConf.CanNmPduRxIndicationEnabled = Boolean
							.valueOf(element.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmRemoteSleepIndEnabled")) {
					modConf.CanNmRemoteSleepIndEnabled = Boolean
							.valueOf(element.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmRepeatMsgIndEnabled")) {
					modConf.CanNmRepeatMsgIndEnabled = Boolean.valueOf(element
							.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmStateChangeIndEnabled")) {
					modConf.CanNmStateChangeIndEnabled = Boolean
							.valueOf(element.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmUserDataEnabled")) {
					modConf.CanNmUserDataEnabled = Boolean.valueOf(element
							.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmVersionInfoApi")) {
					modConf.CanNmVersionInfoApi = Boolean.valueOf(element.item(
							j).getNodeValue());
				}
			}
		}

		// CanNmModuleConfig::CanNmGlobalConfig::CanNmChannelConfig
		expr = xpath
				.compile("//CanNmModuleConfig/CanNmGlobalConfig/CanNmChannelConfig");
		result = expr.evaluate(doc, XPathConstants.NODESET);
		nodes = (NodeList) result;
		for (int i = 0; i < nodes.getLength(); i++) {
			ChannelConfig cc = new ChannelConfig();
			NamedNodeMap element = nodes.item(i).getAttributes();
			for (int j = 0; j < element.getLength(); j++) {
				if (element.item(j).getNodeName()
						.matches("CanNmBusLoadReductionActive")) {
					cc.CanNmBusLoadReductionActive = Boolean.valueOf(element
							.item(j).getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmMsgCycleOffset")) {
					cc.CanNmMsgCycleOffset = Integer.valueOf(element.item(j)
							.getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmMsgCycleTime")) {
					cc.CanNmMsgCycleTime = Float.valueOf(element.item(j)
							.getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmMsgReducedTime")) {
					cc.CanNmMsgReducedTime = Float.valueOf(element.item(j)
							.getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmMsgTimeoutTime")) {
					cc.CanNmMsgTimeoutTime = Float.valueOf(element.item(j)
							.getNodeValue());
				} else if (element.item(j).getNodeName().matches("CanNmNodeId")) {
					cc.CanNmNodeId = Integer.valueOf(element.item(j)
							.getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmPduCbvPosition")) {
					cc.CanNmPduCbvPosition = element.item(j).getNodeValue();
				} else if (element.item(j).getNodeName()
						.matches("CanNmPduNidPosition")) {
					cc.CanNmPduNidPosition = element.item(j).getNodeValue();
				} else if (element.item(j).getNodeName()
						.matches("CanNmRemoteSleepIndTime")) {
					cc.CanNmRemoteSleepIndTime = Float.valueOf(element.item(j)
							.getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmRepeatMessageTime")) {
					cc.CanNmRepeatMessageTime = Float.valueOf(element.item(j)
							.getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmTimeoutTime")) {
					cc.CanNmTimeoutTime = Float.valueOf(element.item(j)
							.getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmUserDataLength")) {
					cc.CanNmUserDataLength = Integer.valueOf(element.item(j)
							.getNodeValue());
				} else if (element.item(j).getNodeName()
						.matches("CanNmWaitBusSleepTime")) {
					cc.CanNmWaitBusSleepTime = Float.valueOf(element.item(j)
							.getNodeValue()); 
				}
			}
			chConf.add(cc);
//			NodeList subNL = nodes.item(i).getChildNodes();
//			for (int k = 0; k < subNL.getLength(); k++) {
//				NamedNodeMap subElement = subNL.item(k).getAttributes();
//				System.out.println(subElement.getLength());
//				for (int l = 0; l < subElement.getLength(); l++) {
//					System.out.println(subElement.item(l).getNodeName());
//				}
//			}
			
		}
		return false;
	}
}
