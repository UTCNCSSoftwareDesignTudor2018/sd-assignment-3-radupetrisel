package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class Client implements Runnable {

	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private Queue<Pair<String, String>> messages;
	private Queue<String> responses;
	private Semaphore responseSemaphore;
	private Semaphore messageSemaphore;

	private class Pair<A, B> {

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

	public Client(String host, int port) throws UnknownHostException, IOException {

		responseSemaphore = new Semaphore(0);
		messageSemaphore = new Semaphore(0);
		socket = new Socket(host, port);
		out = new PrintWriter(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		messages = new LinkedBlockingQueue<Pair<String, String>>();
		responses = new LinkedBlockingQueue<String>();

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
		out.flush();

		return in.readLine();
	}

	private void add(String article) throws JsonGenerationException, JsonMappingException, IOException {

		out.println(article);
		out.flush();

	}

	public void addMessage(String command, String body) {

		this.messages.add(new Pair<String, String>(command, body));
		messageSemaphore.release();
	}

	private void close() {
		if (out != null) {
			out.println("close");
			out.flush();
		}
	}

	public String getResponse() {

		try {
			responseSemaphore.acquire();
			return this.responses.poll();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void run() {

		boolean closed = false;
		while (!closed) {

			try {
				messageSemaphore.acquire();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

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
						responseSemaphore.release();
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
						e.printStackTrace();
					}

					break;

				case "close":
					closed = true;
					close();
					break;
				}

			}

		}

	}

}
