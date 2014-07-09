package oobbs.domainmodel;

public interface DomainRequestProcessor {
	
	public DomainResponse process(DomainAction request);

}
