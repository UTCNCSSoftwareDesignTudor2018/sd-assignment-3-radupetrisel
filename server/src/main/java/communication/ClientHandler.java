package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import bll.ArticleBLL;
import bll.UserBLL;
import bll.dtos.ArticleDto;
import communication.commands.Login;
import communication.commands.Request;
import dal.repositories.UserRepository;

public class ClientHandler implements Runnable {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private int id;
	private UserBLL ubll;
	private ArticleBLL abll;

	public ClientHandler(Socket socket) {

		System.out.println("creating new client");
		this.socket = socket;
		
		try {
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			ubll = new UserBLL();
			ubll.setUserRepo(new UserRepository());
			abll = new ArticleBLL();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		boolean closed = false;
		while (!closed) {
			try {
				
				String command = in.readLine();
				
				if (command == null) continue;
				
				Request request = null;
				
				request = new ObjectMapper().readValue(command, Request.class);
		
				switch (request.getClass().getSimpleName()) {
				
				case "Login":

					Login log = (Login)request;
					
					int id = ubll.login(log.getUsername(), log.getPassword());

					if (id > 0) {
						out.println(true);
						this.id = id;
					} else {
						out.println(false);
					}

					out.flush();
					break;

				case "ViewArticles":

					List<ArticleDto> articles = abll.findAll();

					ObjectMapper mapper = new ObjectMapper();
					out.println(mapper.writeValueAsString(articles));
					out.flush();
					break;

//				case 
//
//					ArticleDto article = new ObjectMapper().readValue(in.readLine(), ArticleDto.class);
//
//					ubll.addArticle(this.id, article);
//					break;

				case "Close":
					System.out.println("closing client");
					in.close();
					out.close();
					socket.close();
					closed = true;
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
