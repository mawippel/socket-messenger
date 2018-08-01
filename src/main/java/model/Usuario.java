package model;

public class Usuario {

	private int id;
	private String senha;
	private String nome;
	private int wins;

	public Usuario() {
		// Default Constructor.
	}

	public Usuario(int id) {
		super();
		this.id = id;
	}

	public Usuario(int id, String senha) {
		super();
		this.id = id;
		this.senha = senha;
	}
	
	public Usuario(int id, String senha, String nome) {
		super();
		this.id = id;
		this.senha = senha;
		this.nome = nome;
	}

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return String.format("[%s] - %s", id, nome);
	}

}
