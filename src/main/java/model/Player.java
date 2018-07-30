package model;

import controller.PlayerStatus;

public class Player extends Usuario {

	private PlayerStatus status;

	public Player(int id, PlayerStatus status) {
		super(id);
		this.status = status;
	}

	public PlayerStatus getStatus() {
		return status;
	}

	public void setStatus(PlayerStatus status) {
		this.status = status;
	}
	
}
