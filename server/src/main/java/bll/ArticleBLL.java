package bll;

import bll.dtos.ArticleDto;
import dal.entities.Article;
import dal.entities.User;
import dal.repositories.ArticleRepository;

public class ArticleBLL {

	private ArticleRepository articleRepo;
	
	public ArticleBLL() {
		articleRepo = new ArticleRepository();
	}
	
	public void save(ArticleDto article, User author) {
		
		Article art = article.convert();
		art.setAuthor(author);
		
		articleRepo.save(art);		
	}
	
}
