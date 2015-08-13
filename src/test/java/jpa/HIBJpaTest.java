package jpa;

import java.net.URL;
import java.net.URLClassLoader;

import net.sf.ehcache.CacheManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import qq.security.dao.hib.MasterDao;
import qq.security.dao.hib.SubjectDao;
import qq.security.model.Master;
import qq.security.model.Subject;
import qq.security.service.HibService;

@PropertySource("classpath:application.properties")
@ContextConfiguration(locations = { "classpath:applicationHBContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class HIBJpaTest {

	@Autowired
	HibService serivce;
	@Autowired
	MasterDao mdao;

	@Test
	public void testSpringMVC() {

		Master master = new Master();

		Subject subject = new Subject();
		
		serivce.save(master, subject);

		/*
		 * User user = dao.findByAccountId(id).find(1L);
		 * 
		 * Assert.assertNotNull(user);
		 * 
		 * // verify(storageDelegate, times(1)).addMessage(any(Message.class));
		 * } verify(dao, times(1)).find(any(Long.class));
		 */
	}
}
