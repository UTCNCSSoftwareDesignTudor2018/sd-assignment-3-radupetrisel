package bll.dtos;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class ArticleDto {

	private String title;
	private String author;
	private String articleAbstract;
	private String body;

	public ArticleDto() {

	}

	@JsonCreator
	public ArticleDto(@JsonProperty(value = "title") String title, @JsonProperty(value = "author") String author,
			@JsonProperty(value = "abstract") String articleAbstract, @JsonProperty(value = "body") String body) {
		
		this.title = title;
		this.author = author;
		this.articleAbstract = articleAbstract;
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getArticleAbstract() {
		return articleAbstract;
	}

	public void setArticleAbstract(String articleAbstract) {
		this.articleAbstract = articleAbstract;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
