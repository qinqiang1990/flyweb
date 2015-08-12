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

import qq.security.dao.sdjpa.v1.ControllerDaov1;
import qq.security.model.User;
import static org.mockito.Mockito.*;

@Transactional
@PropertySource("classpath:application.properties")
@ContextConfiguration(locations = { "classpath:applicationHBContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class HIBJpaTest {

	@Autowired
	ControllerDaov1 dao;

	@Autowired
	CacheManager cacheManager;

	@Before
	public void before() {
		cacheManager.clearAll();
	}

	@Test
	public void testSpringMVC() {

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
