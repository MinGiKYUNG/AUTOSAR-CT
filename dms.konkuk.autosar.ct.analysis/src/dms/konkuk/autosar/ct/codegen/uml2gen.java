package dms.konkuk.autosar.ct.codegen;



import java.io.*;
import java.util.*;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.util.ImportManager;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.eclipse.uml2.uml.util.UMLUtil.UML2EcoreConverter;


public class uml2gen{
	/**
	 * @param args
	 */

	public void gen(String path, String directory,String requirements){
	//public static void main(String args[]) {
		// TODO Auto-generated method stub
	
		String filePath = path;
		URI uml_model_path = URI.createFileURI(filePath);
		
		ResourceSet rs = new ResourceSetImpl();
		rs.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap()
		
		.put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
		
		Map<URI, URI> uriMap = rs.getURIConverter().getURIMap();
		URI uri = URI.createURI("jar:file:/C:/ACT/org.eclipse.uml2.uml.resources_2.2.0.v200805131030.jar!/");
		uriMap.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP), 
				uri.appendSegment("libraries").appendSegment(""));
		uriMap.put(URI.createURI(UMLResource.METAMODELS_PATHMAP), 
				uri.appendSegment("metamodels").appendSegment(""));
		uriMap.put(URI.createURI(UMLResource.PROFILES_PATHMAP), 
				uri.appendSegment("profiles").appendSegment(""));
		
		
		UMLResource res = (UMLResource) rs.getResource(uml_model_path,true);
		TreeIterator<EObject> iter = res.getAllContents();
		while (iter.hasNext()){
			EObject obj = iter.next();
		}
		// TODO Convert UML to Ecore		
		Package pack = (Package) EcoreUtil.getObjectByType(res.getContents(), UMLPackage.Literals.PACKAGE);

		Map<String, String> optionsMap = new HashMap<String, String>() ;
		optionsMap.put(UML2EcoreConverter.OPTION__ANNOTATION_DETAILS, UMLUtil.OPTION__PROCESS);
		Collection<? extends EPackage> ecoreElements = UMLUtil.convertToEcore(pack, optionsMap); 
		ecoreElements.getClass().toString();

		// TODO SAVING ecore to file
		//XMIResource ecoreRes  = (XMIResource)ecoreElements;
		
		// TODO Convert Ecore to Genmodel
		GenModel gen = GenModelFactory.eINSTANCE.createGenModel();
		gen.initialize(ecoreElements); 
		gen.setComplianceLevel(GenJDKLevel.JDK50_LITERAL);
		initializeModel(gen);

		GenPackage genp = gen.createGenPackage();
		genp.setEcorePackage(ecoreElements.iterator().next());
		genp.getEcorePackage().setName("Archtecture.gen_java");
		//GenModel genModel = genp.getGenModel();	/* test */
		gen.setModelName("AUTOSAR_TESTING_-_EEPROM");

		EList<EClassifier> elist = genp.getEcorePackage().getEClassifiers();

		Iterator<EClassifier> eit = elist.iterator();
		GenClass genClass = null;
		while (eit.hasNext()){
			genClass = (GenClass)gen.findGenClassifier(
					genp.getEcorePackage().getEClassifier(eit.next().getName()));
			prepareModel(gen, genClass.getQualifiedClassName());
		}	    
		// TODO Generate JAVA from Genmodel and Ecore
		JAVATemplates JETClass = new JAVATemplates(); 

		Iterator<EClassifier> JETClassIter = elist.iterator();

		////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////
		Map<String,String> Depndency_Map = new java.util.HashMap<String,String>();
		Iterator<EClassifier> JETClassIter_test = elist.iterator();
		GenClass genJETClass_test = null;
		List<String> Component_List = new ArrayList();
		while (JETClassIter_test.hasNext()){
			genJETClass_test = (GenClass)gen.findGenClassifier(
					genp.getEcorePackage().getEClassifier(JETClassIter_test.next().getName())); 
			if(!genJETClass_test.isInterface())
			{
				Component_List.add(genJETClass_test.getName());
			}
		}

		EList<PackageableElement> temp_list1 =pack.getPackagedElements(); 
		Iterator<PackageableElement> temp_iterator1 = temp_list1.iterator();
		while (temp_iterator1.hasNext()){
			PackageableElement packageableElement = temp_iterator1.next();
			if(packageableElement.getName() !=  null)
			{					
				String Element_Name = packageableElement.getName();
				EList<Dependency> temp_list2 = packageableElement.getClientDependencies();
				Iterator<Dependency> temp_iterator2 = temp_list2.iterator();
				while (temp_iterator2.hasNext()){
					Dependency dependency = temp_iterator2.next();			
					if(dependency.getSuppliers() != null)
					{
						EList<NamedElement> temp_list3 = dependency.getSuppliers();
						Iterator<NamedElement> temp_iterator3 = temp_list3.iterator();						
						while (temp_iterator3.hasNext()){
							String SupplierName = temp_iterator3.next().getName();
							for(int i = 0; i<Component_List.size(); i++)
							{
								if(SupplierName.equals(Component_List.get(i)))
								{
									Depndency_Map.put(Element_Name,SupplierName);
									break;
								}
							}
						}		
					}
				}
			}
		}	  
		///////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////
		GenClass genJETClass = null;
		String Class_sb_1 = requirements;
		String Class_sb_2= null;
		String FILEURL = null;
		String fileName = null;
		//GenBase genBase = null;

		while (JETClassIter.hasNext()){
			genJETClass = (GenClass)gen.findGenClassifier(
					genp.getEcorePackage().getEClassifier(JETClassIter.next().getName())); 
			fileName = genJETClass.getGenPackage().getEcorePackage().getName();

			if(!genJETClass.isInterface())
			{
				//FILEURL = "c:\\" +"TestArchitecture\\java_gen";
				FILEURL = directory;
				File file = new File(FILEURL);
				file.mkdirs();
				//interface element 
				if(!genJETClass.getImplementedGenOperations().isEmpty())
				{
					/*Class_sb_2 = JETClass.generate(new Object[]{genJETClass, 
							genJETClass.isInterface(),false, Depndency_Map,elist,genp});
					fileName = genJETClass.getInterfaceName();*/
				}
				//component element
				else
				{
					Class_sb_1 += JETClass.generate(new Object[]{genJETClass, 
							genJETClass.isInterface(),true, Depndency_Map,elist,genp});
					fileName= genJETClass.getName();
					try{
						File file_1 = new File(FILEURL + "\\" + fileName + ".java");
						OutputStream out_1 = new FileOutputStream(file_1); 
						out_1.write(Class_sb_1.getBytes());
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			else
			{}
		}
		
		List<String> standardTypes_List = new ArrayList();
		List<String> general_List = new ArrayList();
		standardTypes_List.add("CTEaGeneral");
		standardTypes_List.add("EaBlockConfiguration");
		standardTypes_List.add("EaGeneral");
		standardTypes_List.add("Std_ReturnType");
		standardTypes_List.add("Std_VersionInfoType");
		standardTypes_List.add("SymbolDefinitions");
		general_List.add("Serviceid");
		general_List.add("TypeWrapper");
		Iterator<String> STIter = standardTypes_List.iterator();
		testcase_templates tt = new testcase_templates();
		
		while (STIter.hasNext()){
			String Temp = null;
			String Temp1 = STIter.next();
			//FILEURL = "c:\\" +"TestArchitecture\\java_gen\\TestCase_standardTypes";
			FILEURL = directory + "\\TestCase_standardTypes";
			File file = new File(FILEURL);
			file.mkdirs();
			Temp = tt.generate(new Object[]{"Architecture.gen_java.TestCase_standardTypes",Temp1});
			try{
				File file_1 = new File(FILEURL + "\\" + Temp1 + ".java");
				OutputStream out = new FileOutputStream(file_1); 
				out.write(Temp.getBytes());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		Iterator<String> GIter = general_List.iterator();
		while (GIter.hasNext()){
			String Temp = null;
			String Temp1 = GIter.next();
			//FILEURL = "c:\\" +"TestArchitecture\\java_gen\\TestCase_general";
			FILEURL = directory + "\\TestCase_general";
			File file = new File(FILEURL);
			file.mkdirs();
			Temp = tt.generate(new Object[]{"Architecture.gen_java.TestCase_general",Temp1});
			try{
				File file_1 = new File(FILEURL + "\\" + Temp1 + ".java");
				OutputStream out = new FileOutputStream(file_1); 
				out.write(Temp.getBytes());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("gen complete");
	}
	
	
	
	
	public void initializeModel(GenModel genModel)
	{
		for (Iterator<EObject> i = genModel.eAllContents(); i.hasNext(); )
		{
			EObject object = i.next();
			if (object instanceof GenBase){
				GenBase genBase = (GenBase)object;
				EModelElement eModelElement = genBase.getEcoreModelElement();
				if (eModelElement != null){
					EAnnotation eAnnotation = eModelElement.getEAnnotation(GenModelPackage.eNS_URI);
					if (eAnnotation != null){
						for (Map.Entry<String, String> entry : eAnnotation.getDetails()){
							EStructuralFeature feature = genBase.eClass().getEStructuralFeature(entry.getKey());
							if (feature instanceof EAttribute){
								EAttribute attribute = (EAttribute)feature;
								genBase.eSet(attribute, EcoreUtil.createFromString(attribute.getEAttributeType()
										, entry.getValue()));
							}}}}}}
	}

	public void prepareModel(GenModel genModel, String qualifiedClassName)
	{
		int i = qualifiedClassName.lastIndexOf('.');
		String packageName = i != -1 ? qualifiedClassName.substring(0, i) : null;
		String className = i != -1 ? qualifiedClassName.substring(i + 1) : qualifiedClassName;

		ImportManager importManager = new ImportManager(packageName);
		importManager.addMasterImport(packageName, className);
		genModel.setImportManager(importManager);
	}
}