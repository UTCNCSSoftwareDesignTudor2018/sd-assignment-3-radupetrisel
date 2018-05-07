package presentation.controllers;

import communication.Requester;
import presentation.views.ArticleScene;

public class ArticleSceneController {
	
	public ArticleSceneController(ArticleScene page) {
		page.setArticles(Requester.viewArticles());
	}
}
