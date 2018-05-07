package communication;

import java.util.List;

import communication.entities.Article;
import communication.requests.CloseRequest;
import communication.requests.LoginRequest;
import communication.requests.ViewArticlesRequest;
import communication.responses.LoginResponse;
import communication.responses.ViewArticlesResponse;

public class Requester {

	private static Client client;

	public static void setClient(Client c) {
		client = c;
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

	public static void close() {

		if (client != null)
			client.addMessage(new CloseRequest());
	}

}
