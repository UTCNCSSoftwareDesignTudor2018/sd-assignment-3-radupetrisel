package presentation.views;

import java.util.List;

import communication.entities.Article;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ArticleScene {

	private ListView<Article> articles;
	private Button addArticle;
	private Button viewMyArticles;
	private BorderPane layout;

	public ArticleScene(Stage stage, int width, int height) {

		layout = new BorderPane();

		Scene scene = new Scene(layout, width, height);

		articles = new ListView<Article>();

		layout.setCenter(articles);

		addArticle = new Button("Add article");
		viewMyArticles = new Button("View my articles");

		VBox leftBox = new VBox();
		leftBox.getChildren().addAll(addArticle, viewMyArticles);

		layout.setLeft(leftBox);

		stage.setScene(scene);
	}

	public void setArticles(List<Article> articles) {

		this.articles.setItems(FXCollections.observableArrayList(articles));
	}
}
