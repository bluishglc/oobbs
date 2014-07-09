package oobbs.domainmodel;

import java.util.ArrayList;
import java.util.List;

public class ResultCollector <T> {
	
	private List<Object> reulsts = new ArrayList<Object>();
	
	public void add(Object result){
		reulsts.add(result);
	}
	
	public Object getLatest(){
		return reulsts.get(reulsts.size());
	}
	
	public T getUniqueResult(){
		if(reulsts.size()!=1){
			throw new RuntimeException("It's not unique result! There's no result or more than one reulst.");
		}
		return (T) reulsts.get(0);
	}
	
}
