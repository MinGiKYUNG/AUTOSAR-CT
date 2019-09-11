package dms.konkuk.autosar.ct.model;

import org.eclipse.jface.viewers.IStructuredSelection;

public class DataRepository {
	private static DataRepository instance;
	private IStructuredSelection selections;
	private String fullPath;
	private String workspacePath;
	private String selectName;
	private String projectName;
	private String projectPath;
	private DataRepository()
	{}
	
	public static DataRepository GetInstance()
	{
		if(instance == null)
			instance = new DataRepository();
		return instance;
	}
	
	public void insertData()
	{
		
	}
	
	public String getFullPath()
	{
		return fullPath;
	}
	public String getWorkspacePath()
	{
		return workspacePath;
	}
	public String getSelectName()
	{
		return selectName;
	}
	public String getProjectName()
	{
		return projectName;
	}
	public String getProjectPath()
	{
		return projectPath;
	}
	
	public IStructuredSelection getSelection()
	{
		return selections;
	}
	
	public void setFullPath(String s)
	{
		fullPath = s;
	}
	
	public void setWorkspacePath(String s)
	{
		workspacePath = s;
	}
	
	public void setSelectName(String s)
	{
		selectName = s;
	}
	
	public void setProjectName(String s)
	{
		projectName = s;
	}
	
	public void setProjectPath(String s)
	{
		projectPath = s;
	}
	
	public void setSelection(IStructuredSelection sel)
	{
		selections = sel;
	}
}
