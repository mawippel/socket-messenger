package gui;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import controller.AppSession;
import controller.MessageController;
import controller.schedule.ScheduleController;
import exception.InvalidUserException;
import model.Message;
import model.Usuario;

public class teste {

	public static void main(String[] args) {
		MessageController mc = MessageController.getInstance();
		
		AppSession.setUsuarioLogado(new Usuario(1027, "tafhg"));
		
		List<Usuario> users = null;
		try {
			users = mc.getUsers();
		} catch (InvalidUserException e1) {
			e1.printStackTrace();
		}
		System.out.println("--------USUÁRIOS LOGADOS-------");
		System.out.println(users);
		
		Message m = new Message(0, "Teste do Marcelo!");
		mc.sendMessage(m);
		
		List<Message> message = mc.getMessage();
		System.out.println("------------MENSAGEM-----------");
		System.out.println(message);
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(mc.getMessageProperty().toString());
				System.out.println(mc.getUsuarioProperty().toString());
			}
		}, 10, 5, TimeUnit.SECONDS);
		ScheduleController.getInstance().start();
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			ScheduleController.getInstance().stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			ScheduleController.getInstance().restart();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
