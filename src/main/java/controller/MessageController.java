package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controller.socket.Connection;
import controller.socket.TipoConexao;
import exception.InvalidUserException;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import model.Message;
import model.Usuario;

/**
 * Efetua o controle do m�dulo de troca de mensagens da aplica��o.
 * 
 * @author marcelo.wippel, matheushr97
 */
public class MessageController {

	private ListProperty<Message> mensagens;
	private ListProperty<Usuario> usuarios;
	private static MessageController instance;


	private MessageController() {
		mensagens = new SimpleListProperty<Message>(FXCollections.observableArrayList());
		createDefaultUsers();
	}

	public static MessageController getInstance() {
		return instance == null ? instance = new MessageController() : instance;
	}

	public List<Usuario> getUsers() throws InvalidUserException {
		Optional<String> response = Connection.doConnection(TipoConexao.TCP, "GET USERS");
		if (response.isPresent()) {
			// Verificar se deu Usu�rio Inv�lido
			if (response.get().contains("lido!")) {
				throw new InvalidUserException("Usu�rio Inv�lido!");
			}

			String[] splited = response.get().split(":");
			ArrayList<Usuario> users = new ArrayList<>();

			// Um usu�rio a cada loop
			for (int i = 0; i < splited.length; i += 3) {
				Usuario u = new Usuario();
				u.setId(Integer.parseInt(splited[i]));
				u.setNome(splited[i + 1]);
				u.setWins(Integer.parseInt(splited[i + 2]));
				users.add(u);
			}
			
			usuarios.clear();
			createDefaultUsers();
			usuarios.addAll(users);

			return usuarios;
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

			// Procura o usu�rio na lista de usu�rios logados.
			int userID = Integer.parseInt(splited[0]);
			Optional<Usuario> filteredList = usuarios.stream().filter(u -> u.getId() == userID).findAny();
			
			// Adiciona a mensagem referenciado ao usu�rio encontrado, caso n�o encontre o usu�rio, vincula ao usu�rio "Todos"
			mensagens.add(new Message(filteredList.orElse(usuarios.get(0)), splited[1]));
			
			return mensagens;
		}
		return null;
	}
	
	public ListProperty<Message> getMessageProperty(){
		return this.mensagens;
	}
	
	public ListProperty<Usuario> getUsuarioProperty(){
		return this.usuarios;
	}

	public void sendMessage(Message message) {
		Connection.doConnection(TipoConexao.UDP, "SEND MESSAGE", message.getId(), message.getMessage());
	}
	
	private void createDefaultUsers(){
		usuarios = new SimpleListProperty<Usuario>(FXCollections.observableArrayList());
		Usuario u = new Usuario(0);
		u.setNome("Todos");
		usuarios.add(u);
	};
}
