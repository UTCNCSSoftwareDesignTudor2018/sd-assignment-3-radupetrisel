package communication.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse implements Response {

	private String message;

	@JsonCreator
	public LoginResponse(@JsonProperty(value = "message") String message) {
		
		this.message = message;
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
