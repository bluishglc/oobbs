package oobbs.domainmodel;

import java.io.Serializable;

public class SupportResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Object result;

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
}
