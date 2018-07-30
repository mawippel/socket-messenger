package gui.manager;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StageManager {

	private static Stage telaAberta;

	public static void abrirTela(Tela tela) {
		try {
			Pane root = FXMLLoader.load(StageManager.class.getResource(tela.getResource()));
			double width = root.getPrefWidth();
			double height = root.getPrefHeight();

			Scene scene = new Scene(root, width, height);
			Stage stage = new Stage();
			stage.setTitle(tela.getTitulo());
			stage.setScene(scene);
			if (telaAberta != null) {
				telaAberta.close();
			}
			stage.show();
			telaAberta = stage;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
