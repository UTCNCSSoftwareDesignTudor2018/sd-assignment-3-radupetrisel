package communication.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import bll.UserBLL;
import communication.responses.LoginResponse;
import communication.responses.Response;

public class LoginRequest implements Request {

	private String username;
	private String password;

	@JsonCreator
	public LoginRequest(@JsonProperty(value = "username") String username,
			@JsonProperty(value = "password") String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Response execute() {
		
		int resp = new UserBLL().login(username, password);

		switch (resp) {

		case -1:
			return new LoginResponse("Invalid password");
		case -2:
			return new LoginResponse("Invalid username");
		default:
			return new LoginResponse("Success");
		}
	}
	
	public String toString() {
		return "login " + this.username + " " + this.password; 
	}

}
