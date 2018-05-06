package dal.repositories;

import static dal.utils.Hibernate.openSession;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dal.entities.Article;
import dal.entities.Article_;
import dal.entities.User;

public class ArticleRepository {
	
	public void save(Article article) {

		Session session = openSession();
		
		Transaction tx = session.beginTransaction();
		session.save(article);
		tx.commit();
		session.close();
	}
	
	public List<Article> findByTitle(String title){
		
		Session session = openSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<Article> query = builder.createQuery(Article.class);
		Root<Article> root = query.from(Article.class);
		
		query.select(root).where(builder.equal(root.get(Article_.title), title));
		return session.createQuery(query).getResultList();				
	}
	
	public List<Article> findByAuthor(User author){
		
		Session session = openSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<Article> query = builder.createQuery(Article.class);
		Root<Article> root = query.from(Article.class);
		
		query.select(root).where(builder.equal(root.get(Article_.author), author));
		
		return session.createQuery(query).getResultList();	
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

	public List<Article> findAll() {
		
		Session session = openSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<Article> query = builder.createQuery(Article.class);
		Root<Article> root = query.from(Article.class);
		
		List<Article> articles = session.createQuery(query.select(root)).getResultList();
		session.close();
		
		return articles;
	}

}
