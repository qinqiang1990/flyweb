package qq.security.dao.jpa;

import java.util.LinkedHashMap;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import qq.security.dao.base.QueryResult;
import qq.security.dao.jpa.utils.AbstractBaseDaoImpl;
import qq.security.model.User;

@Repository
public class UserDaoImpl extends AbstractBaseDaoImpl<User> implements UserDao {

	@Cacheable(value = "userCache", key = "#entityId")
	@Override
	public User find(Long entityId) {
		// TODO Auto-generated method stub
		return super.find(entityId);
	}

	@Cacheable(value = "userCache", key = "#entity.id")
	@Override
	public void merge(User entity) {
		// TODO Auto-generated method stub
		super.merge(entity);
	}

	@Cacheable(value = "userCache", key = "#entityId")
	@Override
	public void persist(User entity) {
		// TODO Auto-generated method stub
		super.persist(entity);
	}

	@Override
	public QueryResult<User> query(int fistResult, int maxResult,
			String whereSql, Object[] params,
			LinkedHashMap<String, String> orderBy) {
		// TODO Auto-generated method stub
		return super.query(fistResult, maxResult, whereSql, params, orderBy);
	}

	@Override
	public QueryResult<User> query(int fistResult, int maxResult,
			LinkedHashMap<String, String> orderBy) {
		// TODO Auto-generated method stub
		return super.query(fistResult, maxResult, orderBy);
	}

	@Override
	public QueryResult<User> query(int fistResult, int maxResult,
			String whereSql, Object[] params) {
		// TODO Auto-generated method stub
		return super.query(fistResult, maxResult, whereSql, params);
	}

	@Override
	public QueryResult<User> query(int fistResult, int maxResult) {
		// TODO Auto-generated method stub
		return super.query(fistResult, maxResult);
	}

	@Override
	public QueryResult<User> query(String whereSql, Object[] params,
			LinkedHashMap<String, String> orderBy) {
		// TODO Auto-generated method stub
		return super.query(whereSql, params, orderBy);
	}

	@Override
	public QueryResult<User> query(String whereSql, Object[] params) {
		// TODO Auto-generated method stub
		return super.query(whereSql, params);
	}

	@Cacheable(value = "userCache")
	@Override
	public Object queryBy(String whereSql, Object[] params) {
		// TODO Auto-generated method stub
		return super.queryBy(whereSql, params);
	}

	@Override
	public Object queryForProperty(String property, Long entityId) {
		// TODO Auto-generated method stub
		return super.queryForProperty(property, entityId);
	}

	@CacheEvict(value = "userCache", key = "#entityIds")
	@Override
	public void remove(Long... entityIds) {
		// TODO Auto-generated method stub
		super.remove(entityIds);
	}

}