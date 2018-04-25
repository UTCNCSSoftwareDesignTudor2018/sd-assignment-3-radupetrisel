package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class Client implements Runnable {

	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private volatile Queue<Pair<String, String>> messages;
	private volatile Queue<String> responses;
	
	private class Pair<A, B>{
		
		private A first;
		private B second;
		
		public Pair(A a, B b) {
			first = a;
			second = b;
		}
		
		public A getFirst() {
			return first;
		}
		
		public B getSecond() {
			return second;
		}
		
	}
	
	public Client(String host, int port) {
		try {
			socket = new Socket(host, port);
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			messages = new LinkedBlockingQueue<Pair<String, String>>();
			responses = new LinkedBlockingQueue<String>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String login(String username, String password) throws IOException {

		out.println("login");
		out.println(username);
		out.println(password);
		out.flush();

		return in.readLine();
	}

	private String viewArticles() throws JsonParseException, JsonMappingException, IOException {

		out.println("articles");

		return in.readLine();
	}

	private void add(String article) throws JsonGenerationException, JsonMappingException, IOException {

		out.println(article);

	}
	
	public void addMessage(String command, String body) {
		this.messages.add(new Pair<String, String>(command, body));
	}
	
	public String getResponse() {
		return this.responses.poll();
	}

	@Override
	public void run() {

		while (true) {

			if (!messages.isEmpty()) {

				Pair<String, String> message = messages.poll();

				String command = message.getFirst();
				String body = message.getSecond();

				switch (command) {

				case "login":
					String[] userpass = body.split(" ");
					String username = userpass[0];
					String password = userpass[1];
					try {
						responses.add(this.login(username, password));
					} catch (IOException e) {
						e.printStackTrace();
					}

					break;

				case "viewArticles":

					try {
						responses.add(this.viewArticles());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case "addArticle":

					try {
						this.add(body);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;

				}

			}

		}

	}

}
