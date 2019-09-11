package dms.konkuk.autosar.ct.editor;

import java.io.*;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import dms.konkuk.autosar.ct.dialog.AddSwsItemDialog;
import dms.konkuk.autosar.ct.model.SwsItemContentProvider;
import dms.konkuk.autosar.ct.model.SwsItemLabelProvider;
import dms.konkuk.autosar.ct.model.SwsItemProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import dms.konkuk.autosar.ct.model.ItemEditingSupport;
import dms.konkuk.autosar.ct.model.SwsItem;

public class SwsEditor extends EditorPart {

	public static final String ID = "dms.konkuk.autosar.ct.editor.SWSEditor"; //$NON-NLS-1$
	private Table table;
	private TableViewer tableViewer_1;
	private File file;
	DocumentBuilderFactory dbf;
	DocumentBuilder db;
	Document document;
	NodeList nodeItem;
	private String swsFilePath;

	public SwsEditor() {
		SwsItemProvider.getInstance().clear();
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setVisible(true);
		container.setLayout(new GridLayout(1, false));
		{
			ToolBar toolBar = new ToolBar(container, SWT.FLAT | SWT.RIGHT);
			{
				ToolItem toolItem = new ToolItem(toolBar, SWT.NONE);
				toolItem.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						AddSwsItemDialog asiDialog = new AddSwsItemDialog(PlatformUI.getWorkbench()
								.getActiveWorkbenchWindow().getShell());
						SwsItemProvider sip = SwsItemProvider.getInstance();
						asiDialog.setNo(SwsItemProvider.getInstance().getBiggestNo() + 1);
						asiDialog.open();
						if (asiDialog.getSwsItem() != null) {
							System.out.println(asiDialog.getSwsItem().toString());
							sip.getSwsItems().add(asiDialog.getSwsItem());
							getViewer().refresh();
						} else
							System.out.println("help!!!");
					}
				});
				toolItem.setText("New");
			}
			{
				ToolItem tltmDeleteItem = new ToolItem(toolBar, SWT.NONE);
				tltmDeleteItem.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						TableItem[] ti = table.getSelection();
						System.out.println("length:" + ti.length);
						for (int dCount = 0; dCount < ti.length; dCount++) {
							System.out
									.println("delItem:\t" + ti[dCount].getText(0) + "," + ti[dCount].getText(1) + ","
											+ ti[dCount].getText(2) + "," + ti[dCount].getText(3) + ","
											+ ti[dCount].getText(4) + "," + ti[dCount].getText(5) + ","
											+ ti[dCount].getText(6) + "," + ti[dCount].getText(7) + ","
											+ ti[dCount].getText(8) + "," + ti[dCount].getText(9));
							SwsItemProvider.getInstance().deleteSwsItem(Integer.parseInt(ti[dCount].getText(0)));
						}
						getViewer().refresh();
					}
				});
				tltmDeleteItem.setText("Delete");
			}
			{
				ToolItem tltmSave = new ToolItem(toolBar, SWT.NONE);
				tltmSave.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						doSaveAs();
					}
				});
				tltmSave.setText("Save");
			}
		}
		{
			tableViewer_1 = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
			table = tableViewer_1.getTable();
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			{
				TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer_1, SWT.CENTER);
				tableViewerColumn.setEditingSupport(new ItemEditingSupport(tableViewer_1, 0));
				TableColumn tableColumn = tableViewerColumn.getColumn();
				tableColumn.setAlignment(SWT.RIGHT);
				tableColumn.setWidth(50);
				tableColumn.setText("No");
			}
			{
				TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer_1, SWT.NONE);
				tableViewerColumn.setEditingSupport(new ItemEditingSupport(tableViewer_1, 1));
				TableColumn tableColumn = tableViewerColumn.getColumn();
				tableColumn.setWidth(100);
				tableColumn.setText("ID");
			}
			{
				TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer_1, SWT.NONE);
				tableViewerColumn.setEditingSupport(new ItemEditingSupport(tableViewer_1, 2));
				TableColumn tableColumn = tableViewerColumn.getColumn();
				tableColumn.setWidth(100);
				tableColumn.setText("Refined ID");
			}
			{
				TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer_1, SWT.NONE);
				tableViewerColumn.setEditingSupport(new ItemEditingSupport(tableViewer_1, 3));
				TableColumn tableColumn = tableViewerColumn.getColumn();
				tableColumn.setWidth(200);
				tableColumn.setText("Description");
			}
			{
				TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer_1, SWT.NONE);
				tableViewerColumn.setEditingSupport(new ItemEditingSupport(tableViewer_1, 4));
				TableColumn tableColumn = tableViewerColumn.getColumn();
				tableColumn.setWidth(150);
				tableColumn.setText("Configuration");
			}
			{
				TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer_1, SWT.NONE);
				tableViewerColumn.setEditingSupport(new ItemEditingSupport(tableViewer_1, 5));
				TableColumn tableColumn = tableViewerColumn.getColumn();
				tableColumn.setWidth(150);
				tableColumn.setText("Category");
			}
			{
				TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer_1, SWT.NONE);
				tableViewerColumn.setEditingSupport(new ItemEditingSupport(tableViewer_1, 6));
				TableColumn tableColumn = tableViewerColumn.getColumn();
				tableColumn.setWidth(100);
				tableColumn.setText("Relevance to CT");
			}
			{
				TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer_1, SWT.NONE);
				tableViewerColumn.setEditingSupport(new ItemEditingSupport(tableViewer_1, 7));
				TableColumn tableColumn = tableViewerColumn.getColumn();
				tableColumn.setWidth(150);
				tableColumn.setText("How to Test");
			}
			{
				TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer_1, SWT.NONE);
				tableViewerColumn.setEditingSupport(new ItemEditingSupport(tableViewer_1, 8));
				TableColumn tableColumn = tableViewerColumn.getColumn();
				tableColumn.setWidth(150);
				tableColumn.setText("Bugzilla Reference");
			}
			{
				TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer_1, SWT.NONE);
				tableViewerColumn.setEditingSupport(new ItemEditingSupport(tableViewer_1, 9));
				TableColumn tableColumn = tableViewerColumn.getColumn();
				tableColumn.setWidth(200);
				tableColumn.setText("Comment");
			}
			tableViewer_1.setContentProvider(new SwsItemContentProvider());
			tableViewer_1.setLabelProvider(new SwsItemLabelProvider());
			tableViewer_1.setInput(SwsItemProvider.getInstance().getSwsItems());
		}
	}

	@Override
	public void setFocus() {
		// Set the focus
		table.setFocus();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation

	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
		// read element of SWS file
		// read header part of SWS file
		try {
			// dbf = DocumentBuilderFactory.newInstance();
			// db = dbf.newDocumentBuilder();
			// document = db.parse(file);
			// document.getDocumentElement().normalize();
//			nodeItem = document.getElementsByTagName("ACT_SWS");
//
//			// remove previous Sws Item
//			if (nodeItem.getLength() != 0) {
////				for (int count = 0; count < nodeItem.getLength(); count++) {
////					System.out.println(nodeItem.item(0).getFirstChild().getFirstChild().getNodeName());
////					Node removeNode = nodeItem.item(0).getFirstChild().getFirstChild();
////					document.removeChild(removeNode);
////				}
//			} else {
//				// add current Sws Item
//				// Node node = document.getElementsByTagName("ACT_SWS");
				nodeItem = document.getElementsByTagName("ACT_SWS");
				document.removeChild(nodeItem.item(0));
				Node newnode = document.createElement("ACT_SWS");
				document.appendChild(newnode);
				nodeItem = document.getElementsByTagName("ACT_SWS");
				
				List<SwsItem> saveItemList = SwsItemProvider.getInstance().getSwsItems();
				for (int count2 = 0; count2 < saveItemList.size(); count2++) {
					Element saveItem = document.createElement("SWS_ITEM");
					saveItem.setAttribute("NO", saveItemList.get(count2).getNo().toString());
					saveItem.setAttribute("ID", saveItemList.get(count2).getSwsID());
					saveItem.setAttribute("REF_ID", saveItemList.get(count2).getReqID());
					saveItem.setAttribute("CONF", saveItemList.get(count2).getConf());
					saveItem.setAttribute("CATEGORY", saveItemList.get(count2).getCategory());
					saveItem.setAttribute("RELEVANCE_TO_CT", saveItemList.get(count2).getRevelanceToCT());
					saveItem.setAttribute("HOWTOTEST", saveItemList.get(count2).getHowToTest());
					saveItem.setAttribute("BUGZILLA_ID", saveItemList.get(count2).getBugzillaID());
					saveItem.setAttribute("DESCRIPTION", saveItemList.get(count2).getDescription());
					saveItem.setAttribute("COMMENT", saveItemList.get(count2).getComment());
//					document.appendChild(saveItem);
					System.out.println(count2 + " ::" + saveItemList.get(count2).getSwsID());
					nodeItem.item(0).appendChild(saveItem);
				}
				writeXmlFile(document, swsFilePath);
			//}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	// This method writes a DOM document to a file
	public static void writeXmlFile(Document doc, String filename) {
	    try {
	        // Prepare the DOM document for writing
	        Source source = new DOMSource(doc);

	        // Prepare the output file
	        File file = new File(filename);
	        
	        if (file.delete()==true)	{
	        	try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        
	        Result result = new StreamResult(file);

	        // Write the DOM document to the file
	        Transformer xformer = TransformerFactory.newInstance().newTransformer();
	        xformer.transform(source, result);
	    } catch (TransformerConfigurationException e) {
	    } catch (TransformerException e) {
	    }
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		try {
			setSite(site);
			setInput(input);
			setPartName(input.getName());

		} catch (Exception e) {
			throw new PartInitException(e.getMessage());
		}
		readSwsFile(input);

	}

	private void readSwsFile(IEditorInput input) {
		// read element of SWS file
		IFile jfile = ((IFileEditorInput) input).getFile();
		swsFilePath = jfile.getLocation().toOSString();
		file = new File(swsFilePath);

		// read header part of SWS file
		try {
			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
			document = db.parse(file);
			document.getDocumentElement().normalize();
			// System.out.println("Root element "+document.getDocumentElement().getNodeName());
			nodeItem = document.getElementsByTagName("SWS_ITEM");
			for (int i = 0; i < nodeItem.getLength(); i++) {
				SwsItem swsItem = new SwsItem();
				Node tempNode = nodeItem.item(i);
				NamedNodeMap ndmap = tempNode.getAttributes();
				for (int j = 0; j < ndmap.getLength(); j++) {
					System.out.println(ndmap.item(j).getNodeValue());
					if (ndmap.item(j).getNodeName() == "NO")
						swsItem.setNo(Integer.valueOf(ndmap.item(j).getNodeValue()));
					else if (ndmap.item(j).getNodeName() == "ID")
						swsItem.setSwsID(ndmap.item(j).getNodeValue());
					else if (ndmap.item(j).getNodeName() == "REF_ID")
						swsItem.setReqID(ndmap.item(j).getNodeValue());
					else if (ndmap.item(j).getNodeName() == "CATEGORY") {
						swsItem.setCategory(ndmap.item(j).getNodeValue());
						// System.out.println("Category:\t"+j+"\t"+ndmap.item(j).getNodeValue());
					} else if (ndmap.item(j).getNodeName() == "RELEVANCE_TO_CT")
						swsItem.setRelevanceToCT(ndmap.item(j).getNodeValue());
					else if (ndmap.item(j).getNodeName() == "HOWTOTEST")
						swsItem.setHowToTest(ndmap.item(j).getNodeValue());
					else if (ndmap.item(j).getNodeName() == "BUGZILLA_ID")
						swsItem.setBugzillaID(ndmap.item(j).getNodeValue());
					else if (ndmap.item(j).getNodeName() == "DESCRIPTION")
						swsItem.setDescription(ndmap.item(j).getNodeValue());
					else if (ndmap.item(j).getNodeName() == "CONF")
						swsItem.setConf(ndmap.item(j).getNodeValue());
					else if (ndmap.item(j).getNodeName() == "COMMENT")
						swsItem.setComment(ndmap.item(j).getNodeValue());
				}
				SwsItemProvider.getInstance().setSwsItem(swsItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	public TableViewer getViewer() {
		return tableViewer_1;
	}

}
