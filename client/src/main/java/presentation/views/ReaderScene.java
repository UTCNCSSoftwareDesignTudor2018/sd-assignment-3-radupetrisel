package presentation.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReaderScene{
	
	private Button viewArticles;
	protected VBox leftBox;
	private BorderPane layout;

	public ReaderScene(Stage stage, int width, int height) {

		layout = new BorderPane();

		Scene scene = new Scene(layout, width, height);
		
		viewArticles = new Button("View articles");

		leftBox = new VBox();
		leftBox.getChildren().addAll(viewArticles);

		layout.setLeft(leftBox);

		stage.setScene(scene);
	}
	
	public void setCenter(Node center) {
		this.layout.setCenter(center);
	}
	
	public void setViewArticlesHandler(EventHandler<ActionEvent> e) {
		this.viewArticles.setOnAction(e);
	}
}
