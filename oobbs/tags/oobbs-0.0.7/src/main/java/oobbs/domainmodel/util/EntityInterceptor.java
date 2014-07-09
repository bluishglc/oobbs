package oobbs.domainmodel.util;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

/**
 * The EntityInterceptor intercept save and update operation, set
 * creation time and modified time for <code>TimeAutoStamped</code> entities.
 *
 * NOTE: Below setting does NOT work for mysql!
 * 
 * @Temporal(TemporalType.TIMESTAMP)
 * @Column(nullable=false,insertable=false,updatable=false)
 * @org.hibernate.annotations.Generated(org.hibernate.annotations.GenerationTime.INSERT) 
 * private Date creationTime;
 * 
 * @Temporal(TemporalType.TIMESTAMP)
 * @Column(nullable=false,insertable=false,updatable=false)
 * @org.hibernate.annotations.Generated(org.hibernate.annotations.GenerationTime.ALWAYS) 
 * private Date modifiedTime;
 */

public class EntityInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = -980149122081810855L;

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		boolean changed = false;
		if (entity instanceof TimeAutoStamped) {
			Date creationDate = new Date();
			for (int i = 0; i < propertyNames.length; i++) {
				if ("creationTime".equals(propertyNames[i])||"modifiedTime".equals(propertyNames[i])) {
					state[i] = creationDate;
					changed = true;
				}
			}
		}
		return changed;
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		if (entity instanceof TimeAutoStamped) {
			Date modifiedDate = new Date();
			for (int i = 0; i < propertyNames.length; i++) {
				if ("modifiedTime".equals(propertyNames[i])) {
					currentState[i] = modifiedDate;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		// TODO Auto-generated method stub
		return super.onLoad(entity, id, state, propertyNames, types);
	}	
	
	
}
