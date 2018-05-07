package communication.requests;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import communication.responses.Response;

@JsonTypeInfo(use = Id.MINIMAL_CLASS)
public interface Request {
	
	public Response execute();
}
