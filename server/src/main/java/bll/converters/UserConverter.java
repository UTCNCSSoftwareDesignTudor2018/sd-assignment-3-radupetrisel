package bll.converters;

import java.util.stream.Collectors;

import bll.dtos.User;
import dal.entities.UserEntity;
import dal.repositories.UserRepository;

public class UserConverter {

	public static User fromEntity(UserEntity ue) {

		User user = new User();

		user.setFirstName(ue.getFirstName());
		user.setLastName(ue.getLastName());
		user.setUsername(ue.getUsername());
		user.setArticles(
				ue.getArticles().stream().map(ae -> ArticleConverter.fromEntity(ae)).collect(Collectors.toList()));
		user.setPassword(ue.getPassword());

		return user;
	}
	
	public static UserEntity toEntity(User user) {
		
		UserEntity ue = new UserEntity();
		
		ue.setFirstName(user.getFirstName());
		ue.setLastName(user.getLastName());
		ue.setUsername(user.getUsername());
		ue.setPassword(user.getPassword());
		
		try {
			ue.setArticles(new UserRepository().findByUsername(user.getUsername()).getArticles());
		} catch (NullPointerException e) {
			ue.setArticles(null);
		}
		
		
		return ue;
	}
}
