package controller.schedule;

import controller.MessageController;
import exception.InvalidUserException;

public class UsersRunnable implements Runnable{
	
	private MessageController mc = MessageController.getInstance();
	
	@Override
	public void run() {
		try {
			mc.getUsers();
		} catch (InvalidUserException e) {
			e.printStackTrace();
		}
	}

}
