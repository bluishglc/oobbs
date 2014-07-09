package oobbs.presentation.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import oobbs.Constants;
import oobbs.application.util.Pager;

public class PagingTag  extends TagSupport {

	private static final long serialVersionUID = 975365986735469291L;
	
	private String action;
	
	private String param;

	public int doStartTag() throws JspException {		
		Pager pager = (Pager) pageContext.getAttribute(Constants.PAGER, PageContext.REQUEST_SCOPE);
		int offset = 3;
		int recordTotal = pager.getRecordTotal();
		int targetPage = pager.getTargetPage();
		int pageTotal = pager.getPageTotal();
		StringBuffer snippet = new StringBuffer("");
		//Appends summary.
		snippet.append("Page "+targetPage+" of "+pageTotal+" ["+recordTotal+" topics]");
		snippet.append("Go To Page:");
		//Appends previous link.
		if(pager.isHasPreviousPage()){
			snippet.append("<a href='").append(action).append("&").append(param).append("=").append(targetPage-1).append("'>").append("Previous").append("</a>,");
		} 
		//Appends first page link.
		if(targetPage-offset>1){
			snippet.append("<a href='").append(action).append("&").append(param).append("=").append(1).append("'>").append("1").append("</a>,");
		}
		//Appends ellipsis.
		if(targetPage-offset>2){
			snippet.append("...,");
		}	
		//Appends center pages.
		int startIndex = targetPage-offset<=1?1:targetPage-offset;
		int lastIndex = targetPage+offset>=pageTotal?pageTotal:targetPage+offset;
        for(int i=startIndex;i<=lastIndex;i++){
        	if(i==targetPage){
        		snippet.append(i).append(",");
        	}else{
        		snippet.append("<a href='").append(action).append("&").append(param).append("=").append(i).append("'>").append(i).append("</a>,");
        	}        			
        }
        //Appends ellipsis.
		if(targetPage+offset<pageTotal-1){
			snippet.append("...,");
		}
		//Appends last page link.
		if(targetPage+offset<pageTotal){
			snippet.append("<a href='").append(action).append("&").append(param).append("=").append(pageTotal).append("'>").append(pageTotal).append("</a>,");
		}
		//Appends next link.
        if(pager.isHasNextPage()){
        	snippet.append("<a href='").append(action).append("&").append(param).append("=").append(targetPage+1).append("'>").append("Next").append("</a>,");
        }
        try {
            pageContext.getOut().write(snippet.toString());
        } catch (IOException io) {
            throw new JspException(io);
        }
        return super.doStartTag();
	}

	/*----------------------------------    Accessor Methods    ----------------------------------*/

	public void setAction(String action) {
		this.action = action;
	}

	public void setParam(String param) {
		this.param = param;
	}

}
