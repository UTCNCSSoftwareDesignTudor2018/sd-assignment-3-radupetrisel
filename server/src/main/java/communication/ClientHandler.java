package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import bll.UserBLL;
import bll.dtos.ArticleDto;
import dal.repositories.UserRepository;

public class ClientHandler implements Runnable {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private int id;
	private UserBLL ubll;
	
	public ClientHandler(Socket socket) {
		this.socket = socket;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			ubll = new UserBLL();
			ubll.setUserRepo(new UserRepository());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		try {
			
			String request = in.readLine();
			
			switch (request) {
			
			case "login":
				
				
				String username = in.readLine();
				String password = in.readLine();
				
				int id = ubll.login(username, password);
				
				if (id > 0) { out.println(true);
					this.id = id;
				}
				else {
					out.println(false);
					Thread.currentThread().interrupt();
				}
					
				out.flush();
				break;
				
			case "articles":
				
				List<ArticleDto> articles = ubll.viewArticles(this.id);
				
				ObjectMapper mapper = new ObjectMapper();
				out.println(mapper.writeValueAsString(articles));
				out.flush();
				break;
				
			case "add":
				
				ArticleDto article = new ObjectMapper().readValue(in.readLine(), ArticleDto.class);
				
				ubll.addArticle(this.id, article);
				break;
				
			case "close":
				in.close();
				out.close();
				socket.close();
				Thread.currentThread().interrupt();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
