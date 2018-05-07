package presentation.controllers;

import communication.Requester;
import communication.entities.Article;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import presentation.views.ArticleScene;

public class ArticleSceneController {
	
	private ArticleScene page;
	
	public ArticleSceneController(ArticleScene page) {
		
		this.page = page;
		page.setViewArticlesHandler(e -> page.setCenter(getArticles()));
		page.setAddArticleHandler(e -> {
			
			VBox box = new VBox();
			
			TextField title = new TextField();
			title.setPromptText("Title");
			
			TextField abstrac = new TextField();
			abstrac.setPromptText("Abstract");
			
			TextArea body = new TextArea();
			body.setMinHeight(30);
			
			Button done = new Button("Done");	
			
			box.getChildren().addAll(title, abstrac, body, done);
			
			page.setCenter(box);
			
			done.setOnAction(ea -> {
				
				Requester.addArticle(title.getText(), abstrac.getText(), body.getText());
				
			});
		});
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
					
					Text author = new Text("by: " + article.getAuthor());
					author.setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
					
					Text body = new Text(article.getBody());
					
					box.setSpacing(5);
					box.setAlignment(Pos.TOP_LEFT);
					
					box.getChildren().addAll(title, author, body);
					
					page.setCenter(box);
				}			
		});
		
		return articles;
	}
}
