package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Subject;
import org.junit.Test;

public class JPATest {

	@Test
	public void save() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("JPAPU");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Subject sub = new Subject();
		sub.setName("subject");
		em.persist(sub);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

}
