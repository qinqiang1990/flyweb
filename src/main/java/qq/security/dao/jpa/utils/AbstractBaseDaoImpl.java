package qq.security.dao.jpa.utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import qq.security.dao.base.BaseDao;
import qq.security.dao.base.QueryResult;

@Transactional
public abstract class AbstractBaseDaoImpl<T> implements BaseDao<T> {

	protected static Logger logger = Logger
			.getLogger(AbstractBaseDaoImpl.class);
	// 注入实体管理器
	@PersistenceContext
	protected EntityManager em;
	//protected EntityManager em = JPAUtils.getEntityManager();

	@SuppressWarnings("unchecked")
	protected Class<T> entityClass = (Class<T>) EntityUtils.getEntityClass(this
			.getClass());

	protected String entityName = getEntityName();

	public T find(Long entityId) {
		return em.find(entityClass, entityId);
	}

	public void merge(T entity) {
		em.merge(entity);
	}

	public void persist(T entity) {
		em.persist(entity);
	}

	@SuppressWarnings("unchecked")
	public QueryResult<T> query(int fistResult, int maxResult, String whereSql,
			Object[] params, LinkedHashMap<String, String> orderBy) {
		StringBuilder sb = new StringBuilder("");
		// 拼凑where语句
		if (null != whereSql && !"".equals(whereSql.trim())) {
			sb.append(" where 1=1 and ").append(whereSql.trim()).append(" ");
		}
		// 拼凑orderBy语句
		if (null != orderBy && !orderBy.isEmpty()) {
			sb.append(" order by ");
			for (String key : orderBy.keySet()) {
				sb.append("o.").append(key).append(" ")
						.append(orderBy.get(key)).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}

		List<T> results = null;
		long count = 0L;

		String jpql = "select o from " + entityName + " o ";
		Query query = em.createQuery(jpql + sb.toString());
		logger.info(jpql + sb.toString());
		// 设置参数
		setParameters(query, params);
		if (fistResult > -1 && maxResult > -1)
			query.setFirstResult(fistResult).setMaxResults(maxResult);
		results = query.getResultList();
		query = em.createQuery("select count(o) from " + entityName + " o "
				+ sb.toString());
		setParameters(query, params);
		count = (Long) query.getSingleResult();
		return new QueryResult<T>(results, count);
	}

	public QueryResult<T> query(int fistResult, int maxResult,
			LinkedHashMap<String, String> orderBy) {
		return query(fistResult, maxResult, null, null, orderBy);
	}

	public QueryResult<T> query(int fistResult, int maxResult, String whereSql,
			Object[] params) {
		return query(fistResult, maxResult, null, null, null);
	}

	public QueryResult<T> query(int fistResult, int maxResult) {
		return query(fistResult, maxResult, null, null, null);
	}

	public QueryResult<T> query(String whereSql, Object[] params,
			LinkedHashMap<String, String> orderBy) {
		return query(-1, -1, whereSql, params, orderBy);
	}

	public QueryResult<T> query(String whereSql, Object[] params) {
		return query(-1, -1, whereSql, params, null);
	}

	public Object queryBy(String whereSql, Object[] params) {

		StringBuffer sb = new StringBuffer();
		// 拼凑where语句
		if (null != whereSql && !"".equals(whereSql.trim())) {
			sb.append(" where 1=1 and ").append(whereSql.trim()).append(" ");
		}
		String jpql = "select o from " + entityName + " o ";
		Query query = em.createQuery(jpql + sb.toString());
		logger.info(jpql + sb.toString());
		setParameters(query, params);
		Object result = null;
		try {
			result = query.getSingleResult();
		} catch (RuntimeException e) {
		}
		return result;
	}

	public Object queryForProperty(String property, Long entityId) {
		String jpql = "select o." + property + " from " + entityName
				+ " o where o." + getEntityId() + "=?1";
		System.out.println(jpql);
		Query query = em.createQuery(jpql);
		query.setParameter("1", entityId);
		Object result = null;
		try {
			result = query.getSingleResult();
		} catch (RuntimeException e) {
		}
		return result;
	}

	public void remove(Long... entityIds) {
		for (Long entityId : entityIds) {
			em.remove(em.getReference(entityClass, entityId));
		}
	}

	private void setParameters(Query query, Object[] params) {
		if (null != query && null != params && params.length > 0) {
			for (int i = 1; i <= params.length; i++) {
				query.setParameter(i, params[i - 1]);
			}
		}
	}

	private String getEntityId() {
		Field[] fields = entityClass.getDeclaredFields();
		Id id = null;
		String entityId = null;
		// 先看看字段有没有注解Id
		for (Field field : fields) {
			id = field.getAnnotation(Id.class);
			if (null != id) {
				entityId = field.getName();
				break;
			}
		}
		// 如果字段上没有注解，则在getter方法上面找.
		if (null == id) {
			try {
				PropertyDescriptor[] propertyDescriptors = Introspector
						.getBeanInfo(entityClass).getPropertyDescriptors();
				Method readMethod = null;
				for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
					readMethod = propertyDescriptor.getReadMethod();
					id = readMethod.getAnnotation(Id.class);
					if (null != id) {
						entityId = propertyDescriptor.getName();
						break;
					}
				}
			} catch (IntrospectionException e) {
				throw new RuntimeException(e);
			}
		}

		if (entityId == null)
			throw new RuntimeException("主键没有设置");

		return entityId;
	}

	private String getEntityName() {
		String entityName = entityClass.getSimpleName();
		Entity entity = entityClass.getAnnotation(Entity.class);
		String name = null;
		if (null != entity)
			name = entity.name();
		return null == name || "".equals(name.trim()) ? entityName : name
				.trim();
	}
}