package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
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
		System.out.println("creating new client");
		this.socket = socket;
		try {
			this.socket.setKeepAlive(true);
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

		boolean closed = false;
		while (!closed) {
			try {

				String request = in.readLine();

				switch (request) {

				case "login":

					String username = in.readLine();
					String password = in.readLine();

					int id = ubll.login(username, password);

					if (id > 0) {
						out.println(true);
						this.id = id;
					} else {
						out.println(false);
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
