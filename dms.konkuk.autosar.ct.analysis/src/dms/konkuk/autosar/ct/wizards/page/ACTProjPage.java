package dms.konkuk.autosar.ct.wizards.page;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class ACTProjPage extends WizardPage {
	private Text projectNameField;
	private String initialProjectFieldValue;
	private int deviceNo;

	/**
	 * Create the wizard.
	 */
	public ACTProjPage() {
		super("wizardPage");
		setTitle("New AUTOSAR Conformance Test Project");
		setDescription("Describe project informations");
	}

	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		{
			Label lblProject = new Label(container, SWT.NONE);
			lblProject.setBounds(20, 13, 101, 15);
			lblProject.setText("Project Name:");
		}
		{
			projectNameField = new Text(container, SWT.BORDER);
			projectNameField.setBounds(127, 10, 425, 21);
		}
		{
			Group grpSelectAutosarBswbasic = new Group(container, SWT.NONE);
			grpSelectAutosarBswbasic.setText("Select AUTOSAR BSW(Basic SoftWare)");
			grpSelectAutosarBswbasic.setBounds(10, 99, 542, 229);
			{
				Button btnEaeepromAbstraction = new Button(grpSelectAutosarBswbasic, SWT.RADIO);
				btnEaeepromAbstraction.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						deviceNo = 0;
					}
				});
				btnEaeepromAbstraction.setBounds(27, 24, 215, 16);
				btnEaeepromAbstraction.setText("EA (EEPROM Abstraction)");
			}
			{
				Button btnCannmcanNetwork = new Button(grpSelectAutosarBswbasic, SWT.RADIO);
				btnCannmcanNetwork.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						deviceNo = 1;
					}
				});
				btnCannmcanNetwork.setBounds(27, 50, 249, 16);
				btnCannmcanNetwork.setText("CanNm (CAN Network Management)");
			}
			{
				Button btnDetdiagnosticEvent = new Button(grpSelectAutosarBswbasic, SWT.RADIO);
				btnDetdiagnosticEvent.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						deviceNo = 2;
					}
				});
				btnDetdiagnosticEvent.setBounds(27, 76, 249, 16);
				btnDetdiagnosticEvent.setText("DEM (Diagnostic Event Manager)");
			}
			{
				Button btnWatchdogDriver = new Button(grpSelectAutosarBswbasic, SWT.RADIO);
				btnWatchdogDriver.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						deviceNo = 3;
					}
				});
				btnWatchdogDriver.setBounds(27, 102, 163, 16);
				btnWatchdogDriver.setText("Watchdog Driver");
			}
			{
				Button btnPduRouter = new Button(grpSelectAutosarBswbasic, SWT.RADIO);
				btnPduRouter.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						deviceNo = 4;
					}
				});
				btnPduRouter.setBounds(27, 128, 249, 16);
				btnPduRouter.setText("PDU Router");
			}
			{
				Button btnFlashEepromEmulation = new Button(grpSelectAutosarBswbasic, SWT.RADIO);
				btnFlashEepromEmulation.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						deviceNo = 5;
					}
				});
				btnFlashEepromEmulation.setBounds(27, 154, 249, 16);
				btnFlashEepromEmulation.setText("Flash EEPROM Emulation");
			}
			{
				Button btnDetdevelopmentError = new Button(grpSelectAutosarBswbasic, SWT.RADIO);
				btnDetdevelopmentError.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						deviceNo = 6;
					}
				});
				btnDetdevelopmentError.setBounds(27, 180, 249, 16);
				btnDetdevelopmentError.setText("DET (Development Error Tracer)");
			}
			{
				Button btnFlexrayStateManager = new Button(grpSelectAutosarBswbasic, SWT.RADIO);
				btnFlexrayStateManager.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						deviceNo = 7;
					}
				});
				btnFlexrayStateManager.setBounds(295, 180, 193, 16);
				btnFlexrayStateManager.setText("FlexRay State Manager");
			}
			{
				Button btnLinStateManager = new Button(grpSelectAutosarBswbasic, SWT.RADIO);
				btnLinStateManager.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						deviceNo = 8;
					}
				});
				btnLinStateManager.setBounds(295, 154, 177, 16);
				btnLinStateManager.setText("LIN State Manager");
			}
			{
				Button btnEthernetDriver = new Button(grpSelectAutosarBswbasic, SWT.RADIO);
				btnEthernetDriver.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						deviceNo = 9;
					}
				});
				btnEthernetDriver.setBounds(295, 128, 168, 16);
				btnEthernetDriver.setText("Ethernet Driver");
			}
			{
				Button btnComManager = new Button(grpSelectAutosarBswbasic, SWT.RADIO);
				btnComManager.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						deviceNo = 10;
					}
				});
				btnComManager.setBounds(295, 102, 163, 16);
				btnComManager.setText("COM Manager");
			}
			{
				Button btnRamTest = new Button(grpSelectAutosarBswbasic, SWT.RADIO);
				btnRamTest.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						deviceNo = 11;
					}
				});
				btnRamTest.setBounds(295, 76, 168, 16);
				btnRamTest.setText("RAM Test");
			}
			{
				Button btnFlashDriver = new Button(grpSelectAutosarBswbasic, SWT.RADIO);
				btnFlashDriver.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						deviceNo = 12;
					}
				});
				btnFlashDriver.setBounds(295, 50, 177, 16);
				btnFlashDriver.setText("Flash Driver");
			}
			{
				Button btnNvramManager = new Button(grpSelectAutosarBswbasic, SWT.RADIO);
				btnNvramManager.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent e) {
						deviceNo = 13;
					}
				});
				btnNvramManager.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
					}
				});
				btnNvramManager.setBounds(295, 24, 193, 16);
				btnNvramManager.setText("NVRAM Manager");
			}
		}
	}

	public IProject getProjectHandle() {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
	}

	public String getProjectName() {
		if (projectNameField == null) {
			return initialProjectFieldValue;
		}

		return getProjectNameFieldValue();
	}

	private String getProjectNameFieldValue() {
		if (projectNameField == null) {
			return ""; //$NON-NLS-1$
		}

		return projectNameField.getText().trim();
	}

	public IPath getLocationPath() {
		return null;
	}

	public int GetDeviceNum() {
		return deviceNo;
	}

}
