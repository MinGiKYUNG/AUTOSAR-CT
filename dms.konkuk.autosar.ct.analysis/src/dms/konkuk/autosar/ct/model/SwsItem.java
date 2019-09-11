package dms.konkuk.autosar.ct.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SwsItem {
	private Integer no;
	private String swsID;
	private String reqID;
	private String description;
	private String conf;
	private String category;
	private String relevanceToCT;
	private String howToTest;
	private String BugzillaID;
	private String comment;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public SwsItem() {
	}

	public SwsItem(Integer no, String swsID, String reqID, String description, String conf, String category,
			String relevanceToCT, String howToTest, String BugzillaID, String comment) {
		super();
		this.no = no;
		this.swsID = swsID;
		this.reqID = reqID;
		this.description = description;
		this.conf = conf;
		this.category = category;
		this.relevanceToCT = relevanceToCT;
		this.howToTest = howToTest;
		this.BugzillaID = BugzillaID;
		this.comment = comment;
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public Integer getNo() {
		return no;
	}
	
	public String getSwsID() {
		return swsID;
	}

	public String getReqID() {
		return reqID;
	}

	public String getDescription() {
		return description;
	}

	public String getConf() {
		return conf;
	}

	public String getCategory() {
		return category;
	}

	public String getRevelanceToCT() {
		return relevanceToCT;
	}

	public String getHowToTest() {
		return howToTest;
	}

	public String getBugzillaID() {
		return BugzillaID;
	}

	public String getComment() {
		return comment;
	}
	
	public void setNo(Integer no) {
		propertyChangeSupport.firePropertyChange("No", this.no, this.no = no);
	}
	public void setSwsID(String swsID) {
		propertyChangeSupport.firePropertyChange("swsID", this.swsID, this.swsID = swsID);
	}

	public void setReqID(String reqID) {
		propertyChangeSupport.firePropertyChange("reqID", this.reqID, this.reqID = reqID);
	}

	public void setDescription(String description) {
		propertyChangeSupport.firePropertyChange("description", this.description, this.description = description);
	}

	public void setConf(String conf) {
		propertyChangeSupport.firePropertyChange("conf", this.conf, this.conf = conf);
	}

	public void setCategory(String category) {
		propertyChangeSupport.firePropertyChange("category", this.category, this.category = category);
	}

	public void setRelevanceToCT(String relevanceToCT) {
		propertyChangeSupport.firePropertyChange("relevanceToCT", this.relevanceToCT,
				this.relevanceToCT = relevanceToCT);
	}

	public void setHowToTest(String howToTest) {
		propertyChangeSupport.firePropertyChange("howToTest", this.howToTest, this.howToTest = howToTest);
	}

	public void setBugzillaID(String bugzillaID) {
		propertyChangeSupport.firePropertyChange("bugzillaID", this.BugzillaID, this.BugzillaID = bugzillaID);
	}

	public void setComment(String comment) {
		propertyChangeSupport.firePropertyChange("comment", this.comment, this.comment = comment);
	}
	
	@Override
	public String toString()	{
		return swsID+"|"+comment;
		
	}
}
