package server;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Server {

	public static void main(String[] args) {
		
		System.out.println("asd");
		
		Configuration config = new Configuration();
		SessionFactory factory = config.configure().buildSessionFactory();		
	}

}
