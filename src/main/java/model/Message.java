package model;

public class Message {
	
	private String message;
	private Usuario usuario;
	
	public Message(Usuario usuario, String message) {
		this.usuario = usuario;
		this.message = message;
	}

	/**
	 * Apenas para facilitar o acesso ao ID do usu�rio.
	 * @return um int
	 */
	public int getId() {
		return this.usuario.getId();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Retorna <code>true</code> se for uma mensagem de Servidor, caso contr�rio, retorna <code>false</code>
	 */
	public boolean isServerMessage() {
		return getId() == 0 ? true : false;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append(getUsuario().getNome()).append(" - ").append(getMessage()).toString();
	}
	
}
