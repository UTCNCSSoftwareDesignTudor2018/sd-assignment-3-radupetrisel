package presentation.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginScene {

	private TextField username;
	private PasswordField password;
	private Button login;
	private Button guestLogin;
	private Stage stage;
	private Button createAccount;
	private Text failure;
	
	public LoginScene(Stage stage, int width, int height) {

		this.stage = stage;
		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.setSpacing(10);

		Scene scene = new Scene(box, width, height);
		
		failure = new Text();
		failure.setVisible(false);
		
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
		
		guestLogin = new Button("Login as guest");
		guestLogin.setAlignment(Pos.CENTER);
		
		createAccount = new Button("Create account");
		createAccount.setAlignment(Pos.CENTER);
		
		box.getChildren().addAll(failure, username, password, login, guestLogin, createAccount);
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
	
	public void setGuestLoginHandler(EventHandler<ActionEvent> e) {
		guestLogin.setOnAction(e);
	}
	
	public void setCreateHandler(EventHandler<ActionEvent> e) {
		createAccount.setOnAction(e);
	}
	
	public void setFailure(String message) {
		failure.setText(message);
		failure.setVisible(true);
	}
	
	
	public Stage getStage() {
		return this.stage;
	}
}
