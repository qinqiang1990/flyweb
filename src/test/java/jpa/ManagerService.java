package jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Subject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ManagerService {
 

	public void save(Subject sub,EntityManager em) { 
		em.getTransaction().begin();
		em.persist(sub);
		em.getTransaction().commit();
	}

}
