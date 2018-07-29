package model;

public class Usuario {

	private int id;
	private String nome;
	private int wins;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getWins() {
		return wins;
	}
	
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append(getId()).append(" - ").append(getNome()).append(" - ").append(getWins()).toString();
	}
	
}
