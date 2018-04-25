package presentation.controllers;

import java.io.IOException;

import communication.Requester;
import presentation.views.LoginScene;

public class LoginController {

	private LoginScene loginScene;

	public LoginController(LoginScene loginScene) {
		this.loginScene = loginScene;

		loginScene.setLoginHandler(e -> {

			System.out.println(Requester.login(loginScene.getUsername(), loginScene.getPassword()));

		});

	}
}
