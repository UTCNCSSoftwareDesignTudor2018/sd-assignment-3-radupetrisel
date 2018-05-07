package bll;

import java.util.List;

import bll.converters.UserConverter;
import bll.dtos.Article;
import bll.dtos.User;
import dal.entities.UserEntity;
import dal.repositories.UserRepository;

public class UserBLL {

	private UserRepository userRepo;

	public UserBLL() {
		userRepo = new UserRepository();
	}

	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public int login(String username, String password) {
		
		UserEntity user = userRepo.findByUsername(username);
		
		if (user.getPassword() == password.hashCode())
			return user.getId();
		else
			return -1;
	}

	public List<Article> viewArticles(int id) {
		
		User user = UserConverter.fromEntity(userRepo.findById(id));

		return user.getArticles();
	}

	public void addArticle(Article article) {

		UserEntity user = userRepo.findByUsername(article.getAuthor());

		new ArticleBLL().save(article, user);
	}
}
