package gui;

import java.util.Optional;

import controller.MessageController;
import controller.schedule.ScheduleController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Message;
import model.Usuario;

public class SocketView {
	
	@FXML
	private ListView<Message> mensagensListView;
	
	@FXML
	private ListView<Usuario> usuariosListView;
	
	@FXML
	private TextField txtMensagem;
	
	@FXML
	private ComboBox<Usuario> comboDestinatario;
	
	private MessageController mc;
	
	@FXML
	public void initialize() {
		this.mc = MessageController.getInstance();
		bind();
		ScheduleController.getInstance().start();
	}

	private void bind() {
		mensagensListView.itemsProperty().bind(mc.getMessageProperty());
		usuariosListView.itemsProperty().bind(mc.getUsuarioProperty());
		comboDestinatario.itemsProperty().bind(mc.getUsuarioProperty());
	}
	
	@FXML
	public void mensagemKeyPressed(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			Usuario destinatario = Optional.ofNullable(comboDestinatario.getSelectionModel().getSelectedItem()).orElse(mc.USUARIO_TODOS);
			mc.sendMessage(new Message(destinatario, txtMensagem.getText()));
			txtMensagem.clear();
		}
		
	}

}
