package communication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import communication.entities.Article;

public class Requester {

	private static Client client;

	public static void setClient(Client c) {
		client = c;
	}

	public static boolean login(String username, String password) {

		client.addMessage("login", username + " " + password);

		return Boolean.parseBoolean(client.getResponse());
	}
	
	public static List<Article> viewArticles(){

		client.addMessage("viewArticles", null);
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
			client.addMessage("close", null);

	}

}
