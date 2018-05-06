package dal.repositories;

import static dal.utils.Hibernate.getInstance;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dal.entities.User;
import dal.entities.User_;

public class UserRepository {

	public void save(User user) {

		Session session = getInstance().openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		session.close();
	}
	
	public User findByUsername(String username) {
		
		Session session = getInstance().openSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		
		query.select(root).where(builder.equal(root.get(User_.username), username));
		
		User user = session.createQuery(query).getSingleResult();
		
		session.close();
		return user;
	}

	public User findById(int id) {

		Session session = getInstance().openSession();
		User user = session.get(User.class, id);
		session.close();

		return user;
	}
	
	public void delete(User user) {
		
		Session session = getInstance().openSession();
		Transaction tx = session.beginTransaction();
		session.delete(user);
		tx.commit();
		session.close();
	}
}
