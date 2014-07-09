package oobbs.domainmodel;

import java.io.Serializable;
import java.util.List;


/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 *
 * <p>Extend this interface if you want typesafe (no casting necessary) DAO's for your
 * domain objects.
 *
 * @author Laurence Geng
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public interface Repository <T, PK extends Serializable> {

    T get(PK id);
    
    T load(PK id);

    List<T> loadAll();
   
    boolean contains(PK id);
   
    boolean contains(T entity);
    
    /*
     * NOTE: We use persist/merge not save/update/saveOrUpdate, It's because we have mapped
     * cascading options for persist/merge.
     */
    
	/**
	 * NOTE: persist method doesn't guarantee that the identifier value will be assigned to
	 * the persistent instance immediately, the assignment might happen at flush.
	 * time.
	 */
    void persist(T entity);
    
    T merge(T entity);
    
    void delete(PK id);
    
    void delete(T entity);
    
    void refresh(T entity);
    
    void flush();
    
    //T merge(T entity);
    
    //void persist(T entity);

}