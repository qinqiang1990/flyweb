package jpa.otm;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Subject;

import org.junit.Test;

import entity.otm.InventoryForm;
import entity.otm.InventoryRecord;

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

}
