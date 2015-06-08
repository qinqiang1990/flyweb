package qq.security.dao.jpa;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

import qq.security.dao.jpa.utils.AbstractBaseDaoImpl;
import qq.security.model.Controller;

@Repository
public class ControllerDaoImpl extends AbstractBaseDaoImpl<Controller>
		implements ControllerDao {

	@Override
	public Controller find(Long entityId) {
		// TODO Auto-generated method stub
		return super.find(entityId);
	}

	@CacheEvict(value = "UserCache", allEntries = true)
	@Override
	public void persist(Controller entity) {
		em.persist(entity);
	}

}