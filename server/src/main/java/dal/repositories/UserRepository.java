package dal.repositories;

import static dal.utils.Hibernate.*;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dal.entities.User;

public class UserRepository {

	public void save(User user) {

		Session session = openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		session.close();
	}

	public User findById(int id) {

		Session session = openSession();
		User user = session.get(User.class, id);
		session.close();

		return user;
	}
	
	public User getOne(int id) throws ObjectNotFoundException{
		
		Session session = openSession();
		User user = session.load(User.class, id);
		
		return user;
	}
	
	public void delete(User user) {
		
		Session session = openSession();
		Transaction tx = session.beginTransaction();
		session.delete(user);
		tx.commit();
		session.close();
	}
}
