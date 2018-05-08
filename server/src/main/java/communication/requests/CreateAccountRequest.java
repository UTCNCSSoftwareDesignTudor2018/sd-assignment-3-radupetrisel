package communication.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import bll.UserBLL;
import bll.dtos.User;
import communication.responses.CreateAccountResponse;
import communication.responses.Response;

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

	@Override
	public Response execute() {

		String message = new UserBLL().createUser(user);

		return new CreateAccountResponse(message);
	}

}
