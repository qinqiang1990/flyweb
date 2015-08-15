package qq.security.dao.hib;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import qq.security.model.Master;
import qq.security.model.Subject;

@Repository
public class MasterDao extends SuperDao {

	private static final Logger log = Logger.getLogger(MasterDao.class);

	@Transactional(propagation=Propagation.REQUIRED)
	public void save(Master master) {
		log.info("saving master instance");
		try {
			getSession().save(master);
			log.info("save successful");
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}
	}

	public void save1(Master master) {
		log.info("saving master instance");
		try {
			getSession().save(master);
			log.info("save successful");
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}
	}

	public void save2(Master master) {
		log.info("saving master instance");
		try {
			Session session = getSession();
			Transaction t=session.beginTransaction();
			session.save(master);
			t.commit();

			log.info("save successful");
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}
	}
}