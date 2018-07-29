package controller.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Optional;

/**
 * Comunicação via TCP
 * 
 * @author matheushr97
 */
public class SocketTCP extends AbstractSocket {

	@Override
	protected Optional<String> doConnection(String comando) {
		try {
			// Cria um socket TCP para conexão
			Socket sock = new Socket("larc.inf.furb.br", 1012);

			// Coloca os dados em um buffer e envia para o servidor
			DataOutputStream d = new DataOutputStream(sock.getOutputStream());
			d.write(comando.getBytes("UTF-8"));

			// Prepara um buffer para receber a resposta do servidor
			InputStreamReader s = new InputStreamReader(sock.getInputStream());
			BufferedReader rec = new BufferedReader(s);

			// Lê os dados enviados pela aplicação servidora
			String rBuf = rec.readLine();
			sock.close();
			return Optional.ofNullable(rBuf);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}

}
