package jpa;

import java.util.Date;

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

	@Test
	public void get() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("JPAPU");
		EntityManager em = factory.createEntityManager();
		Subject sub = em.find(Subject.class, Long.valueOf(1));
		System.out.println(sub.getId() + ":" + sub.getCreateTime() + ":"
				+ sub.getUpdateTime());
		em.close();
		factory.close();
		System.out.println(sub.getId() + ":" + sub.getCreateTime() + ":"
				+ sub.getUpdateTime());
	}

	// 1.事务关联
	// 2.托管状态
	@Test
	public void update() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("JPAPU");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Subject sub = em.find(Subject.class, Long.valueOf(1));
		sub.setName(new Date().toString());
		em.getTransaction().commit();
		em.close();
		factory.close();
		/*
		 * System.out.println(sub.getId() + ":" + sub.getCreateTime() + ":" +
		 * sub.getUpdateTime());
		 */
	}

	@Test
	public void update2() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("JPAPU");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Subject sub = em.find(Subject.class, Long.valueOf(1));
		em.clear();
		sub.setName(new Date().toString());
		em.merge(sub);
		em.getTransaction().commit();
		em.close();
		factory.close();
		/*
		 * System.out.println(sub.getId() + ":" + sub.getCreateTime() + ":" +
		 * sub.getUpdateTime());
		 */
	}

	@Test
	public void getReference() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("JPAPU");
		EntityManager em = factory.createEntityManager();
		Subject sub = em.getReference(Subject.class, Long.valueOf(1));
		/*
		 * System.out.println(sub.getId() + ":" + sub.getCreateTime() + ":" +
		 * sub.getUpdateTime());
		 */
		em.close();
		factory.close();
		System.out.println(sub.getId() + ":" + sub.getCreateTime() + ":"
				+ sub.getUpdateTime());
	}
}
