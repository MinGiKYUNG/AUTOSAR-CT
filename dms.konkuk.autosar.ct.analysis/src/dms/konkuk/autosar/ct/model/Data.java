package dms.konkuk.autosar.ct.model;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.actions.ActionDelegate;

public class Data extends ActionDelegate implements IViewActionDelegate {

	private static IStructuredSelection selections;
	private static IViewPart view;

	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub
		this.view = view;
	}

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub

	}

	public static void req_open(IEditorInput input) {
		IWorkbenchPage page = view.getSite().getPage();
		try {
			page.openEditor(input, "dms.konkuk.autosar.ct.editor.SWSEditor");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		try{
		 this.selections = (IStructuredSelection)selection;
		 setInfo(selections);
		 explorerRefresh();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void setData() {
		selections = (IStructuredSelection) view.getSite()
				.getSelectionProvider().getSelection();
		setInfo(selections);
	}

	// 선택한 프로젝트의 절대 경로
	public static void setInfo(IStructuredSelection selections) {
		IResource res = null;
		Object element = selections.getFirstElement();
		if (element instanceof IAdaptable) {
			res = (IResource) ((IAdaptable) element)
					.getAdapter(IResource.class);
		}
		DataRepository data_repos = DataRepository.GetInstance();
		data_repos.setSelectName(res.getName());
		data_repos.setWorkspacePath(res.getWorkspace().getRoot().getLocation()
				.toOSString());
		data_repos.setFullPath(res.getLocation().toOSString());
		data_repos.setProjectName(res.getProject().getName());
		data_repos.setProjectPath(res.getProject().getLocation().toOSString());
		data_repos.setSelection(selections);
	}
	
	public static void explorerRefresh()
	{
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject projects[] = root.getProjects();
		for(int i = 0; i < projects.length; i++){
			try {
				projects[i].refreshLocal(IResource.DEPTH_INFINITE, null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
