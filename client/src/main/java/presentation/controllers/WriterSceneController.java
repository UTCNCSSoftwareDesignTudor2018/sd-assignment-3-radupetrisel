package presentation.controllers;

import communication.Requester;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import presentation.views.WriterScene;

public class WriterSceneController extends ReaderSceneController {

	public WriterSceneController(WriterScene page) {
		super(page);
		page.setAddArticleHandler(e -> {

			VBox box = new VBox();
			box.setSpacing(20);
			
			TextField title = new TextField();
			title.setPromptText("Title");

			TextField abstrac = new TextField();
			abstrac.setPromptText("Abstract");

			TextArea body = new TextArea();
			body.setPromptText("Your article goes here");
			body.setWrapText(true);
			body.setMinHeight(30);

			Button done = new Button("Done");

			box.getChildren().addAll(title, abstrac, body, done);

			page.setCenter(box);

			done.setOnAction(ea -> {

				Requester.addArticle(title.getText(), abstrac.getText(), body.getText());

			});
		});
	}

}
