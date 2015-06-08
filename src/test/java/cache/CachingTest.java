package cache;

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

import qq.security.dao.jpa.UserDao;
import qq.security.model.User;
import static org.mockito.Mockito.*;

/*
 @ComponentScan(basePackages = "spring.mvc")

 @Autowired
 protected ApplicationContext ctx;

 @Autowired
 EhCacheCacheManager cacheManager;

 ApplicationContext act = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });

 UserDao userDao = (UserDao) act.getBean("userDaoImpl");
 */
@Transactional
@PropertySource("classpath:application.properties")
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class CachingTest {

	@Autowired
	UserDao userDao;
/*
	@Autowired
	CacheManager cacheManager;

	@Before
	public void before() {
		cacheManager.clearAll();
	}
	*/

	@Test
	public void testSpringMVC() {

		User user = userDao.find(1L);

		Assert.assertNotNull(user);

		// verify(storageDelegate, times(1)).addMessage(any(Message.class)); }
		verify(userDao, times(1)).find(any(Long.class));
	}

	@Test
	public void testURLClassLoader() {

		URLClassLoader classLoader = (URLClassLoader) CachingTest.class
				.getClassLoader();
		URL[] urls = classLoader.getURLs();
		for (int i = 0; i < urls.length; i++) {
			System.out.println(urls[i].toString());
		}
	}

	@Test
	public void testCaching() {

		userDao.find(1L);
		//System.out.println(cacheManager.getCache("userCache").get("test"));

	}
}
