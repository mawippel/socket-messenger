package controller.schedule;

import controller.MessageController;

public class MessagesRunnable implements Runnable{
	
	private MessageController mc = MessageController.getInstance();
	
	@Override
	public void run() {
		mc.getMessage(); //Popular o MessageController
	}

}
