package communication;

import java.util.List;

import communication.entities.Article;
import communication.requests.AddArticleRequest;
import communication.requests.CloseRequest;
import communication.requests.LoginRequest;
import communication.requests.ViewArticlesRequest;
import communication.responses.LoginResponse;
import communication.responses.Response;
import communication.responses.ViewArticlesResponse;

public class Requester {

	private static Client client;

	public static void setClient(Client c) {
		client = c;
	}
	
	public static void setUsername(String username) {
		client.setUsername(username);
	}
	
	public static String login(String username, String password) {

		client.addMessage(new LoginRequest(username, password));

		LoginResponse resp = (LoginResponse) client.getResponse();
		return resp.getMessage();
	}

	public static List<Article> viewArticles() {

		client.addMessage(new ViewArticlesRequest());
		List<Article> articles = ((ViewArticlesResponse) client.getResponse()).getArticles();

		return articles;
	}
	
	public static void addArticle(String title, String abstrac, String body) {
		
		Article article = new Article();
		article.setAuthor(client.getUsername());
		article.setArticleAbstract(abstrac);
		article.setBody(body);
		article.setTitle(title);
		
		client.addMessage(new AddArticleRequest(article));
		
		Response resp = client.getResponse();
	}

	public static void close() {
		
		client.addMessage(new CloseRequest());
		client.getResponse();
	}

}
