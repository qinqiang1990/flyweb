package qq.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import qq.security.dao.jpa.UserDao;
import qq.security.model.User;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	@Cacheable(value = "UserCache", key = "#entityId")
	public User find(Long entityId) {
		return userDao.find(entityId);
	}

	@CacheEvict(value = "UserCache", key = "#entityIds")
	public void remove(Long... entityIds) {
		userDao.remove(entityIds);
	}

}