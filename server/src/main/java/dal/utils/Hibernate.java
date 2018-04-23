package dal.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Hibernate {

	private static SessionFactory factory;
	private static Session session;
	
	private static SessionFactory getInstance() {
		
		if (factory == null)
			factory = new Configuration().configure().buildSessionFactory();
		
		return factory;
	}
	
	public static Session openSession() {
		
		if (session == null)
			session = getInstance().openSession();
		
		return session;
	}
	
	
	public static void close() {
		factory.close();
	}
}
