package controller;

import model.Usuario;

public class AppSession {

	/**
	 * Usuário autenticado da aplicação
	 */
	private static Usuario usuarioLogado;

	public static Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public static void setUsuarioLogado(Usuario usuarioLogado) {
		AppSession.usuarioLogado = usuarioLogado;
	}

}
