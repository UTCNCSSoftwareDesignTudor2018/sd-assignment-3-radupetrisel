package server;

import dal.repositories.ArticleRepository;

public class Server {

	public static void main(String[] args) {
		
		ArticleRepository repo = new ArticleRepository();
		
		System.out.println(repo.findByTitle("Test"));
		
		
	}

}
