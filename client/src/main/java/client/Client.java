package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Client extends Application{
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		
		final Socket client = new Socket("localhost", 1111);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		PrintWriter out = new PrintWriter(client.getOutputStream());
		
		out.println("login");
		out.flush();
		out.println("diana.danila");
		out.println("1234");
		out.flush();
			
		String success = in.readLine();
		
		if (success.equals("success")) {
			
			Stage stage = new Stage();
			
			HBox box = new HBox();
			
			Scene scene = new Scene(box, 200, 300);
			stage.setScene(scene);
			stage.show();
			
			stage.setOnCloseRequest(e -> {
				try {
					client.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
		}
		
	}
}
