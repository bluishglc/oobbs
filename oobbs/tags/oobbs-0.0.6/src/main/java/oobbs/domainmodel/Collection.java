package oobbs.domainmodel;

import java.util.List;

/**
 * The collection interface represents a set of objects, it's like the java.util.Collection, however, there no
 * real objects in this collection, it only looks like a collection, its method's implementation is database access
 * operation! see<code>oobbs.infrastructure.persistence.AbstractHibernateCollection</code>
 * NOTE: Domain Collection is READ-ONLY, All persist operations should executed by domain event!
 * 
 * @author laurence.geng
 */
public interface Collection<Owner,Element> {
		
	void setOwner(Owner owner);
	
	/**
	 * Sets the owner alias. The owner alias is used as alias when querying.
	 *
	 * @param ownerName the new owner alias
	 */
	void setOwnerAlias(String ownerName);

	boolean contains(Element e);

	boolean isEmpty();

	int size();
	
	/**
	 * The most important method. It returns a subset of the whole collection.
	 * the returned subset is fetched from database by sql, hql or other data access way,
	 * The Collection itself never load all elements once time!
	 */
	List<Element> toList(int startIndex, int offset);
	
}
