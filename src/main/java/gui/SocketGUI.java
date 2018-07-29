package gui;

import java.util.List;

import controller.SocketMessageController;
import model.Message;
import model.Usuario;

/**
 * Interface do usuário.
 * @author marcelo.wippel
 */
public class SocketGUI {

	public static void main (String[] args) {
		SocketMessageController sc = SocketMessageController.getInstance();
		
		List<Usuario> users = sc.getUsers("1027", "tafhg");
		System.out.println("--------USUÁRIOS LOGADOS-------");
		System.out.println(users);
		
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		users = sc.getUsers("5094", "pbapd");
		System.out.println("--------USUÁRIOS LOGADOS-------");
		System.out.println(users);

		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		users = sc.getUsers("5094", "pbapd");
		System.out.println("--------USUÁRIOS LOGADOS-------");
		System.out.println(users);
		
		Message m = new Message(0, "Teste do Marcelo!");
		sc.sendMessage("1027", "tafhg", m);
		
		Message m2 = new Message(0, "Matheus que enviou para o servidor");
		sc.sendMessage("5094", "pbapd", m2);
		
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		List<Message> message = sc.getMessage("1027", "tafhg");
		System.out.println("------------MENSAGEM-----------");
		System.out.println(message);
		
		message = sc.getMessage("5094", "pbapd");
		System.out.println("------------MENSAGEM-----------");
		System.out.println(message);
	}
	
}
