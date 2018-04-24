package communication.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	private String firstName;
	private String lastName;
	private String username;
	private List<Article> articles;

	@JsonCreator
	public User(@JsonProperty(value = "firstName") String firstName, @JsonProperty(value = "lastName") String lastName,
			@JsonProperty(value = "username") String username, @JsonProperty(value = "articles") List<Article> articles) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.articles = articles;
	}

}
