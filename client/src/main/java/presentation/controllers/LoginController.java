package presentation.controllers;

import communication.Requester;
import presentation.views.ArticleScene;
import presentation.views.LoginScene;

public class LoginController {

	public LoginController(LoginScene loginScene) {

		loginScene.setLoginHandler(e -> {

		if (Requester.login(loginScene.getUsername(), loginScene.getPassword())){
			
			new ArticleSceneController(new ArticleScene(loginScene.getStage(), 600, 800));
			
		}

		});

	}
}
