package dms.konkuk.autosar.ct.analysis;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

public class CommonWorks {

	public void explorerRefresh() {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject projects[] = root.getProjects();
		for (int i = 0; i < projects.length; i++) {
			try {
				projects[i].refreshLocal(IResource.DEPTH_INFINITE, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}
}
