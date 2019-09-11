package dms.konkuk.autosar.ct.model;

import java.util.ArrayList;
import java.util.List;

public class SwsItemProvider {
	
	private static SwsItemProvider content;
	private List<SwsItem> items;
	
	private SwsItemProvider()	{
		items = new ArrayList<SwsItem>();
//		swsitem = new SwsItem(1, "CanNm_001", "CanNm_001a", "test message test message test message", "AUTOSAR_CanNm_Ecuc_1", "Dynamic Test", "yes", "TTCN-3", "CANNM_001a", "This is comment");
//		items.add(swsitem);
//		swsitem = new SwsItem(2, "CanNm_002", "CanNm_001a", "test message", "AUTOSAR_CanNm_Ecuc_1", "Dynamic Test", "yes", "TTCN-3", "CANNM_001a", "This is comment");
//		items.add(swsitem);
//		swsitem = new SwsItem(3, "CanNm_003", "CanNm_001a", "test message", "AUTOSAR_CanNm_Ecuc_1", "Dynamic Test", "yes", "TTCN-3", "CANNM_001a", "This is comment");
//		items.add(swsitem);
	}
	
	public static synchronized SwsItemProvider getInstance()	{
		if (content != null) {
			return content;
		}
		content = new SwsItemProvider();
		return content;
	}
	
	public List<SwsItem> getSwsItems()	{
		return items;
	}
	
	public void setSwsItem(SwsItem si)	{
		items.add(si);
		return;
	}
	
	public void clear(){
		items.clear();
	}
	
	public int getBiggestNo()	{
		int bigNo=0;
		int compareNo;
		for(SwsItem item : items)	{
			compareNo = item.getNo();
			if (bigNo < compareNo)	{
				bigNo = compareNo;	
			}
		}
		return bigNo;			
	}
	
	public void deleteSwsItem(int no)	{
		for (int i=0; i < items.size(); i++)	{
			if( no ==items.get(i).getNo())
				items.remove(i);
		}
		arrangeNo();
	}
	
	private void arrangeNo(){
		for(int count=0; count < items.size(); count++)	{
			items.get(count).setNo(count+1);
		}
		
	}
}
