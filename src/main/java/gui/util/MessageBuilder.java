package gui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MessageBuilder {

	public static Alert buildErro(String texto) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro");
		alert.setHeaderText("Ocoreu um erro");
		alert.setContentText(texto);
		return alert;
	}

}
