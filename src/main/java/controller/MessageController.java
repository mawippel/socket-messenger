package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	public static final Usuario USUARIO_TODOS = new Usuario(0, null, "Todos");

	private MessageController() {
		mensagens = new SimpleListProperty<Message>(FXCollections.observableArrayList());
		usuarios = new SimpleListProperty<Usuario>(FXCollections.observableArrayList(new HashSet<Usuario>()));
		usuarios.add(USUARIO_TODOS);
	}

	public static MessageController getInstance() {
		return instance == null ? instance = new MessageController() : instance;
	}

	public List<Usuario> getUsers() throws InvalidUserException {
		Optional<String> response = Connection.doConnection(TipoConexao.TCP, "GET USERS");
		if (response.isPresent()) {
			// Verificar se deu Usu�rio Inv�lido
			if (response.get().trim().isEmpty() || response.get().contains("lido!")) {
				throw new InvalidUserException("Usu�rio inv�lido!");
			}

			List<Usuario> responseUsers = getUsersFromResponse(response.get().split(":"));
			updateUsers(responseUsers);
			return responseUsers;
		}
		return null;
	}

	/**
	 * Atualiza os usu�rios logados na aplica��o.
	 * 
	 * @param responseUsers
	 *            usu�rios atualmente logados
	 */
	private void updateUsers(List<Usuario> responseUsers) {
		if(AppSession.getUsuarioLogado().getNome() == null) {
			Optional<Usuario> usuarioSessao = responseUsers.stream().filter(us -> AppSession.getUsuarioLogado().getId() == us.getId()).findAny();
			if(usuarioSessao.isPresent()) {
				AppSession.getUsuarioLogado().setNome(usuarioSessao.get().getNome());
			}
		}
		
		List<Usuario> usuariosDeslogados = usuarios.stream().filter(u -> !u.equals(USUARIO_TODOS) && !responseUsers.contains(u))
				.collect(Collectors.toList());
		usuarios.removeAll(usuariosDeslogados);
		
		responseUsers.stream().filter(u -> !usuarios.contains(u))
			.forEach(novoUsuario -> usuarios.add(novoUsuario));
	}

	private List<Usuario> getUsersFromResponse(String[] splited) {
		List<Usuario> responseUsers = new ArrayList<>();
		// Um usu�rio a cada loop
		for (int i = 0; i < splited.length; i += 3) {
			Usuario u = new Usuario();
			u.setId(Integer.parseInt(splited[i]));
			u.setNome(splited[i + 1]);
			u.setWins(Integer.parseInt(splited[i + 2]));
			responseUsers.add(u);
		}
		return responseUsers;
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
			//Mensagens enviadas pelo usu�rio da sess�o j� est�o adicionadas.
			if(userID == AppSession.getUsuarioLogado().getId()) {
				return null;
			}
			Optional<Usuario> filteredList = usuarios.stream().filter(u -> u.getId() == userID).findAny();

			// Adiciona a mensagem referenciado ao usu�rio encontrado, caso n�o encontre o
			// usu�rio, vincula ao usu�rio "Todos"
			mensagens.add(new Message(filteredList.orElse(USUARIO_TODOS), splited[1]));

			return mensagens;
		}
		return null;
	}

	public ListProperty<Message> getMessageProperty() {
		return this.mensagens;
	}

	public ListProperty<Usuario> getUsuarioProperty() {
		return this.usuarios;
	}

	public void sendMessage(Message message) {
		Connection.doConnection(TipoConexao.UDP, "SEND MESSAGE", message.getId(), message.getMessage());
		message.setUsuario(AppSession.getUsuarioLogado());
		mensagens.add(message);
	}

}
