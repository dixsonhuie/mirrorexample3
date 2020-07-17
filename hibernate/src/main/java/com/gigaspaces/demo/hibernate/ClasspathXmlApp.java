package com.gigaspaces.demo.hibernate;

import com.gigaspaces.demo.common.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

public class ClasspathXmlApp {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");


        SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Person p = new Person();
        p.setId(1);
        p.setAge(21);
        p.setFirstName("My");
        p.setLastName("Last");

        session.save(p);
        session.getTransaction().commit();

        System.out.println("About to exit...");
        System.exit(0);
    }

}
