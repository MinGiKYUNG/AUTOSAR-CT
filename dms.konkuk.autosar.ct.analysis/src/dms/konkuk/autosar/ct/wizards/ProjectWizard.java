package dms.konkuk.autosar.ct.wizards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
//import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.part.FileEditorInput;

import dms.konkuk.autosar.ct.wizards.page.ACTProjPage;
import dms.konkuk.autosar.ct.analysis.Activator;

public class ProjectWizard extends Wizard implements INewWizard{
	private ACTProjPage mainPage;
	private IProject newProject;
	private boolean traceEnabled = false;
	private File actProj;
	
	public ProjectWizard() {
		IDialogSettings autosar_setting =Activator.getDefault().getDialogSettings();
		IDialogSettings wizardSettings =
			autosar_setting.getSection("Autosar CT Project");
		if (wizardSettings == null)
			wizardSettings = autosar_setting.addNewSection("Autosar CT Project");
		setDialogSettings(autosar_setting);
		setWindowTitle("New AUTOSAR Conformance Test");
	}
	
	public void CreateProjectFolder() throws CoreException, IOException
	{
		File file;
		String project_path =  newProject.getLocation().toOSString();
		String[] contentsName = {"Architecture", "TestCase", "Configuration", "Executable", "Report", "Log", "Requirements"}; 

		// make directory
		for (String tempContent: contentsName)	{
			file = new File (project_path + "\\"+tempContent);
			file.mkdir();
		}
		
		// make act project configuration file 
		actProj = new File (project_path + "\\" +"setting.actProj");
		try {
			FileOutputStream out = new FileOutputStream(actProj);
			String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
			out.write(str.getBytes());
			out.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}	
		
		// refresh
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject projects[] = root.getProjects();
		for(int i = 0; i < projects.length; i++){
			try {
				projects[i].refreshLocal(IResource.DEPTH_INFINITE, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		// open setting file
		IPath location = Path.fromOSString(actProj.getAbsolutePath()); 
		IFile proj = root.getFileForLocation(location); 
		IEditorInput editorInput = new FileEditorInput(proj);
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(actProj.getName());
		page.openEditor(editorInput, desc.getId());
		}

	@Override
	public void addPages() {
		mainPage = new ACTProjPage();
		addPage(mainPage);
	}

	@Override
	public boolean performFinish() {
		if(mainPage.GetDeviceNum() > -1 && mainPage.GetDeviceNum() < 13)
		{
			try {
				createNewProject();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		
	}
	public IProject createNewProject() throws CoreException, IOException {
		if (newProject != null)
			return newProject;
		// get a project handle
		final IProject newProjectHandle = mainPage.getProjectHandle();
		// get a project description
		IPath defaultPath = Platform.getLocation();
		IPath newPath = mainPage.getLocationPath();
		if (defaultPath.equals(newPath))
			newPath = null;
		IWorkspace workspace = getWorkspace();
		final IProjectDescription description = workspace.newProjectDescription(newProjectHandle.getName());
		description.setLocation(newPath);
		// create the new project operation
		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
			protected void execute(IProgressMonitor monitor)
			throws CoreException {
				createProject(description, newProjectHandle, monitor);
				//addCustomNature(newProjectHandle);
			}
		};
		// run the new project creation operation
		try {
			getContainer().run(false, true, op);
		} catch (InterruptedException e) {
			return null;
		} catch (InvocationTargetException e) {
			// ie.- one of the steps resulted in a core exception
			resultError("Create Project with CustomNature Request", "Project creation failed");
			e.printStackTrace();
			return null;
		}
		newProject = newProjectHandle;
		CreateProjectFolder();
		return newProject;
	}

	public void createProject(IProjectDescription description, IProject projectHandle, IProgressMonitor monitor)
	throws CoreException, OperationCanceledException {
		try {
			monitor.beginTask("", 2000);
			projectHandle.create(description, new SubProgressMonitor(monitor, 1000));
			if (monitor.isCanceled())
				throw new OperationCanceledException();
			projectHandle.open(new SubProgressMonitor(monitor, 1000));
		} finally {
			monitor.done();
		}
	}

	protected void resultError(String title, String msg) {
		// Indicate Error
		if (traceEnabled)
			// Trace only to console
			System.out.println(title + msg);
		else
			// User interaction response
			MessageDialog.openError(getShell(), title, msg);
	}

	public static IWorkspace getWorkspace() {
		return org.eclipse.core.resources.ResourcesPlugin.getWorkspace();
	}

	public IProject getNewProject() {
		return newProject;
	}
}
