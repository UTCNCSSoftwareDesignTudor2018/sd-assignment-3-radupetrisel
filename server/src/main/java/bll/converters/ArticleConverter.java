package bll.converters;

import bll.dtos.Article;
import dal.entities.ArticleEntity;
import dal.repositories.UserRepository;

public class ArticleConverter {

	public static Article fromEntity(ArticleEntity ae) {
		
		Article article = new Article();
		article.setTitle(ae.getTitle());
		article.setArticleAbstract(ae.getArticleAbstract());
		article.setBody(ae.getBody());
		article.setAuthor(ae.getAuthor().getUsername());
		
		return article;
	}
	
	public static ArticleEntity toEntity(Article article) {
		
		ArticleEntity ae = new ArticleEntity();
		
		ae.setTitle(article.getTitle());
		ae.setArticleAbstract(article.getArticleAbstract());
		ae.setBody(article.getBody());
		ae.setAuthor(new UserRepository().findByUsername(article.getAuthor()));
		
		return ae;
	}
}
