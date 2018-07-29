package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controller.socket.Connection;
import controller.socket.TipoConexao;
import exception.InvalidUserException;
import model.Message;
import model.Usuario;

/**
 * Efetua o controle do módulo de troca de mensagens da aplicação.
 * 
 * @author marcelo.wippel, matheushr97
 */
public class MessageController {

	private List<Message> mensagens;
	private static MessageController instance;


	private MessageController() {
		mensagens = new ArrayList<>();
	}

	public static MessageController getInstance() {
		return instance == null ? instance = new MessageController() : instance;
	}

	public List<Usuario> getUsers() throws InvalidUserException {
		Optional<String> response = Connection.doConnection(TipoConexao.TCP, "GET USERS");
		if (response.isPresent()) {
			// Verificar se deu Usuário Inválido
			if (response.get().contains("lido!")) {
				throw new InvalidUserException("Usuário Inválido!");
			}

			String[] splited = response.get().split(":");
			List<Usuario> users = new ArrayList<>();

			// Um usuário a cada loop
			for (int i = 0; i < splited.length; i += 3) {
				Usuario u = new Usuario();
				u.setId(Integer.parseInt(splited[i]));
				u.setNome(splited[i + 1]);
				u.setWins(Integer.parseInt(splited[i + 2]));
				users.add(u);
			}

			return users;
		}
		return null;
	}

	public List<Message> getMessage() {
		Optional<String> response = Connection.doConnection(TipoConexao.TCP, "GET MESSAGE");
		if (response.isPresent()) {
			String[] splited = response.get().split(":");

			if (response.get().trim().equals(":")) {
				return null;
			}

			mensagens.add(new Message(Integer.parseInt(splited[0]), splited[1]));
			return mensagens;
		}
		return null;
	}

	public void sendMessage(Message message) {
		Connection.doConnection(TipoConexao.UDP, "SEND MESSAGE", message.getId(), message.getMessage());
	}
}
