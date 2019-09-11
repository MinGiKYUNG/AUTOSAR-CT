package dms.konkuk.autosar.ct.wizards;

//import java.io.File;
import java.io.File;
import java.io.FileOutputStream;
//import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import dms.konkuk.autosar.ct.wizards.page.RequiredSWS;
import dms.konkuk.autosar.ct.analysis.CommonWorks;

public class NewSWSDoc extends Wizard implements INewWizard {

	private RequiredSWS req;
	private File actReq;
//	private File swsFile;

	public NewSWSDoc() {
		setWindowTitle("New SWS Document Wizard");
	}

	@Override
	public void addPages() {
		req = new RequiredSWS();
		addPage(req);
	}

	@Override
	public boolean performFinish() {
		try {
			String swsDocName = req.getSwsDocName();
			String projName = req.getProjName();
			
			CommonWorks cw = new CommonWorks();
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IProject projects[] = root.getProjects();
			for(int i = 0; i < projects.length; i++)	{
				if (projName.equals(projects[i].getName())){
					createRequirement(projects[i].getLocation().toString() + "\\Requirements\\" + swsDocName + ".sws");
					actReq = new File(projects[i].getLocation().toString() + "\\Requirements\\" + swsDocName + ".sws");
				}
			}
			
			IPath location = Path.fromOSString(actReq.getAbsolutePath()); 
			IFile proj = root.getFileForLocation(location); 
			IEditorInput editorInput = new FileEditorInput(proj);
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(actReq.getName());
			page.openEditor(editorInput, desc.getId());
			
			cw.explorerRefresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

	public void createRequirement(String swsFile) {
		try {
			FileOutputStream out = new FileOutputStream(swsFile);
			String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<ACT_SWS>\n" + "</ACT_SWS>";
			out.write(str.getBytes());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
