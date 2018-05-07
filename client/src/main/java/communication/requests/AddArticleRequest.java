package communication.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import communication.entities.Article;

public class AddArticleRequest implements Request {

	private Article article;

	@JsonCreator
	public AddArticleRequest(@JsonProperty(value = "article") Article article) {
		this.article = article;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}
