package dms.konkuk.autosar.ct.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.PathEditor;

public class AnalysisPref extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	/**
	 * Create the preference page.
	 */
	public AnalysisPref() {
		super(FLAT);
		setMessage("Analysis - Configurations for requirements");
		setTitle("Analysis");
	}

	/**
	 * Create contents of the preference page.
	 */
	@Override
	protected void createFieldEditors() {
		// Create the field editors
		addField(new BooleanFieldEditor("id", "New BooleanFieldEditor", BooleanFieldEditor.DEFAULT, getFieldEditorParent()));
		addField(new BooleanFieldEditor("id", "New BooleanFieldEditor", BooleanFieldEditor.DEFAULT, getFieldEditorParent()));
		addField(new ComboFieldEditor("id", "New ComboFieldEditor", new String[][]{{"name_1", "value_1"}, {"name_2", "value_2"}}, getFieldEditorParent()));
		addField(new PathEditor("id", "New PathEditor", "Select directory:", getFieldEditorParent()));
	}

	/**
	 * Initialize the preference page.
	 */
	public void init(IWorkbench workbench) {
		// Initialize the preference page
	}

}
