package controller;

public enum PlayerStatus {

	IDLE("Aguardando"),
	PLAYING("Jogando"),
	GETTING("Pedindo cartas"),
	WAITING("Aguardando final da rodada");
	
	private String denominacao;

	private PlayerStatus(String denominacao) {
		this.denominacao = denominacao;
	}

	public String getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}
	
	public static PlayerStatus getPlayerStatusByName(String name) {
		if (IDLE.name().equals(name)) {
			return IDLE;
		} else if (PLAYING.name().equals(name)) {
			return PLAYING;
		} else if (GETTING.name().equals(name)) {
			return GETTING;
		} else if (WAITING.name().equals(name)) {
			return WAITING;
		}
		return null;
	}
	
}
