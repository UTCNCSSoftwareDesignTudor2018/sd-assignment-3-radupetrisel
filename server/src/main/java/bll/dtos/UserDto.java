package bll.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import dal.entities.User;

public class UserDto {

	private String firstName;
	private String lastName;
	private String username;
	private List<ArticleDto> articles;

	public UserDto() {

	}

	public UserDto(User user) {

		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.articles = user.getArticles().stream().map(a -> new ArticleDto(a)).collect(Collectors.toList());

	}

	@JsonCreator
	public UserDto(@JsonProperty(value = "firstName") String firstName,
			@JsonProperty(value = "lastName") String lastName,
			@JsonProperty(value = "articles") List<ArticleDto> articles,
			@JsonProperty(value = "username") String username) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.articles = articles;
		this.setUsername(username);
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

	public List<ArticleDto> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleDto> articles) {
		this.articles = articles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
