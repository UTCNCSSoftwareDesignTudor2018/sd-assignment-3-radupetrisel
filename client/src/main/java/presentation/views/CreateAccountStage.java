package presentation.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateAccountStage {

	private TextField firstName;
	private TextField lastName;
	private TextField username;
	private PasswordField password;
	private Button create;
	private Stage stage;
	private Text message;

	public CreateAccountStage(int width, int height) {

		stage = new Stage();
		VBox box = new VBox();
		Scene scene = new Scene(box, width, height);

		message = new Text();
		message.setVisible(false);
		
		firstName = new TextField();
		firstName.setPromptText("First name");

		lastName = new TextField();
		lastName.setPromptText("Last name");

		username = new TextField();
		username.setPromptText("username");

		password = new PasswordField();
		password.setPromptText("Password");

		create = new Button("Create");

		box.getChildren().addAll(message, firstName, lastName, username, password, create);

		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}

	public String getUsername() {
		return username.getText();
	}

	public String getFirstName() {
		return firstName.getText();
	}

	public String getLastName() {
		return lastName.getText();
	}

	public String getPassword() {
		return password.getText();
	}

	public void setCreateHandler(EventHandler<ActionEvent> e) {
		create.setOnAction(e);
	}
	
	public void close() {
		stage.close();
	}
	
	public void setMessage(String message) {
		this.message.setText(message);
		this.message.setVisible(true);
	}

}
