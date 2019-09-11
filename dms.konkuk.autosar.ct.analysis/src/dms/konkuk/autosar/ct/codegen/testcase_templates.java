package dms.konkuk.autosar.ct.codegen;



//package org.eclipse.emf.codegen.ecore.templates.model;
import java.util.*;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;



//import autosar.ct.eeprom.IMTC.Std_VersioninfoType;

public class testcase_templates
{
	protected static String nl;
	public static synchronized JAVATemplates create(String lineSeparator)
	{
		nl = lineSeparator;
		JAVATemplates result = new JAVATemplates();
		nl = null;
		return result;
	}

	public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
	protected final String TEXT_1 = "package ";

	public String generate(Object argument)
	{
		final StringBuffer stringBuffer = new StringBuffer();
		final String packagename = (String)((Object[])argument)[0];
		final String classename = (String)((Object[])argument)[1];
		stringBuffer.append(TEXT_1);
		stringBuffer.append(packagename + ";"+ NL + NL);
		stringBuffer.append("import java.util.*;" + NL + NL);
		TempSource ts = new TempSource();
		stringBuffer.append(ts.getsource(classename));
		stringBuffer.append( NL + NL);
		return stringBuffer.toString();
	}
}
