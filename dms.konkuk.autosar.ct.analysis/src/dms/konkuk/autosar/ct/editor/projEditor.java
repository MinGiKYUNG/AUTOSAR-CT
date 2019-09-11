package dms.konkuk.autosar.ct.editor;



import java.io.File;
import java.io.IOException;

import org.apache.xerces.parsers.DOMParser;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.xml.sax.SAXException;
import org.apache.xalan.serialize.Serializer;
import org.apache.xalan.serialize.SerializerFactory;
import org.apache.xalan.templates.OutputProperties;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.StringTokenizer;

import dms.konkuk.autosar.ct.model.*;
import dms.konkuk.autosar.ct.birt.BirtView;
import dms.konkuk.autosar.ct.birt.ReportFactory;
import dms.konkuk.autosar.ct.birt.XMLValidation;
import dms.konkuk.autosar.ct.codegen.*;

public class projEditor extends EditorPart {

	public static final String ID = "dms.konkuk.autosar.ct.editor.projEditor"; //$NON-NLS-1$
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private FormToolkit toolkit;
	private Form form;
	private Text txtNewText;
	
	/*************************************************/
	private String sws_file_name;
	private String testcase_file_name;
	private String uml_file_name;
	Combo combo;
	Combo combo_1;
	Combo combo_31;
	Combo combo_2;
	String project_name;
	String uml_path;
	String testcase_path;
	String sws_path;
	IWorkbenchPartSite view;
	String full_path;
	Combo combo_31111;
	private IWorkbenchWindow window;
	/*************************************************/
	
	public projEditor() {
		
	}

	/**
	 * Create contents of the editor part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createForm(parent);
		form.setText("AUTOSAR Conformance Test Info");
		TableWrapLayout layout = new TableWrapLayout();
		form.getBody().setLayout(layout);
		TableWrapData td = new TableWrapData();
		td.colspan = 2;

		Label lblThisFileContains = toolkit.createLabel(form.getBody(),
				"This file contains internal information of current AUTOSAR Conformance Test Project.", SWT.NONE);

		Label lblThisActProject = toolkit.createLabel(form.getBody(),
				"This ACT project is created for testing CanNm (Controller Area Network: Network Management) Module.",
				SWT.NONE);
		
		Label label = toolkit.createLabel(form.getBody(), "", SWT.NONE);
		
		/*************************************************/
		IEditorInput input = getEditorInput();
		IFile jfile = ((IFileEditorInput)input).getFile();
		project_name = jfile.getProject().getName();
		IProject[] ip = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		full_path = null;
		for(int i=0; i<ip.length; i++){
			full_path = ip[i].getLocation().toOSString();	
			if(full_path.endsWith(project_name))break;
		}
		/*************************************************/

