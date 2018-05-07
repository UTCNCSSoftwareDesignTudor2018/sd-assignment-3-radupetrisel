package bll.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import dal.entities.ArticleEntity;

public class Article {

	private String title;
	private String author;
	private String articleAbstract;
	private String body;

	@JsonCreator
	public Article(@JsonProperty(value = "title") String title, @JsonProperty(value = "author") String author,
			@JsonProperty(value = "abstract") String articleAbstract, @JsonProperty(value = "body") String body) {
		this.title = title;
		this.author = author;
		this.articleAbstract = articleAbstract;
		this.body = body;
	}

	public Article(ArticleEntity ae) {

		this.title = ae.getTitle();
		this.articleAbstract = ae.getArticleAbstract();
		this.body = ae.getBody();
		this.author = ae.getAuthor().getUsername();

	}

	public Article() {
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

	@Override
	public String toString() {
		return this.title + "\n" + this.author + "\n" + this.articleAbstract;
	}

	public ArticleEntity convert() {

		ArticleEntity ae = new ArticleEntity();
		ae.setTitle(this.title);
		ae.setBody(this.body);
		ae.setArticleAbstract(this.articleAbstract);

		return ae;
	}
}
