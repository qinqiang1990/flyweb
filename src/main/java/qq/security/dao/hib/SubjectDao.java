package qq.security.dao.hib;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import qq.security.model.Subject;

@Repository
public class SubjectDao extends SuperDao {

	private static final Logger log = Logger.getLogger(SubjectDao.class);

	@Transactional
	public void save(Subject subject) {
		log.info("saving subject instance");
		try {
			getSession().save(subject);
			log.info("save successful");
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}
	}

	public void save2(Subject subject) {
		log.info("saving subject instance");
		try {
			Session session = getSession();
			Transaction t=session.beginTransaction();
			session.save(subject);
			t.commit();

			log.info("save successful");
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}

	}
}