package dms.konkuk.autosar.ct.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import dms.konkuk.autosar.ct.model.SwsItem; //import dms.konkuk.autosar.ct.model.SwsItemProvider;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class AddSwsItemDialog extends TitleAreaDialog {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Combo combo_1;
	private Combo combo_2;
	private Combo combo_3;
	private int no = 0;
	private SwsItem item;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public AddSwsItemDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		setTitle("Add a new SWS Item");
		// setMessage("Please enter the data of the new SoftWare Specification",
		// IMessageProvider.INFORMATION);
		return contents;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public SwsItem getSwsItem() {
		return item;
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Please enter the data of the new SoftWare Specification");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		{
			Label lblNo = new Label(container, SWT.NONE);
			lblNo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblNo.setText("No");
		}
		{
			Label label = new Label(container, SWT.NONE);
			label.setText(String.valueOf(no));
		}
		{
			Label lblSwsId = new Label(container, SWT.NONE);
			lblSwsId.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblSwsId.setText("SWS ID");
		}
		{
			text = new Text(container, SWT.BORDER);
			text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		}
		{
			Label lblRefinedSwsId = new Label(container, SWT.NONE);
			lblRefinedSwsId.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblRefinedSwsId.setText("Refined SWS ID");
		}

		text_1 = new Text(container, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		{
			Label lblDescription = new Label(container, SWT.NONE);
			lblDescription.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblDescription.setText("Description");
		}

		text_2 = new Text(container, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		{
			Label lblConfiguration = new Label(container, SWT.NONE);
			lblConfiguration.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblConfiguration.setText("Configuration");
		}

		text_3 = new Text(container, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		{
			Label lblCategory = new Label(container, SWT.NONE);
			lblCategory.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblCategory.setText("Category");
		}

		combo_1 = new Combo(container, SWT.NONE);
		combo_1.setItems(new String[] { "No Requirement", "Redundant Requirement", "Informal Requirement",
				"Definition of Configuration Parameter", "Implementation of Configuration Parameter",
				"Requirement on Configuration", "Detection of Wrong Configurations", "Development Error",
				"Header Files for Internal Use", "Inside Source Code", "Inside Header File",
				"Header Files for External Use", "Provided Signature", "Required Signature",
				"Requirement on Module Behavior", "Requirement on Re-entrance of Module",
				"Requirement on Execution in Interrupt Context", "Requirement on other Module",
				"Direct Hardware Access", "Non-observable Module Behavior", "Non-testable Requirement",
				"Vendor-Specific Extensions", "Pending on Bug" });
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		{
			Label lblRelevanceToCt = new Label(container, SWT.NONE);
			lblRelevanceToCt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblRelevanceToCt.setText("Relevance to CT");
		}

		combo_2 = new Combo(container, SWT.NONE);
		combo_2.setItems(new String[] { "yes", "no" });
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblHowToTest = new Label(container, SWT.NONE);
		lblHowToTest.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblHowToTest.setText("How to Test");

		combo_3 = new Combo(container, SWT.NONE);
		combo_3.setItems(new String[] { "Configuration Inspection", "Source Code Inspection", "Compile-build process",
				"Dynamic test case" });
		combo_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblBugzillaReference = new Label(container, SWT.NONE);
		lblBugzillaReference.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBugzillaReference.setText("Bugzilla Reference");

		text_4 = new Text(container, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblComment = new Label(container, SWT.NONE);
		lblComment.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblComment.setText("Comment");

		text_5 = new Text(container, SWT.BORDER);
		{
			GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
			gridData.heightHint = 100;
			text_5.setLayoutData(gridData);
		}
		return parent;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if (checkInput() == true) {
					item = new SwsItem(no, text.getText(), text_1.getText(), text_2.getText(), text_3.getText(),
							combo_1.getText(), combo_2.getText(), combo_3.getText(), text_4.getText(), text_5.getText());
					// System.out.println("hhhh");
				}
					
			}
		});

		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				close();
			}
		});
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 500);
	}

	private boolean checkInput() {

		int[] len = new int[9];
		len[0] = text_1.getText().length();
		len[1] = text_2.getText().length();
		len[2] = text_3.getText().length();
		len[3] = text_4.getText().length();
		len[4] = text_5.getText().length();
		len[5] = combo_1.getText().length();
		len[6] = combo_2.getText().length();
		len[7] = combo_3.getText().length();
		len[8] = text.getText().length();

		for (int j : len) {
			if (0 == j) {
				return false;
			}
			if (combo_2.getText().equals("none")) {
				return false;
			}
		}
		return true;

	}

}
