package com.qq.utils;
 
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public final class SpringUtil {

    private static ApplicationContext  ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    
    public static Object getBean(String beanName){
         return ctx.getBean(beanName);
    }    
}



/*private JdbcTemplate jdbcT = (JdbcTemplate) SpringUtil
        .getBean("jdbcTemplate");*/