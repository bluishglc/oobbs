package oobbs.domainmodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DomainAction implements Serializable{

	private static final long serialVersionUID = 1L;
	
    public static final String COUNT_FORUM_POSTS = "COUNT_FORUM_POSTS";
    public static final String GET_FORUM_LATEST_POST = "GET_FORUM_LATEST_POST";
	
	private String name;
	
	private Map<String,Object> properties = new HashMap<String,Object>();
	
	private boolean performed = false;
	
	private Object performResult;
	
	public DomainAction() {}

	public DomainAction(String name) {
		this.name = name;
	}
	
	public void setProperty(String name,Object value){
		properties.put(name, value);
	}
	
	public Object getProperty(String name){
		return properties.get(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPerformed() {
		return performed;
	}

	public void setPerformed(boolean performed) {
		this.performed = performed;
	}

	public Object getPerformResult() {
		if(performed == false){
			throw new RuntimeException("This task has not been performed!");
		}
		return performResult;
	}

	public void setPerformResult(Object performResult) {
		this.performResult = performResult;
	}
	
}
