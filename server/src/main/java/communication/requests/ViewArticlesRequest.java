package communication.requests;

import bll.ArticleBLL;
import communication.responses.Response;
import communication.responses.ViewArticlesResponse;

public class ViewArticlesRequest implements Request {

	@Override
	public Response execute() {

		return new ViewArticlesResponse(new ArticleBLL().findAll());

	}

}
