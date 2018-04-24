package server;

import communication.Server;
import dal.entities.User;
import dal.repositories.UserRepository;

public class Test {

	public static void main(String[] args) {
		
		dal.utils.Hibernate.getInstance();
		Server server = new Server(1111);
		server.serve();
		
		dal.utils.Hibernate.close();
	}

}
