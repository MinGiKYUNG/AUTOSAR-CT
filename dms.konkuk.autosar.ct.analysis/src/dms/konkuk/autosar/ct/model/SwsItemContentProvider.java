package dms.konkuk.autosar.ct.model;

import org.eclipse.jface.viewers.IStructuredContentProvider;
//import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import java.util.List;

public class SwsItemContentProvider implements IStructuredContentProvider {

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getElements(Object inputElement) {
		List<SwsItem> items = (List<SwsItem>) inputElement;
		return items.toArray();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		//this.viewer = (StructuredViewer)viewer;
//		if(oldInput !=newInput)	{
//			if(oldInput != null)	{
//				((SwsItem)oldInput).removePropertyChangeListener(this);
//			}
//		}
		
	}

}
