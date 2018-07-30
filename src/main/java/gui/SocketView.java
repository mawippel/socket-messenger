package gui;

import controller.MessageController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Message;

public class SocketView {
	
	@FXML
	private ListView<Message> mensagensListView;
	
	@FXML
	public void initialize() {
		mensagensListView.itemsProperty().bind(MessageController.getInstance().getMessageProperty());
	}

}
