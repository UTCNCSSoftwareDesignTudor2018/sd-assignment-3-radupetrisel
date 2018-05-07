package dal.repositories;

import static dal.utils.Hibernate.getInstance;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dal.entities.UserEntity;
import dal.entities.UserEntity_;

public class UserRepository {

	public void save(UserEntity user) {

		Session session = getInstance().openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		session.close();
	}
	
	public UserEntity findByUsername(String username) {
		
		Session session = getInstance().openSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<UserEntity> query = builder.createQuery(UserEntity.class);
		Root<UserEntity> root = query.from(UserEntity.class);
		
		query.select(root);
		query.where(builder.equal(root.get(UserEntity_.username), username));
		
		UserEntity user = session.createQuery(query).getSingleResult();
		
		session.close();
		return user;
	}

	public UserEntity findById(int id) {

		Session session = getInstance().openSession();
		UserEntity user = session.get(UserEntity.class, id);
		session.close();

		return user;
	}
	
	public void delete(UserEntity user) {
		
		Session session = getInstance().openSession();
		Transaction tx = session.beginTransaction();
		session.delete(user);
		tx.commit();
		session.close();
	}
}
