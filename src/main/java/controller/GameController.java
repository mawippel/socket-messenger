package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controller.socket.Connection;
import controller.socket.TipoConexao;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import model.Message;
import model.Player;
import model.Usuario;

public class GameController {

	private static GameController instance;
	private static ListProperty<Player> players;

	private GameController() {
		players = new SimpleListProperty<Player>(FXCollections.observableArrayList());
	}

	public static GameController getInstance() {
		return instance == null ? instance = new GameController() : instance;
	}
	
	public List<Player> getPlayers() {
		Optional<String> response = Connection.doConnection(TipoConexao.TCP, "GET PLAYERS");
		if (response.isPresent()) {
			String[] splited = response.get().split(":");

			// Um usuário a cada loop
			for (int i = 0; i < splited.length; i += 2) {
				players.add(new Player(Integer.parseInt(splited[i]), PlayerStatus.getPlayerStatusByName(splited[i + 1])));
			}
			
			return players;
		}
		return null;
	}
	
}
