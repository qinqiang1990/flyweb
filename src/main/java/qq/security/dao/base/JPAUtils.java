package qq.security.dao.base;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JPAUtils {
	private static EntityManagerFactory entityManagerFactory;
	private static ThreadLocal<EntityManager> ems = new ThreadLocal<EntityManager>();

	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("JPAPU");
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public static EntityManager getEntityManager() {
		EntityManager em = ems.get();
		if (null == em) {
			em = entityManagerFactory.createEntityManager();
			ems.set(em);
		}
		return em;
	}

	public static void freeEntityManager() {
		EntityManager em = ems.get();
		if (null != em) {
			ems.remove();
			em.close();
		}
	}
}