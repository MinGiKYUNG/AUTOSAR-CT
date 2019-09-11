package dms.konkuk.autosar.ct.model;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class SwsItemLabelProvider extends LabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		SwsItem item = (SwsItem) element;
		switch (columnIndex) {
		case 0:
			return item.getNo().toString();
		case 1:
			return item.getSwsID();
		case 2:
			return item.getReqID();
		case 3:
			return item.getDescription();
		case 4:
			return item.getConf();
		case 5:
			return item.getCategory();
		case 6:
			return item.getRevelanceToCT();
		case 7:
			return item.getHowToTest();
		case 8:
			return item.getBugzillaID();
		case 9:
			return item.getComment();
		default:
			throw new RuntimeException("Model error");
		}
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

}
