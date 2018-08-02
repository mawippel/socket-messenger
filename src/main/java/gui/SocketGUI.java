package gui;

import controller.schedule.ScheduleController;
import gui.manager.StageManager;
import gui.manager.Tela;
import javafx.application.Application;
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
		StageManager.abrirTela(Tela.AUTENTICACAO);
	}
	
	@Override
	public void stop() throws Exception {
		ScheduleController.getInstance().stop();
	}
	
}
