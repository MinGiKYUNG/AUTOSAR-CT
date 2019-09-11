package dms.konkuk.autosar.ct.wizards.page;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Combo;

public class RequiredSWS extends WizardPage {
	private Text text;
	private List swsList;
	private Label lblSelectedProject;
	private Combo combo;

	private String[] commStack = { "CAN Driver", "CAN Interface", "CAN Network Management", "CAN State Manager",
			"CAN Transceiver Driver", "CAN Transport Layer", "COM", "COM Manager", "CommunicationStackTypes",
			"Ethernet Driver", "Ethernet Interface", "Ethernet State Manager", "Ethernet Transceiver Driver",
			"FlexRay Driver", "FlexRay Interface", "FlexRay Network Management", "FlexRay State Manager",
			"FlexRay Transceiver Driver", "FlexRay Transport Layer", "IPDUM Multiplexer", "LIN Driver",
			"LIN Interface", "LIN Network Management", "LIN State Manager", "LIN Transceiver Driver",
			"Network Management Interface", "PDU Router", "SAE1939 Transport Layer", "Socket Adapter", "TTCAN Driver",
			"TTCAN Interface", "UDP Network Management", "XCP" };
	private String[] SysServ = { "BFX Library", "BSW Mode Manager", "CRC Library", "Crypto Abstraction Library",
			"Crypto Service Manager", "Debugging", "Disgnostic Log and Trace", "E2E Library", "ECU State Manager",
			"ECU State Manager Fixed", "EFX Library", "IFL Library", "IFX Library", "MFL Library", "MFX Library",
			"MultiCore OS", "OS", "Synchronized Time Base Manager", "Watchdog Manager" };
	private String[] DiagServ = { "Development Error Tracer", "Diagnostic Communication Manager",
			"Diagnostic Event Manager", "Function Inhibition Manager" };
	private String[] MemStack = { "EEPROM Abstraction", "EEPROM Driver", "Flash Driver", "Flash EEPROM Emulation",
			"Memory Abstraction Interface", "NVRAM Manager", "RAM Test" };
	private String[] Peripherals = { "ADC Driver", "Core Test", "DIO Driver", "Flash Test", "GPT Driver", "ICU Driver",
			"MCU Driver", "Port Driver", "PWM Driver", "SPI Handler Driver", "Watchdog Driver", "Watchdog Interface" };

	public RequiredSWS() {
		super("wizardPage");
		setMessage("Create SWS document for AUTOSAR Conformance Test");

		setTitle("New SWS document");
		setDescription("Wizard Page description");
		
	
	}

	public String getSwsDocName() {
		return text.getText();
	}
	
	public String getProjName()	{
		return combo.getText();
	}

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		{
			Label lblNewSwsDocument = new Label(container, SWT.NONE);
			lblNewSwsDocument.setBounds(10, 13, 131, 15);
			lblNewSwsDocument.setText("New SWS Document:");
		}
		{
			text = new Text(container, SWT.BORDER);
			text.setBounds(145, 10, 419, 21);
		}
		{
			Group grpSelectBaseline = new Group(container, SWT.NONE);
			grpSelectBaseline.setText("Select Baseline SWS");
			grpSelectBaseline.setBounds(10, 75, 554, 231);
			{
				swsList = new List(grpSelectBaseline, SWT.BORDER | SWT.V_SCROLL);
				swsList.setBounds(317, 27, 227, 194);
			}
			{
				Button btnCommunicationStack = new Button(grpSelectBaseline, SWT.RADIO);
				btnCommunicationStack.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						swsList.removeAll();
						swsList.setItems(commStack);
					}
				});
				btnCommunicationStack.setBounds(10, 27, 181, 16);
				btnCommunicationStack.setText("Communication Stack");
			}
			{
				Button btnSystemServices = new Button(grpSelectBaseline, SWT.RADIO);
				btnSystemServices.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						swsList.removeAll();
						swsList.setItems(SysServ);
					}
				});
				btnSystemServices.setBounds(10, 49, 181, 16);
				btnSystemServices.setText("System Services");
			}
			{
				Button btnDisgnosticServices = new Button(grpSelectBaseline, SWT.RADIO);
				btnDisgnosticServices.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						swsList.removeAll();
						swsList.setItems(DiagServ);
					}
				});
				btnDisgnosticServices.setBounds(10, 71, 181, 16);
				btnDisgnosticServices.setText("Diagnostic Services");
			}
			{
				Button btnMemoryStack = new Button(grpSelectBaseline, SWT.RADIO);
				btnMemoryStack.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						swsList.removeAll();
						swsList.setItems(MemStack);
					}
				});
				btnMemoryStack.setBounds(10, 93, 181, 16);
				btnMemoryStack.setText("Memory Stack");
			}
			{
				Button btnPeripherals = new Button(grpSelectBaseline, SWT.RADIO);
				btnPeripherals.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						swsList.removeAll();
						swsList.setItems(Peripherals);
					}
				});
				btnPeripherals.setBounds(10, 115, 181, 16);
				btnPeripherals.setText("Peripherals");
			}
			{
				Button btnImplementationIntegration = new Button(grpSelectBaseline, SWT.RADIO);
				btnImplementationIntegration.setEnabled(false);
				btnImplementationIntegration.setBounds(10, 137, 181, 16);
				btnImplementationIntegration.setText("Implementation Integration");
			}
			{
				Button btnRteruntimeEnvironment = new Button(grpSelectBaseline, SWT.RADIO);
				btnRteruntimeEnvironment.setEnabled(false);
				btnRteruntimeEnvironment.setBounds(10, 159, 181, 16);
				btnRteruntimeEnvironment.setText("RTE (RunTime Environment)");
			}
		}
		{
			lblSelectedProject = new Label(container, SWT.NONE);
			lblSelectedProject.setBounds(10, 37, 131, 15);
			lblSelectedProject.setText("Selected Project:");
		}
		{
			combo = new Combo(container, SWT.NONE);
			combo.setBounds(145, 34, 419, 23);
			// Get location of projects
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IProject projects[] = root.getProjects();
			for(int i = 0; i < projects.length; i++){
				combo.add(projects[i].getName());
			}
		}
	}
}
