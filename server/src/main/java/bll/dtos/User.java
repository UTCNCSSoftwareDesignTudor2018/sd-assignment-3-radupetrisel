package bll.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import dal.entities.UserEntity;

public class User {

	private String firstName;
	private String lastName;
	private String username;
	private List<Article> articles;

	@JsonCreator
	public User(@JsonProperty(value = "firstName") String firstName, @JsonProperty(value = "lastName") String lastName,
			@JsonProperty(value = "username") String username,
			@JsonProperty(value = "articles") List<Article> articles) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.articles = articles;
	}

	public User(UserEntity ue) {

		this.firstName = ue.getFirstName();
		this.lastName = ue.getLastName();
		this.username = ue.getUsername();
		this.articles = ue.getArticles().stream().map(ae -> new Article(ae)).collect(Collectors.toList());

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public UserEntity convert() {
		
		UserEntity ue = new UserEntity();
		ue.setFirstName(this.firstName);
		ue.setLastName(this.lastName);
		ue.setUsername(this.username);
		
		return null;
	}

}
