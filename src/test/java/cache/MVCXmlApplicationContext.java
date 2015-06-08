package cache;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import qq.security.dao.jpa.UserDao;
import qq.security.dao.jpa.UserDaoImpl;

public class MVCXmlApplicationContext {

	public static Logger logger = Logger
			.getLogger(MVCXmlApplicationContext.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext act = new ClassPathXmlApplicationContext(
				"/applicationContext.xml");

		UserDao userDao = (UserDao) act.getBean("userDaoImpl");
		
		System.out.print(userDao.find(1L).getLogin());
	}
}