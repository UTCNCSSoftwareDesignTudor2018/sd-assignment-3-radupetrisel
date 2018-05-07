package presentation.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ArticleScene {

	private Button addArticle;
	private Button viewArticles;
	private BorderPane layout;

	public ArticleScene(Stage stage, int width, int height) {

		layout = new BorderPane();

		Scene scene = new Scene(layout, width, height);

		addArticle = new Button("Add article");
		viewArticles = new Button("View articles");

		VBox leftBox = new VBox();
		leftBox.getChildren().addAll(addArticle, viewArticles);

		layout.setLeft(leftBox);

		stage.setScene(scene);
	}
	
	public void setCenter(Node center) {
		this.layout.setCenter(center);
	}
	
	public void setAddArticleHandler(EventHandler<ActionEvent> e) {
		this.addArticle.setOnAction(e);
	}
	
	public void setViewArticlesHandler(EventHandler<ActionEvent> e) {
		this.viewArticles.setOnAction(e);
	}
}
