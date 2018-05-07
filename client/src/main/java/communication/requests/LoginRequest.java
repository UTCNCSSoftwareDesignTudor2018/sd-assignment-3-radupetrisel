package communication.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest implements Request {

	private String username;
	private String password;

	@JsonCreator
	public LoginRequest(@JsonProperty(value = "username") String username, @JsonProperty(value = "password") String password) {
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
	
	public String toString() {
		return "login " + this.username + " " + this.password; 
	}

}
