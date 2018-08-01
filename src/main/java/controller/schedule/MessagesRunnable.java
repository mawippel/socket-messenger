package controller.schedule;

import controller.MessageController;
import javafx.application.Platform;

public class MessagesRunnable implements Runnable{
	
	private MessageController mc = MessageController.getInstance();
	
	@Override
	public void run() {
		Platform.runLater(()-> {
			mc.getMessage(); //Popular o MessageController
		});
		
	}

}
