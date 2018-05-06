package communication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import communication.commands.Close;
import communication.commands.Login;
import communication.commands.ViewArticles;
import communication.entities.Article;

public class Requester {

	private static Client client;

	public static void setClient(Client c) {
		client = c;
	}

	public static boolean login(String username, String password) {
		
		client.addMessage(new Login(username, password));
		
		String resp = client.getResponse();
		return Boolean.parseBoolean(resp);
	}

	public static List<Article> viewArticles() {

		client.addMessage(new ViewArticles());
		List<Article> articles = null;
		
		try {
			articles = new ObjectMapper().readValue(client.getResponse(), ArrayList.class);
			System.out.println(articles.get(0));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return articles;
	}

	public static void close() {

		if (client != null)
			client.addMessage(new Close());

	}

}
