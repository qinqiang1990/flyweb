package jpa.otm;

import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Subject;

import org.junit.Test;

import entity.otm.positive.*;
import entity.otm.two.*;

/*
 * http://blog.csdn.net/gebitan505/article/details/22619175
 */
public class JPATest {

	@Test
	public void save() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("JPAPU");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		InventoryForm form = new InventoryForm();
		form.setNumber("InventoryForm");

		InventoryRecord record1 = new InventoryRecord();
		record1.setNote("note1");

		InventoryRecord record2 = new InventoryRecord();
		record2.setNote("note2");

		form.addRecords(record1);
		form.addRecords(record2);

		em.persist(form);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	@Test
	public void positive_save() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("JPAPU");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Form form = new Form();
		form.setNumber("PInventoryForm");

		Record record1 = new Record();
		record1.setNote("Pnote1");
		Record record2 = new Record();
		record2.setNote("Pnote2");

		form.getRecords().add(record1);
		form.getRecords().add(record2);

		em.persist(form);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	@Test
	public void positive_find_One() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("JPAPU");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Form form = em.find(Form.class, Long.valueOf(1));
		System.out.println(form.getNumber());
		System.out.println(form.getRecords().size());
		Iterator it = form.getRecords().iterator();
		while (it.hasNext()) {
			Record temp = (Record) it.next();
			System.out.println(temp.getNote());
		}

		em.close();
		factory.close();
	}

	@Test
	public void positive_find_Many() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("JPAPU");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Record record = em.find(Record.class, Long.valueOf(1));
		System.out.println(record.getNote());
		System.out.println(record.getForm().getNumber());

		em.close();
		factory.close();
	}

}
