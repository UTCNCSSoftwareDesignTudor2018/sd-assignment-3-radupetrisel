package bll;

import java.util.List;
import java.util.stream.Collectors;

import bll.converters.ArticleConverter;
import bll.dtos.Article;
import dal.entities.ArticleEntity;
import dal.entities.UserEntity;
import dal.repositories.ArticleRepository;

public class ArticleBLL {

	private ArticleRepository articleRepo;
	
	public ArticleBLL() {
		articleRepo = new ArticleRepository();
	}
	
	public List<Article> findAll(){
		
		List<Article> articles = articleRepo.findAll().stream().map(ae -> ArticleConverter.fromEntity(ae)).collect(Collectors.toList());
		
		return articles;
	}
	
	public void save(Article article, UserEntity author) {
		
		ArticleEntity art = ArticleConverter.toEntity(article);
		art.setAuthor(author);
		
		articleRepo.save(art);		
	}
	
}
