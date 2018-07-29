package gui;

import controller.SocketController;
import model.Message;
import model.Usuario;

/**
 * Interface do usuário.
 * @author marcelo.wippel
 */
public class SocketGUI {

	public static void main (String[] args) {
		SocketController sc = new SocketController();
		
		Usuario u = sc.getUsers("1027", "tafhg");
		System.out.println("--------USUÁRIOS LOGADOS-------");
		System.out.println(u);
		
		Message m = new Message(0, "Teste do Marcelo!");
		sc.sendMessage("1027", "tafhg", m);
		
		Message message = sc.getMessage("1027", "tafhg");
		System.out.println("------------MENSAGEM-----------");
		System.out.println(message);
	}
	
}
