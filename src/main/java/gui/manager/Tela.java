package gui.manager;

public enum Tela {

	PRINCIPAL("FURB Messager", "/fxml/principal.fxml"),
	AUTENTICACAO("Autenticação", "/fxml/autenticacao.fxml");

	private String titulo;
	private String resource;

	Tela(String titulo, String resource) {
		this.titulo = titulo;
		this.resource = resource;
	}

	public String getResource() {
		return this.resource;
	}

	public String getTitulo() {
		return titulo;
	}

}
