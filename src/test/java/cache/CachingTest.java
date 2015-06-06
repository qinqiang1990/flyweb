package cache;

import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import qq.security.dao.jpa.UserDao;
import qq.security.dao.jpa.UserDaoImpl;
import static org.mockito.Mockito.*;

/*@Configuration
 @ComponentScan(basePackages = "spring.mvc")
 @PropertySource("classpath:application.properties")*/

//@RunWith(SpringJUnit4ClassRunner.class)
/* @ContextConfiguration(locations = { "applicationContext.xml" }) */
// @ContextConfiguration(locations = "classpath*:/applicationContext.xml")
public class CachingTest {
	/*
	 * @Autowired protected ApplicationContext ctx;
	 */
	/*
	 * @Autowired EhCacheCacheManager cacheManager;
	 */

	/*
	 * @Autowired
	 */

	@Test
	public void testCaching_MessagesCache() {
		ApplicationContext act = new ClassPathXmlApplicationContext(
				"/applicationContext.xml");

		UserDao userDao = (UserDao) act.getBean("uerDao");
		userDao.find(1L);

		// verify(userDao).find(any(Long.class));
	}

	@Test
	public void testURLClassLoader() {
		// TODO Auto-generated method stub
		URLClassLoader classLoader = (URLClassLoader) CachingTest.class
				.getClassLoader();
		URL[] urls = classLoader.getURLs();
		for (int i = 0; i < urls.length; i++) {
			System.out.println(urls[i].toString());
		}
	}

	/*
	 * @Test public void testCaching_MessagesCacheRemove() {
	 * controller.getAllMessages(); controller.getAllMessages();
	 * controller.addMessage(new Message()); controller.getAllMessages();
	 * verify(storageDelegate, times(2)).findAllMessages();
	 * verify(storageDelegate, times(1)).addMessage(any(Message.class)); }
	 */

}
