package gui;

import java.io.IOException;

import controller.AppSession;
import controller.MessageController;
import exception.InvalidUserException;
import gui.manager.StageManager;
import gui.manager.Tela;
import gui.util.MessageBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Usuario;

public class AutenticacaoView {

	@FXML
	private TextField txtId;

	@FXML
	private PasswordField txtSenha;

	@FXML
	public void autenticar() throws IOException, InterruptedException {
		System.out.println("Autenticando");
		Usuario usuario = new Usuario();
		usuario.setId(Integer.parseInt(txtId.getText()));
		usuario.setSenha(txtSenha.getText());
		AppSession.setUsuarioLogado(usuario);
		autentica();

	}

	public void autentica() throws InterruptedException {
		if (tentarAutenticar()) {
			StageManager.abrirTela(Tela.PRINCIPAL);
		} else {
			Thread.sleep(1000);
			try {
				MessageController.getInstance().getUsers();
				StageManager.abrirTela(Tela.PRINCIPAL);
			} catch (InvalidUserException e) {
				AppSession.setUsuarioLogado(null);
				MessageBuilder.buildErro(e.getMessage()).show();
			}
		}

	}

	/**
	 * @return <b> true caso autentique com sucesso.
	 */
	public boolean tentarAutenticar() {
		try {
			MessageController.getInstance().getUsers();
			return true;
		} catch (InvalidUserException e) {
			return false;
		}

	}

}
