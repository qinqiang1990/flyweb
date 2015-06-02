package qq.security.dao.base;

import java.io.Serializable;
import java.util.LinkedHashMap;

public interface BaseDao<T> {

	public void persist(T entity);

	public void merge(T entity);

	public void remove(Long... entityIds);

	public T find(Long entityId);

	public QueryResult<T> query(int fistResult, int maxResult, String whereSql,
			Object[] params, LinkedHashMap<String, String> orderBy);

	public QueryResult<T> query(int fistResult, int maxResult,
			LinkedHashMap<String, String> orderBy);

	public QueryResult<T> query(int fistResult, int maxResult, String whereSql,
			Object[] params);

	public QueryResult<T> query(int fistResult, int maxResult);

	public QueryResult<T> query(String whereSql, Object[] params,
			LinkedHashMap<String, String> orderBy);

	public QueryResult<T> query(String whereSql, Object[] params);

	public Object queryForProperty(String property, Long entityId);

}