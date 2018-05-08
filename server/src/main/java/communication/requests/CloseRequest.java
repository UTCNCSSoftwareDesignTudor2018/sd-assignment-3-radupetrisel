package communication.requests;

import communication.responses.CloseResponse;
import communication.responses.Response;

public class CloseRequest implements Request {

	@Override
	public Response execute() {
		return new CloseResponse();
	}

}
