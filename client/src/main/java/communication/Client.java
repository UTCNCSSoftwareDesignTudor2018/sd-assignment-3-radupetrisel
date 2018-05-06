package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

import com.fasterxml.jackson.databind.ObjectMapper;

import communication.commands.Request;

public class Client implements Runnable {

	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private Queue<Request> messages;
	private Queue<String> responses;
	private Semaphore responseSemaphore;
	private Semaphore messageSemaphore;

	public Client(String host, int port) throws UnknownHostException, IOException {

		responseSemaphore = new Semaphore(0);
		messageSemaphore = new Semaphore(0);
		socket = new Socket(host, port);
		out = new PrintWriter(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		messages = new LinkedBlockingQueue<Request>();
		responses = new LinkedBlockingQueue<String>();

	}

	public void addMessage(Request request) {

		this.messages.add(request);
		messageSemaphore.release();
	}

	public String getResponse() {

		try {
			responseSemaphore.acquire();
			return this.responses.poll();
		} catch (InterruptedException e) {
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

				Request request = messages.poll();
				
				try {
					
					out.println(new ObjectMapper().writeValueAsString(request));
					out.flush();
					
					this.responses.add(in.readLine());
					responseSemaphore.release();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
