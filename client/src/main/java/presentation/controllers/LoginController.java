package presentation.controllers;

import communication.Requester;
import presentation.views.CreateAccountStage;
import presentation.views.LoginScene;
import presentation.views.ReaderScene;
import presentation.views.WriterScene;

public class LoginController {

	public LoginController(LoginScene loginScene) {

		loginScene.setGuestLoginHandler(e -> {

			new ReaderSceneController(new ReaderScene(loginScene.getStage(), 600, 800));

		});

		loginScene.setCreateHandler(e -> new CreateAccountController(new CreateAccountStage(300, 400)));

		loginScene.setLoginHandler(e -> {

			String message = Requester.login(loginScene.getUsername(), loginScene.getPassword());
			if (message.equals("Success")) {

				Requester.setUsername(loginScene.getUsername());
				new WriterSceneController(new WriterScene(loginScene.getStage(), 600, 800));
			} else {
				loginScene.setFailure(message);
			}

		});

	}
}
