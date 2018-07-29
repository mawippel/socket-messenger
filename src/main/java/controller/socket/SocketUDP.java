package controller.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Optional;

/**
 * Comunicação via UDP
 * 
 * @author matheushr97
 */
public class SocketUDP extends AbstractSocket {

	@Override
	protected Optional<String> doConnection(String comando) {
		try {
			// Coloca os dados em um buffer
			byte[] s = new byte[comando.length()];
			s = comando.getBytes("UTF-8");

			// Prepara um pacote com o buffer e as informações do destinatário
			InetAddress ip = InetAddress.getByName("larc.inf.furb.br");
			DatagramPacket pack = new DatagramPacket(s, s.length, ip, 8000);

			// Cria um socket UDP e envia o pacote para localhost:8000
			DatagramSocket socket = new DatagramSocket();
			socket.send(pack);

			// Encerra o socket
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Optional.empty();
	}

}
