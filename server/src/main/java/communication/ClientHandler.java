package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bll.UserBLL;
import dal.repositories.UserRepository;

public class ClientHandler implements Runnable {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private int id;
	
	public ClientHandler(Socket socket) {
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

		try {
			
			String request = in.readLine();
			
			switch (request) {
			
			case "login":
				
				
				String username = in.readLine();
				String password = in.readLine();
				
				UserBLL ubll = new UserBLL();
				ubll.setUserRepo(new UserRepository());
				int id = ubll.login(username, password);
				
				if (id > 0) out.println("success");
				else out.println("invalid password");
					
				out.flush();
				break;
			
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
