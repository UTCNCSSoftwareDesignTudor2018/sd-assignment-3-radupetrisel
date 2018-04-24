package presentation.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScene {

	private TextField username;
	private PasswordField password;
	private Button login;

	public LoginScene(Stage stage, int width, int height) {

		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.setSpacing(10);

		Scene scene = new Scene(box, width, height);

		username = new TextField();
		username.setPromptText("username");
		username.setAlignment(Pos.CENTER);
		username.setMaxWidth(100);

		password = new PasswordField();
		password.setPromptText("password");
		password.setAlignment(Pos.CENTER);
		password.setMaxWidth(100);

		login = new Button("Login");
		login.setAlignment(Pos.CENTER);

		box.getChildren().addAll(username, password, login);
		stage.setScene(scene);
	}
	
	public String getUsername() {
		return username.getText();
	}
	
	public String getPassword() {
		return password.getText();
	}
	
	public void setLoginHandler(EventHandler<ActionEvent> e) {
		login.setOnAction(e);
	}
}
