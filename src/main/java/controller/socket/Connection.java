package controller.socket;

import java.util.Optional;

/**
 * Façade para efetuar conexões
 * 
 * @author matheushr97
 */
public class Connection {

	/**
	 * Efetua a conexão
	 * @return resposta da conexão
	 */
	public static Optional<String> doConnection(TipoConexao tipoConexao, String comando) {
		return doConnection(tipoConexao, comando, null);
	}

	/**
	 * Efetua a conexão
	 * @return resposta da conexão
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
			throw new IllegalArgumentException("Tipo de conexão inválida");
		}
	}



}
