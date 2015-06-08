package qq.security.dao.jpa;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import com.googlecode.ehcache.annotations.TriggersRemove;
import qq.security.dao.jpa.utils.AbstractBaseDaoImpl;
import qq.security.model.Controller;

@Repository
public class ControllerDaoImpl extends AbstractBaseDaoImpl<Controller>
		implements ControllerDao {

	@PostConstruct
	public void init() {
	}

	@Override
	public Controller find(Long entityId) {
		// TODO Auto-generated method stub
		return super.find(entityId);
	}

	@TriggersRemove(cacheName = "userCache", removeAll = true)
	@Override
	public void persist(Controller entity) {
		em.persist(entity);
	}

}