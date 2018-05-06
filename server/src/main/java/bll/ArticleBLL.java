package bll;

import java.util.List;
import java.util.stream.Collectors;

import bll.dtos.ArticleDto;
import dal.entities.Article;
import dal.entities.User;
import dal.repositories.ArticleRepository;

public class ArticleBLL {

	private ArticleRepository articleRepo;
	
	public ArticleBLL() {
		articleRepo = new ArticleRepository();
	}
	
	public List<ArticleDto> findAll(){
		
		List<ArticleDto> articles = articleRepo.findAll().stream().map(a -> new ArticleDto(a)).collect(Collectors.toList());
		
		return articles;
	}
	
	public void save(ArticleDto article, User author) {
		
		Article art = article.convert();
		art.setAuthor(author);
		
		articleRepo.save(art);		
	}
	
}
