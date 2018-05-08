package presentation.controllers;

import communication.Requester;
import presentation.views.CreateAccountStage;

public class CreateAccountController {

	public CreateAccountController(CreateAccountStage stage) {
		
		stage.setCreateHandler(e -> {
			
			String message = Requester.createUser(stage.getFirstName(), stage.getLastName(), stage.getUsername(), stage.getPassword());
			
			if (message.equals("Success"))
				stage.close();
			else stage.setMessage(message);
		});
		
	}
}
