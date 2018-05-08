package presentation.controllers;

import communication.Requester;
import communication.entities.Article;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import presentation.views.ReaderScene;

public class ReaderSceneController{
	
	private ReaderScene page;
	
	public ReaderSceneController(ReaderScene page) {
		
		this.page = page;
		page.setViewArticlesHandler(e -> page.setCenter(getArticles()));
	}
	
	public ListView<Article> getArticles(){
		
		ListView<Article> articles = new ListView<Article>();
		articles.setItems(FXCollections.observableArrayList(Requester.viewArticles()));
		
		articles.setOnMouseClicked(e -> {
			
			if (e.getButton().equals(MouseButton.PRIMARY))
				if (e.getClickCount() == 2) {
					
					Article article = articles.getSelectionModel().getSelectedItem();
					
					VBox box = new VBox();
					
					Text title = new Text(article.getTitle());
					title.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
					title.setWrappingWidth(400);
					
					Text author = new Text("by: " + article.getAuthor());
					author.setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
					author.setWrappingWidth(400);
					
					Text body = new Text(article.getBody());
					body.setWrappingWidth(400);
					
					box.setSpacing(5);
					box.setAlignment(Pos.TOP_LEFT);
					
					box.getChildren().addAll(title, author, body);
					
					page.setCenter(box);
				}			
		});
		
		return articles;
	}
}
