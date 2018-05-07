package communication.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import bll.UserBLL;
import bll.dtos.Article;
import communication.responses.Response;

public class AddArticleRequest implements Request {

	private Article article;

	@JsonCreator
	public AddArticleRequest(@JsonProperty(value = "article") Article article) {
		this.article = article;
	}

	@Override
	public Response execute() {

		new UserBLL().addArticle(article);

		return new AddArticleResponse();
	}

}
