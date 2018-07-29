package controller.socket;

import java.util.Optional;

/**
 * Fa�ade para efetuar conex�es
 * 
 * @author matheushr97
 */
public class Connection {

	/**
	 * Efetua a conex�o
	 * @return resposta da conex�o
	 */
	public static Optional<String> doConnection(TipoConexao tipoConexao, String comando) {
		return doConnection(tipoConexao, comando, null);
	}

	/**
	 * Efetua a conex�o
	 * @return resposta da conex�o
	 */
	public static Optional<String> doConnection(TipoConexao tipoConexao, String comando, Object... parametros) {
		AbstractSocket socket = getSocket(tipoConexao);
		return socket.doConnection(comando, parametros);
	}

	private static AbstractSocket getSocket(TipoConexao tipoConexao) {
		switch (tipoConexao) {
		case TCP:
			return new SocketTCP();
		case UDP:
			return new SocketUDP();

		default:
			throw new IllegalArgumentException("Tipo de conex�o inv�lida");
		}
	}



}
