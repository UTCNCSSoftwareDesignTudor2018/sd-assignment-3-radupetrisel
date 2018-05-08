package communication.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import communication.entities.User;

public class CreateAccountRequest implements Request {

	private User user;

	@JsonCreator
	public CreateAccountRequest(@JsonProperty(value = "user")User user) {
		this.setUser(user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
