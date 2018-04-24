package client;

import java.io.IOException;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.stage.Stage;
import presentation.controllers.LoginController;
import presentation.views.LoginScene;

public class App extends Application {

	public static void main(String[] args) throws UnknownHostException, IOException {

		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		
		Stage stage = new Stage();
		
		new LoginController(new LoginScene(stage, 300, 300));
		
		stage.show();
		
	}

}
