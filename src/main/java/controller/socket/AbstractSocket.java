package controller.socket;

import java.util.Optional;

import controller.AppSession;
import model.Usuario;

/**
 * Abstra��o da parte de comunica��o
 * 
 * @author matheushr97
 */
public abstract class AbstractSocket {

	/**
	 * Efetua a conex�o e retorna a resposta(caso exista)
	 * @param comando
	 * @param parametros
	 */
	public final Optional<String> doConnection(String comando, Object... parametros) {
		String socketCommands = montaComando(comando, parametros);
		return doConnection(socketCommands);
	}

	protected abstract Optional<String> doConnection(String comando);

	private static String montaComando(String comando, Object... parametros) {
		StringBuilder sb = new StringBuilder(comando);
		Usuario usuarioLogado = AppSession.getUsuarioLogado();
		sb.append(adicionarParametros(usuarioLogado.getId(), usuarioLogado.getSenha()));
		
		if (parametros != null && parametros.length > 0) {
			sb.append(adicionarParametros(parametros));
		}

		return sb.toString();
	}

	/**
	 * Monta string dos par�metros separados por ":"
	 */
	private static String adicionarParametros(Object... params) {
		StringBuilder sb = new StringBuilder();
		for (Object param : params) {
			sb.append(param).append(":");
		}
		return sb.substring(0, sb.length() - 1);
	}

}
