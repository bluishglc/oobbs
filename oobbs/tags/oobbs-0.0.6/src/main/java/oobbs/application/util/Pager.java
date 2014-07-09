package oobbs.application.util;

import java.io.Serializable;

public class Pager implements Serializable{

	private static final long serialVersionUID = 7329023367494568303L;

	private String action;
	
	private int recordTotal;
	
	private int pageTotal; 
	
	private int pageSize; 

	/** The requested page number.*/
	private int targetPage=1;
	
	private boolean hasPreviousPage; 
	
	private boolean hasNextPage;

	public Pager(int recordTotal, int pageSize){
		this.recordTotal = recordTotal;
		this.pageSize = pageSize;
	}
	
	/*---------------------------------    Main Logic Methods    ---------------------------------*/
	
	public void navigateTo(int targetPage){
		this.targetPage = targetPage;
		flush();
	}

	public void reset(int recordTotal, int pageSize){
		this.recordTotal = recordTotal;
		this.pageSize = pageSize;
	}
	
	public int getStartRecordIndex() {
		return (targetPage - 1) * pageSize;
	}

	public int getPageTotal() {
		return (recordTotal + pageSize - 1) / pageSize;
	}

	public void flush() {
		pageTotal = (recordTotal + pageSize - 1) / pageSize;
		if(targetPage < 1 || targetPage > pageTotal){
			throw new RuntimeException("The target page number is invalid!");
		}
		if (pageTotal <= 1) {
			hasPreviousPage = false;
			hasNextPage = false;
		} else if (targetPage == 1) {
			hasPreviousPage = false;
			hasNextPage = true;
		} else if (targetPage == pageTotal) {
			hasPreviousPage = true;
			hasNextPage = false;
		} else {
			hasPreviousPage = true;
			hasNextPage = true;
		}
	}

	public String getHtmlSnippet(){
		
        StringBuffer snippet = new StringBuffer("");
        for(int i=1;i<=pageTotal;i++){
        	snippet.append("<a href='"+action+"&postListPager.targetPage="+i+"'>"+i+"</a>,");        			
        }       
        return snippet.toString();
    }
    
	/*----------------------------------    Accessor Methods    ----------------------------------*/
	
	public int getRecordTotal() {
		return recordTotal;
	}

	public void setRecordTotal(int recordTotal) {
		this.recordTotal = recordTotal;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}
	
    public int getTargetPage() {
		return targetPage;
	}

	public void setTargetPage(int targetPage) {
		this.targetPage = targetPage;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}	

}
