package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Interface do usuário.
 * @author matheushr97, marcelo.wippel
 */
public class SocketGUI extends Application {

	public static void main (String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Pane root = FXMLLoader.load(getClass().getResource("/fxml/principal.fxml"));
    
        Scene scene = new Scene(root, 750, 450);
    
        stage.setTitle("FURB Messager");
        stage.setScene(scene);
        stage.show();
	}
	
}
