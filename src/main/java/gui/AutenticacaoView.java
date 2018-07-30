package gui;

import java.io.IOException;

import controller.AppSession;
import gui.manager.StageManager;
import gui.manager.Tela;
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
	public void autenticar() throws IOException {
		System.out.println("Autenticando");
		Usuario usuario = new Usuario();
		usuario.setId(Integer.parseInt(txtId.getText()));
		usuario.setSenha(txtSenha.getText());
		AppSession.setUsuarioLogado(usuario);
		
		StageManager.abrirTela(Tela.PRINCIPAL);
	}

}
