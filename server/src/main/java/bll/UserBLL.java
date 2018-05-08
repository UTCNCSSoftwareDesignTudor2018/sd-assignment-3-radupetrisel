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

	public String createUser(User user) {
		return userRepo.save(UserConverter.toEntity(user));
	}

	public int login(String username, String password) {

		UserEntity user = userRepo.findByUsername(username);
		
		if (user == null)
			return -2;
		else if (user.getPassword() == password.hashCode())
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
