package bll.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	private String firstName;
	private String lastName;
	private String username;
	private List<Article> articles;
	
	public User() {
		
	}
	
	@JsonCreator
	public User(@JsonProperty(value = "firstName") String firstName, @JsonProperty(value = "lastName") String lastName,
			@JsonProperty(value = "username") String username, @JsonProperty(value = "articles") List<Article> articles) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.articles = articles;
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


}
