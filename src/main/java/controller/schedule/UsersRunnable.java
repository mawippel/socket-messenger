package controller.schedule;

import controller.MessageController;
import exception.InvalidUserException;
import javafx.application.Platform;

public class UsersRunnable implements Runnable {

	private MessageController mc = MessageController.getInstance();

	@Override
	public void run() {
		Platform.runLater(() -> {
			try {
				mc.getUsers();
			} catch (InvalidUserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

}
