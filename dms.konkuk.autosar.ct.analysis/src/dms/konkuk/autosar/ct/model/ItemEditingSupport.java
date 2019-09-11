package dms.konkuk.autosar.ct.model;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
//import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
//import org.eclipse.swt.widgets.Composite;

public class ItemEditingSupport extends EditingSupport {
	private CellEditor editor;
//	private Composite parent;
	private int column;

	String[] categoryList = new String[] { "No Requirement", "Redundant Requirement", "Informal Requirement",
			"Definition of Configuration Parameter", "Implementation of Configuration Parameter",
			"Requirement on Configuration", "Detection of Wrong Configurations", "Development Error",
			"Header Files for Internal Use", "Inside Source Code", "Inside Header File",
			"Header Files for External Use", "Provided Signature", "Required Signature",
			"Requirement on Module Behavior", "Requirement on Re-entrance of Module",
			"Requirement on Execution in Interrupt Context", "Requirement on other Module", "Direct Hardware Access",
			"Non-observable Module Behavior", "Non-testable Requirement", "Vendor-Specific Extensions",
			"Pending on Bug" };
	String[] choice = new String[]{ "yes", "no" };
	String[] testMethod = new String[] { "Configuration Inspection", "Source Code Inspection", "Compile-build process",
			"Dynamic test case" };

	public ItemEditingSupport(ColumnViewer viewer, int column) {
		super(viewer);
//		parent =((TableViewer)viewer).getTable();
		switch (column) {
		case 5:
			editor = new ComboBoxCellEditor(((TableViewer) viewer).getTable(), categoryList);
			break;
		case 6:
			editor = new ComboBoxCellEditor(((TableViewer) viewer).getTable(), choice);
			break;
		case 7:
			editor = new ComboBoxCellEditor(((TableViewer) viewer).getTable(), testMethod);
			break;
		default:
			editor = new TextCellEditor(((TableViewer) viewer).getTable());
		}
		this.column = column;

	}

	@Override
	protected boolean canEdit(Object element) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		// TODO Auto-generated method stub
		return editor;
	}

	@Override
	protected Object getValue(Object element) {
		SwsItem item = (SwsItem) element;
		switch (this.column) {
		case 0:
			return item.getNo();
		case 1:
			return item.getSwsID();
		case 2:
			return item.getReqID();
		case 3:
			return item.getDescription();
		case 4:
			return item.getConf();
		case 5:
			for (int i=0; i < categoryList.length; i++)	{
				if(item.getCategory().equals(categoryList[i]))
					return i;
			}
//			return item.getCategory();
		case 6:
			if (item.getRevelanceToCT().equals("yes"))
				return 0;
//			return item.getRevelanceToCT();
		case 7:
			for (int j=0; j < testMethod.length; j++)
				if (item.getHowToTest().equals(testMethod[j]))
					return j;
//			if (item.getHowToTest().equals("Configuration Inspection"))
//				return 0;
//			else if (item.getHowToTest().equals("Source Code Inspection"))
//				return 1;
//			else if (item.getHowToTest().equals("Compile-build process"))
//				return 2;
//			else if (item.getHowToTest().equals("Dynamic test case"))
//				return 3;
//			return item.getHowToTest();
		case 8:
			return item.getBugzillaID();
		case 9:
			return item.getComment();
		default:
			break;
		}
		return null;
	}

	@Override
	protected void setValue(Object element, Object value) {
		SwsItem modifyItem = (SwsItem) element;
		switch (this.column) {
		case 0:
			break;
		case 1:
			modifyItem.setSwsID(String.valueOf(value));
			break;
		case 2:
			modifyItem.setReqID(String.valueOf(value));
			break;
		case 3:
			modifyItem.setDescription(String.valueOf(value));
			break;
		case 4:
			modifyItem.setConf(String.valueOf(value));
			break;
		case 5:
			modifyItem.setCategory(categoryList[((Integer)value)]);
			break;
		case 6:
			if (((Integer)value)==0)
				modifyItem.setRelevanceToCT("yes");
			else
				modifyItem.setRelevanceToCT("no");	
//			modifyItem.setRelevanceToCT(String.valueOf(value));
			break;
		case 7:
			switch ((Integer)value)	{
			case 0:
				modifyItem.setHowToTest("Configuration Inspection");
				break;
			case 1:
				modifyItem.setHowToTest("Source Code Inspection");
				break;
			case 2:
				modifyItem.setHowToTest("Compile-build process");
				break;
			case 3:
				modifyItem.setHowToTest("Dynamic test case");
				break;
			}
//			modifyItem.setHowToTest(String.valueOf(value));
			break;
		case 8:
			modifyItem.setBugzillaID(String.valueOf(value));
			break;
		case 9:
			modifyItem.setComment(String.valueOf(value));
			break;
		default:
			break;
		}
		getViewer().update(element, null);
	}

}
