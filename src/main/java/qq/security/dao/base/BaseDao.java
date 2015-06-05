package qq.security.dao.base;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface BaseDao<T> {

	public void persist(T entity);

	@Cacheable(value = "userCache", key = "#entity.id")
	public void merge(T entity);

	@CacheEvict(value = "userCache", key = "#entityIds")
	public void remove(Long... entityIds);

	@Cacheable(value = "userCache", key = "#entityId")
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

	@Cacheable(value = "userCache")
	public Object queryBy(String whereSql, Object[] params);

}