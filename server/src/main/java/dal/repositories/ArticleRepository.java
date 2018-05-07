package dal.repositories;

import static dal.utils.Hibernate.getInstance;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dal.entities.ArticleEntity;
import dal.entities.ArticleEntity_;
import dal.entities.UserEntity;

public class ArticleRepository {
	
	public void save(ArticleEntity article) {

		Session session = getInstance().openSession();
		
		Transaction tx = session.beginTransaction();
		session.save(article);
		tx.commit();
		session.close();
	}
	
	public List<ArticleEntity> findByTitle(String title){
		
		Session session = getInstance().openSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<ArticleEntity> query = builder.createQuery(ArticleEntity.class);
		Root<ArticleEntity> root = query.from(ArticleEntity.class);
		
		query.select(root).where(builder.equal(root.get(ArticleEntity_.title), title));
		return session.createQuery(query).getResultList();				
	}
	
	public List<ArticleEntity> findByAuthor(UserEntity author){
		
		Session session = getInstance().openSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<ArticleEntity> query = builder.createQuery(ArticleEntity.class);
		Root<ArticleEntity> root = query.from(ArticleEntity.class);
		
		query.select(root).where(builder.equal(root.get(ArticleEntity_.author), author));
		
		return session.createQuery(query).getResultList();	
	}
	
	public ArticleEntity findById(int id) {
		
		Session session = getInstance().openSession();
		ArticleEntity article = session.get(ArticleEntity.class, id);
		session.close();
		
		return article;
	}
	
	public void delete(ArticleEntity article) {
		
		Session session = getInstance().openSession();
		Transaction tx = session.beginTransaction();
		session.delete(article);
		tx.commit();
		session.close();
	}

	public List<ArticleEntity> findAll() {
		
		Session session = getInstance().openSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<ArticleEntity> query = builder.createQuery(ArticleEntity.class);
		Root<ArticleEntity> root = query.from(ArticleEntity.class);
		
		List<ArticleEntity> articles = session.createQuery(query.select(root)).getResultList();
		session.close();
		
		return articles;
	}

}
