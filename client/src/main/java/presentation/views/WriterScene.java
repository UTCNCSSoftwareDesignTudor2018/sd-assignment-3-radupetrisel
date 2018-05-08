package presentation.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WriterScene extends ReaderScene {

	private Button addArticle;

	public WriterScene(Stage stage, int width, int height) {

		super(stage, width, height);
		addArticle = new Button("Add article");

		leftBox.getChildren().add(addArticle);
	}

	public void setAddArticleHandler(EventHandler<ActionEvent> e) {
		this.addArticle.setOnAction(e);
	}

}
