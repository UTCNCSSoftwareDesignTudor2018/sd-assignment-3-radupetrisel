package communication;

public class Requester {

	private static Client client;
	
	public static void setClient(Client c) {
		client = c;
	}
	
	public static boolean login(String username, String password) {
		
		client.addMessage("login", username + " " + password);
		
		return Boolean.parseBoolean(client.getResponse());
	}
	
}
