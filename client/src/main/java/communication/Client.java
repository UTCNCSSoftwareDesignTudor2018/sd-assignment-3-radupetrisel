package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import communication.entities.Article;

public class Client {

	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	public Client(String host, int port) {
		try {
			socket = new Socket(host, port);
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean login(String username, String password) throws IOException {

		out.println("login");
		out.println(username);
		out.println(password);
		out.flush();

		return Boolean.parseBoolean(in.readLine());
	}

	public List<Article> viewArticles() throws JsonParseException, JsonMappingException, IOException {

		out.println("articles");

		List<Article> articles = new ObjectMapper().readValue(in.readLine(), ArrayList.class);

		return articles;
	}

	public void add(Article article) throws JsonGenerationException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(out, article);

	}

}
