package controller;

import model.Usuario;

public class AppSession {

	/**
	 * Usu�rio autenticado da aplica��o
	 */
	private static Usuario usuarioLogado;

	public static Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public static void setUsuarioLogado(Usuario usuarioLogado) {
		AppSession.usuarioLogado = usuarioLogado;
	}

}
