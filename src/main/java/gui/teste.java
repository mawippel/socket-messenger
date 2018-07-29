package gui;

import java.util.List;

import controller.AppSession;
import controller.MessageController;
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
	}

}
