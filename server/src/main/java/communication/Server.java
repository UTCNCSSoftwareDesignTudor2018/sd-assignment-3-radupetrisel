package communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	private ServerSocket server;
	private List<ClientHandler> clients;

	public Server(int port) {
		clients = new ArrayList<ClientHandler>();
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void serve() {
		
		while (true) {
			
			try {
				
				Socket socket = server.accept();
				ClientHandler client = new ClientHandler(socket);
				clients.add(client);
				new Thread(client).start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	

}
