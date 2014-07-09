package oobbs.domainmodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SupportRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
    public static final String COUNT_FORUM_POSTS = "COUNT_FORUM_POSTS";
    public static final String GET_FORUM_LATEST_POST = "GET_FORUM_LATEST_POST";
	
	private String name;
	
	private Map<String,Object> params = new HashMap<String,Object>();
	
	public SupportRequest() {}

	public SupportRequest(String name) {
		this.name = name;
	}
	
	public SupportRequest setParam(String name,Object value){
		params.put(name, value);
		return this;
	}
	
	public Object getParam(String name){
		return params.get(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
