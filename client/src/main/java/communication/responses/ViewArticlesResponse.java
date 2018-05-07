package communication.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import communication.entities.Article;

public class ViewArticlesResponse implements Response {

	private List<Article> articles;

	@JsonCreator
	public ViewArticlesResponse(@JsonProperty(value = "articles") List<Article> articles) {
		this.articles = articles;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

}
