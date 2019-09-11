package dms.konkuk.autosar.ct.analysis;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.PlatformUI;

import dms.konkuk.autosar.ct.dialog.InitDialog;

public class AnalysisPerspective implements IPerspectiveFactory {

	/**
	 * Creates the initial layout for a page.
	 */
	public void createInitialLayout(IPageLayout layout) {
//		String editorArea = layout.getEditorArea();
		addFastViews(layout);
		addViewShortcuts(layout);
		addPerspectiveShortcuts(layout);
		layout.addView("org.eclipse.ui.navigator.ProjectExplorer", IPageLayout.LEFT, 0.25f, IPageLayout.ID_EDITOR_AREA);
		{
			IFolderLayout folderLayout = layout.createFolder("folder", IPageLayout.BOTTOM, 0.83f, IPageLayout.ID_EDITOR_AREA);
			folderLayout.addView("org.eclipse.pde.runtime.LogView");
			folderLayout.addView("org.eclipse.ui.views.TaskList");
			folderLayout.addView("org.eclipse.ui.views.ProblemView");
			folderLayout.addView("org.eclipse.ui.views.PropertySheet");
		}
		
		InitDialog initDialog = new InitDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		initDialog.open();
	}

	/**
	 * Add fast views to the perspective.
	 */
	private void addFastViews(IPageLayout layout) {
	}

	/**
	 * Add view shortcuts to the perspective.
	 */
	private void addViewShortcuts(IPageLayout layout) {
	}

	/**
	 * Add perspective shortcuts to the perspective.
	 */
	private void addPerspectiveShortcuts(IPageLayout layout) {
	}

}
