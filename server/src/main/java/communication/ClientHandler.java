package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;

import com.fasterxml.jackson.databind.ObjectMapper;

import communication.requests.CloseRequest;
import communication.requests.Request;
import communication.responses.Response;

public class ClientHandler extends Observable implements Runnable {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public ClientHandler(Socket socket) {

		System.out.println("creating new client");
		this.socket = socket;

		try {

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		boolean closed = false;
		try {
			while (!closed) {

				String command = in.readLine();

				Request request = new ObjectMapper().readValue(command, Request.class);

				Response resp = request.execute();

				out.println(new ObjectMapper().writeValueAsString(resp));
				out.flush();

				if (request instanceof CloseRequest) {
					closed = true;
				}
			}

			this.setChanged();
			this.notifyObservers();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
