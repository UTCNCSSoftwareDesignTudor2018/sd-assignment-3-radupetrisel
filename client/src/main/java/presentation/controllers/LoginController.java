package presentation.controllers;

import java.io.IOException;

import communication.Client;
import presentation.views.LoginScene;

public class LoginController {
	
	private LoginScene loginScene;
	
	public LoginController(LoginScene loginScene) {
		this.loginScene = loginScene;
		
		loginScene.setLoginHandler(e -> {
			
			Client client = new Client("localhost", 1111);
			
			try {
				System.out.println(client.login(loginScene.getUsername(), loginScene.getPassword()));
			} catch (IOException e1) {
				System.out.println("Error");
				e1.printStackTrace();
			}
			
		});
		
	}
}
