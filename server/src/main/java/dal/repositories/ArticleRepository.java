package dal.repositories;

import static dal.utils.Hibernate.openSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dal.entities.Article;

public class ArticleRepository {

	public void save(Article article) {

		Session session = openSession();
		
		Transaction tx = session.beginTransaction();
		session.save(article);
		tx.commit();
		session.close();
	}
	
	public Article findById(int id) {
		
		Session session = openSession();
		Article article = session.get(Article.class, id);
		session.close();
		
		return article;
	}
	
	public Article getOne(int id) {
		
		Session session = openSession();
		Article article = session.load(Article.class, id);
		
		return article;		
	}
	
	public void delete(Article article) {
		
		Session session = openSession();
		Transaction tx = session.beginTransaction();
		session.delete(article);
		tx.commit();
		session.close();
	}
	

}
