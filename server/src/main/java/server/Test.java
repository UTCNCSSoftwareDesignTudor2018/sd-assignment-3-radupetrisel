package server;

import communication.Server;

public class Test {

	public static void main(String[] args) {
		
		dal.utils.Hibernate.getInstance();
		
		Server server = new Server(1111);
		server.serve();
		
		dal.utils.Hibernate.close();
	}

}
