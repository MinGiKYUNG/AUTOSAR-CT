package dms.konkuk.autosar.ct.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.jface.preference.BooleanFieldEditor;

public class AutosarCTPref extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	/**
	 * Create the preference page.
	 */
	public AutosarCTPref() {
		super(FLAT);
		setMessage("Messages");
		setTitle("AUTOSAR CT");
	}

	/**
	 * Create contents of the preference page.
	 */
	@Override
	protected void createFieldEditors() {
		// Create the field editors
		addField(new BooleanFieldEditor("id", "New BooleanFieldEditor", BooleanFieldEditor.DEFAULT, getFieldEditorParent()));
		addField(new BooleanFieldEditor("id", "New BooleanFieldEditor", BooleanFieldEditor.DEFAULT, getFieldEditorParent()));
		addField(new BooleanFieldEditor("id", "New BooleanFieldEditor", BooleanFieldEditor.DEFAULT, getFieldEditorParent()));
		addField(new BooleanFieldEditor("id", "New BooleanFieldEditor", BooleanFieldEditor.DEFAULT, getFieldEditorParent()));
		addField(new BooleanFieldEditor("id", "New BooleanFieldEditor", BooleanFieldEditor.DEFAULT, getFieldEditorParent()));
	}

	/**
	 * Initialize the preference page.
	 */
	public void init(IWorkbench workbench) {
		// Initialize the preference page
	}

}
