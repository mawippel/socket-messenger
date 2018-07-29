package model;

public class Message {
	
	private int id;
	private String message;

	public Message(int id, String message) {
		this.id = id;
		this.message = message;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Retorna <code>true</code> se for uma mensagem de Servidor, caso contrário, retorna <code>false</code>
	 */
	public boolean isServerMessage() {
		return getId() == 0 ? true : false;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append(getId()).append(" - ").append(getMessage()).toString();
	}
	
}