		// Analysis section
		Section sctnAnalysisTool = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		sctnAnalysisTool.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP, 1, 1));
		sctnAnalysisTool.setText("Analysis Tool");

		Composite composite = toolkit.createComposite(sctnAnalysisTool, SWT.NONE);
		sctnAnalysisTool.setClient(composite);
		composite.setLayout(null);

		Label lblCurrentStatus = toolkit.createLabel(composite, "Current Status:", SWT.NONE);
		lblCurrentStatus.setBounds(5, 5, 84, 12);

		

		Label lblBswName = toolkit.createLabel(composite, "BSW Name:", SWT.NONE);
		lblBswName.setBounds(21, 25, 68, 12);

		txtNewText = toolkit.createText(composite, "New Text", SWT.NONE);
		txtNewText.setBounds(94, 22, 476, 18);
		txtNewText.setText("CanNm");

		Label lblSwsFiles = toolkit.createLabel(composite, "SWS Files: ", SWT.NONE);
		lblSwsFiles.setBounds(24, 49, 65, 12);

		/******************************/
		sws_path = full_path + "//" + "Requirements" ;
		File f = new File(sws_path);
		String s[] = f.list();	
		/******************************/
		
		Label lblYouDefined_1 = toolkit.createLabel(composite, "You defined " + s.length+" Software Specification files.", SWT.NONE);
		lblYouDefined_1.setBounds(94, 5, 237, 12);
		
		ComboViewer comboViewer_1 = new ComboViewer(composite, SWT.BORDER);
		combo = comboViewer_1.getCombo();
		combo.setBounds(94, 45, 476, 20);
		combo.setItems(s);
		Button btnAnalysisSws = toolkit.createButton(composite, "Analysis SWS", SWT.NONE);	
		btnAnalysisSws.setBounds(94, 70, 476, 22);
		view = this.getSite();
		/**************************************************************/
		btnAnalysisSws.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				sws_file_name = project_name + "//" + "Requirements" + "//" + combo.getItem(combo.getSelectionIndex());
				IFile req_file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(sws_file_name));
				IEditorInput input = new FileEditorInput(req_file);
				IWorkbenchPage page = view.getPage();
				try {
					page.openEditor(input, "dms.konkuk.autosar.ct.editor.SWSEditor");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		sctnAnalysisTool.setExpanded(true);

		// Design section
		Section sctnDesignTool = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		sctnDesignTool.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP, 1, 1));
		sctnDesignTool.setText("Design Tool");

		Composite composite_1 = toolkit.createComposite(sctnDesignTool, SWT.NONE);
		sctnDesignTool.setClient(composite_1);
		composite_1.setLayout(null);

		Label lblCurrentStatus_1 = toolkit.createLabel(composite_1, "Current Status:", SWT.NONE);
		lblCurrentStatus_1.setBounds(21, 5, 84, 12);

		

		Label lblTestArchitecture = toolkit.createLabel(composite_1, "Test Architecture:", SWT.NONE);
		lblTestArchitecture.setBounds(5, 44, 100, 12);

		/***************************/
		uml_path = full_path + "//" + "Architecture";
		File f1 = new File(uml_path);
		String s1[] = f1.list();	
		/***************************/
		
		ComboViewer comboViewer_2 = new ComboViewer(composite_1, SWT.BORDER);
		combo_1 = comboViewer_2.getCombo();
		combo_1.setBounds(110, 40, 254, 20);
		int count = 0;
		for(int i=0; i<s1.length; i++){
			if(s1[i].endsWith("uml")){
				combo_1.add(s1[i]);
				++count;
			}
		}

		Label lblTestCase = toolkit.createLabel(composite_1, "Requirements:", SWT.NONE);
		lblTestCase.setBounds(23, 71, 82, 12);

		ComboViewer comboViewer_3 = new ComboViewer(composite_1, SWT.BORDER);
		combo_2 = comboViewer_3.getCombo();
		combo_2.setBounds(110, 67, 254, 20);
		combo_2.setItems(s);

		Label lblYouDefined = toolkit.createLabel(composite_1, "You defined "+count+" Architecture.", SWT.NONE);
		lblYouDefined.setBounds(110, 5, 156, 12);

		Label lblYouUse = toolkit.createLabel(composite_1, "You defined " +s.length+" Requirements.", SWT.NONE);
		lblYouUse.setBounds(110, 22, 167, 12);
		
		Button button = new Button(composite_1, SWT.NONE);
		button.setBounds(370, 39, 200, 22);
		toolkit.adapt(button, true, true);
		formToolkit.adapt(button, true, true);
		button.setText("Show Architecture");
		
		Button button_2 = new Button(composite_1, SWT.NONE);
		button_2.setBounds(370, 66, 200, 22);
		toolkit.adapt(button_2, true, true);
		formToolkit.adapt(button_2, true, true);
		button_2.setText("Code Generation");
		sctnDesignTool.setExpanded(true);
		
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {			
				uml_file_name = project_name + "//" + "Architecture" + "//" +  combo_1.getItem(combo_1.getSelectionIndex())+"comp";
				IFile ff = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uml_file_name));
				IEditorInput input = new FileEditorInput(ff);	
				IWorkbenchPage page = view.getPage();
				try {
					page.openEditor(input, "org.eclipse.uml2.diagram.component.part.UMLDiagramEditorID");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		button_2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {	
				if(combo_1.getSelectionIndex()<0 || combo_2.getSelectionIndex()<0){
					MessageDialog.openInformation(Display.getCurrent().getActiveShell(),"ACT Tool","Please select Requirement & Architecture.");
				}
				/*************************************************/
				String sws_file_path = full_path + "//" + "Requirements" + "//" + combo_2.getItem(combo_2.getSelectionIndex());
				String buffer = null;
				buffer = "	/***\n";
				Document Doc = null;
				try {
					DOMParser dom = new DOMParser();
					dom.parse(sws_file_path);
					Doc = dom.getDocument();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Node root = Doc.getElementsByTagName("ACT_SWS").item(0);
				NodeList item_list = root.getChildNodes();
				for(int i=0; i<item_list.getLength(); i++){
					if(item_list.item(i).getNodeName().equals("SWS_ITEM")){
						String NO= null,ID= null,Refined_ID= null,Descripttion= null,Configuration= null,Category= null,
						Relevance_to_CT= null,How_to_Test= null,Bugzilla_Reference= null,Comment = null;
						NamedNodeMap atr_list = item_list.item(i).getAttributes();
						for(int j=0; j<atr_list.getLength(); j++){
							if(atr_list.item(j).getNodeName().equals("BUGZILLA_ID")){
								Bugzilla_Reference = atr_list.item(j).getNodeValue();
							}else if(atr_list.item(j).getNodeName().equals("CATEGORY")){
								Category = atr_list.item(j).getNodeValue();
							}else if(atr_list.item(j).getNodeName().equals("COMMENT")){
								Comment = atr_list.item(j).getNodeValue();
							}else if(atr_list.item(j).getNodeName().equals("CONF")){
								Configuration = atr_list.item(j).getNodeValue();
							}else if(atr_list.item(j).getNodeName().equals("DESCRIPTION")){
								Descripttion = atr_list.item(j).getNodeValue();
							}else if(atr_list.item(j).getNodeName().equals("HOWTOTEST")){
								How_to_Test = atr_list.item(j).getNodeValue();
							}else if(atr_list.item(j).getNodeName().equals("ID")){
								ID = atr_list.item(j).getNodeValue();
							}else if(atr_list.item(j).getNodeName().equals("NO")){
								NO = atr_list.item(j).getNodeValue();
							}else if(atr_list.item(j).getNodeName().equals("REF_ID")){
								Refined_ID = atr_list.item(j).getNodeValue();
							}else if(atr_list.item(j).getNodeName().equals("RELEVANCE_TO_CT")){
								Relevance_to_CT = atr_list.item(j).getNodeValue();
							}
						}
						buffer +="	*"+
							"NO: "+NO + " ID: " + ID+" Refined ID: "+Refined_ID+ " Descripption: " +Descripttion +" Configuration: "+
						Configuration + " Category: "+Category+" Relevence to CT: "+Relevance_to_CT+" How to Test: "+ How_to_Test
						+ " Comment: " +Comment + "\n";
					}
				}
				buffer += "	***/\n\n\n";
				//System.out.println(buffer);
				/****************************************************/
				String gen_uml_path = uml_path + "\\" + combo_1.getItem(combo_1.getSelectionIndex());
				uml2gen u2g = new uml2gen();
				u2g.gen(gen_uml_path, full_path + "\\TestCase",buffer);	
				MessageDialog.openInformation(Display.getCurrent().getActiveShell(),"ACT Tool","Code Generation complete.");
			}
		});

		// Implementation section
		Section sctnImplementationTool = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		sctnImplementationTool.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP, 1, 1));
		sctnImplementationTool.setText("Implementation Tool");

		Composite composite_2 = toolkit.createComposite(sctnImplementationTool, SWT.NONE);
		sctnImplementationTool.setClient(composite_2);
		composite_2.setLayout(null);

		Label lblCurrentStatus_2 = toolkit.createLabel(composite_2, "Current Status:", SWT.NONE);
		lblCurrentStatus_2.setBounds(5, 5, 84, 12);

		

		Label lblTestCase2 = toolkit.createLabel(composite_2, "Test Case:", SWT.NONE);
		lblTestCase2.setBounds(26, 27, 63, 12);

		/***************************/
		testcase_path = full_path + "//" + "TestCase";
		File f2 = new File(testcase_path);
		String s2[] = f2.list();	
		/***************************/
		ComboViewer comboViewer_41 = new ComboViewer(composite_2, SWT.BORDER);
		combo_31 = comboViewer_41.getCombo();
		combo_31.setBounds(94, 23, 270, 20);
		int count_ = 0;
		for(int i=0; i<s2.length; i++){
			if(s2[i].endsWith("java")){
				combo_31.add(s2[i]);
				++count_;
			}
		}
		Label lblYouImplemented = toolkit.createLabel(composite_2,
				"You implemented "+count_+" test cases.", SWT.NONE);
		lblYouImplemented.setBounds(94, 5, 417, 12);
		
		Button button_1 = new Button(composite_2, SWT.NONE);
		button_1.setBounds(370, 23, 200, 22);
		toolkit.adapt(button_1, true, true);
		formToolkit.adapt(button_1, true, true);
		button_1.setText("Show Test Case");
		sctnImplementationTool.setExpanded(true);
		button_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				testcase_file_name = project_name + "//" + "TestCase" + "//" + combo_31.getItem(combo_31.getSelectionIndex());
				IFile ff = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(testcase_file_name));
				//IClassFile req_file = (IClassFile) ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(testcase_file_name));
				//IEditorInput input = new InternalClassFileEditorInput(req_file);
				IEditorInput input = new FileEditorInput(ff);	
				IWorkbenchPage page = view.getPage();
				try {
					page.openEditor(input, "org.eclipse.jdt.ui.CompilationUnitEditor");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		// Validation section
		Section sctnValidationTool = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		sctnValidationTool.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP, 1, 1));
		sctnValidationTool.setText("Validation Tool");
		
		Composite composite_3 = toolkit.createComposite(sctnValidationTool, SWT.NONE);
		composite_3.setLayout(new GridLayout(3, false));
		sctnValidationTool.setClient(composite_3);
		
		Label lblTestCase21 = toolkit.createLabel(composite_3, "Test Suite:", SWT.NONE);
		lblTestCase21.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

		ComboViewer comboViewer_411 = new ComboViewer(composite_3, SWT.BORDER);
		Combo combo_311 = comboViewer_411.getCombo();
		combo_311.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelect_31 = toolkit.createButton(composite_3, "Select...", SWT.NONE);
		
		Label lblTestCase211 = toolkit.createLabel(composite_3, "Test Executable:", SWT.NONE);
		lblTestCase211.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

		ComboViewer comboViewer_4111 = new ComboViewer(composite_3, SWT.BORDER);
		Combo combo_3111 = comboViewer_4111.getCombo();
		combo_3111.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelect_311 = toolkit.createButton(composite_3, "Select...", SWT.NONE);
		new Label(composite_3, SWT.NONE);
		
		Button btnExecuteTC = toolkit.createButton(composite_3, "Execute AUTOSAR Conformance Test", SWT.NONE);
		btnExecuteTC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		sctnValidationTool.setExpanded(true);

		
		
		
		/*************************************************************************/
		
		// Reporting section
		Section sctnReportingTool = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		sctnReportingTool.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP, 1, 1));
		sctnReportingTool.setText("Reporting Tool");
		
		Composite composite_31 = toolkit.createComposite(sctnReportingTool, SWT.NONE);
		composite_31.setLayout(new GridLayout(3, false));
		sctnReportingTool.setClient(composite_31);
		sctnReportingTool.setExpanded(true);
		
		Label lblTestCase2111 = toolkit.createLabel(composite_31, "Test Executable:", SWT.NONE);
		lblTestCase2111.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

		ComboViewer comboViewer_41111 = new ComboViewer(composite_31, SWT.BORDER);
		//additional thing 주석처리
		combo_31111 = comboViewer_41111.getCombo();
		combo_31111.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		//addional things...
		//-To Do sws_path 재사용해도 될까?
		//1. Report path를 통해서 file.listh함수로 compobox에 들어갈 문자열을 생성했다.
		sws_path = full_path + "//" + "Report";
		File file = new File(sws_path);
		String string[] = file.list();
		
		//2. 콤보 박스에 값을 집어 넣었다.
		combo_31111.setItems(string);
		
		
		//3. 버튼 생성을 하고, 버튼에 Triggered되어서 birt report를 진행(3-1. rpd design인식, 3-2. 생성된 파일 인식
		
		//3-1 버튼 생성
		Button btnSelect_3111 = toolkit.createButton(composite_31, "Select...", SWT.NONE);
		new Label(composite_31, SWT.NONE);
		Button btnReport = toolkit.createButton(composite_31, "View AUTOSAR Conformance Test Result", SWT.NONE);
		
		//3-1-1. 버튼 리스너 생성(3-2. 생성된 파일인식, 생성된 파일 목록 중 콤보박스에 선택된 것을 인식하는 과정)
		btnReport.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				
				//선택된 콤보 박스로 부터 파일 경로 읽어오기.
				sws_file_name = full_path+ "\\" + "Report" + "\\" + combo_31111.getItem(combo_31111.getSelectionIndex());
				IFile req_file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(sws_file_name));
				IEditorInput input = new FileEditorInput(req_file);
				IWorkbenchPage page = view.getPage();
				
				
				//
				boolean xmlflag = true;
				Shell targetPart = Display.getCurrent().getActiveShell();
				
				try {
					//이 부분에 서 rpt design과 파일 출력.
					//birt view 활성화 
					if(xmlflag)
					{
						XMLValidation xmlvalidation = new XMLValidation(sws_file_name);
						//선택된 XML파일이 올바른지 검사후 BIRT VIEW 실행 
						if(xmlvalidation.go())
						{
							//워크스페이스 경로 읽어오기 
							IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
							
							//레포트 생성 
							ReportFactory.getReportFactory().setworkspacepath(root.getLocation().toOSString());
							ReportFactory.getReportFactory().setxmlpath(sws_file_name);///////
							
							//레포트 생성 실패시 액션 종료 
							if(!ReportFactory.getReportFactory().ReportCreate("document"))
							{
								MessageDialog.openInformation(targetPart,"ACT Tool","Report create failed.");
							}
							else{
								//BIRT VIEW 활성화 
								window = PlatformUI.getWorkbench().getWorkbenchWindows()[0];
								if(window == null)
								{
									return;
								}
								page = window.getActivePage();
								if(page == null)
								{
									return;
								}
								try {
									page.showView("dms.konkuk.autosar.ct.analysis.view1");
								} catch (PartInitException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						else
						{
							MessageDialog.openInformation(targetPart,"Autosar","This file is not correct type.");
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		btnReport.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		/*************************************************************************/
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		// Initialize the editor part
		try {
			setSite(site);
			setInput(input);
			setPartName(input.getName());

		} catch (Exception e) {
			throw new PartInitException(e.getMessage());
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
}
