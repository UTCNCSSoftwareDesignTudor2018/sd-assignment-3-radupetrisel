package bll;

import java.util.List;

import bll.dtos.ArticleDto;
import bll.dtos.UserDto;
import dal.entities.User;
import dal.repositories.ArticleRepository;
import dal.repositories.UserRepository;

public class UserBLL {

	private UserRepository userRepo;
	private ArticleRepository articleRepo;
	
	public UserBLL() {
		
	}
	
	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public ArticleRepository getArticleRepo() {
		return articleRepo;
	}

	public void setArticleRepo(ArticleRepository articleRepo) {
		this.articleRepo = articleRepo;
	}

	public UserBLL(UserRepository userRepo, ArticleRepository articleRepo) {
		this.userRepo = userRepo;
		this.articleRepo = articleRepo;
	}
	
	public int login(String username, String password) {
		
		User user = userRepo.findByUsername(username);
		
		if (user.getPassword() == password.hashCode())
			return user.getId();
		else return -1;
	}
	
	public List<ArticleDto> viewArticles(int id){
		UserDto user = new UserDto(userRepo.findById(id));
		
		return user.getArticles();
	}
	
	public void addArticle(int authorId, ArticleDto article) {
		
		User user = userRepo.findById(authorId);
		
		new ArticleBLL().save(article, user);
		
	}
}
