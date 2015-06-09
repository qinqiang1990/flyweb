package qq.security.dao.sdjpa.v3.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
 
import qq.security.dao.sdjpa.v3.ControllerDaov3Plus;

@Repository
public class ControllerDaov3Impl implements ControllerDaov3Plus {

	@PersistenceContext
	EntityManager em;
	/*
	 * @Override public List<Comment> findListByHql(String hql) { Query query =
	 * em.createQuery(hql, Comment.class); return query.getResultList(); }
	 * 
	 * @Override public List<Comment> findPageByHql(String hql, int pageSize,
	 * int firstResult) { Query query = em.createQuery(hql, Comment.class);
	 * query.setFirstResult(firstResult); query.setMaxResults(pageSize); return
	 * query.getResultList(); }
	 */

}
