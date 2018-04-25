package client;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import communication.Client;
import communication.Requester;
import javafx.application.Application;
import javafx.stage.Stage;
import presentation.controllers.LoginController;
import presentation.views.LoginScene;

public class App extends Application {

	public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {

		Client client = null;
		try {
			client = new Client("localhost", 1111);
		} catch (ConnectException e) {
			System.out.println("cannot connect");
			System.exit(1);
		}
		
		Requester.setClient(client);
		Thread t = new Thread(client);

		t.start();

		launch(args);

		t.join();
	}

	@Override
	public void start(Stage arg0) throws Exception {

		Stage stage = new Stage();

		new LoginController(new LoginScene(stage, 300, 300));

		stage.show();

		stage.setOnCloseRequest(e -> Requester.close());
	}

}
