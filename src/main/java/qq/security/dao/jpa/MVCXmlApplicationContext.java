package qq.security.dao.jpa;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

public class MVCXmlApplicationContext {

	public static Logger logger = Logger
			.getLogger(MVCXmlApplicationContext.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext act = new ClassPathXmlApplicationContext(
				"/applicationContext.xml");

		UserDaoImpl userDaoImp = (UserDaoImpl) act.getBean("userDaoImpl");

	}
}