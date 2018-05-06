package dal.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Hibernate {
	
	private static SessionFactory factory;
	private static Session session;
	private static Object mutex = new Object();
	
	public static SessionFactory getInstance() {
		
		SessionFactory fact = factory;
		if (fact == null)
			synchronized(mutex) {
				fact = factory;
				if (fact == null)
					fact = factory = new Configuration().configure().buildSessionFactory();
			}
		
		return factory;
	}
	
	public static void close() {
		factory.close();
	}
}
