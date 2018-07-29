package controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import model.Message;
import model.Usuario;

/**
 * Efetua o controle da parte do socket. 
 *  
 * @author marcelo.wippel
 */
public class SocketMessageController {

	private static List<Message> mensagens;
	private static SocketMessageController instance;
	
	/**
	 * 
	 */
	private SocketMessageController() {
		mensagens = new ArrayList<>();
	}
	
	public static SocketMessageController getInstance() {
		return instance == null ? instance = new SocketMessageController() : instance;
	}

	public List<Usuario> getUsers(String user, String pass) {
		Socket socket = null;
		try {
			socket = createDefaultSocket(1012);
			String response = doConnection(socket, "GET USERS", user, pass);
			
			String[] splited = response.split(":");
			List<Usuario> users = new ArrayList<>();
			
			//Um usuário a cada loop
			for (int i = 0; i < splited.length; i += 3) {
				Usuario u = new Usuario();
				u.setId(Integer.parseInt(splited[i]));
				u.setNome(splited[i + 1]);
				u.setWins(Integer.parseInt(splited[i + 2]));
				users.add(u);
			}
			
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// Encerra a conexão com o servidor
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<Message> getMessage(String user, String pass) {
		Socket socket = null;
		try {
			socket = createDefaultSocket(1012);
			String response = doConnection(socket, "GET MESSAGE", user, pass);
			
			String[] splited = response.split(":");
			
			if (response.trim().equals(":")) {
				return null;
			}
			
			mensagens.add(new Message(Integer.parseInt(splited[0]), splited[1]));
			
			return mensagens;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// Encerra a conexão com o servidor
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void sendMessage(String user, String pass, Message message) {
		DatagramSocket socket = null;
		try {
			//“SEND MESSAGE <userid1>:<passwd1>:<userid2>:<msg>” 
			String mensagem = "SEND MESSAGE " + user + ":" + pass + ":" + message.getId() + ":" + message.getMessage();
			
			// Coloca os dados em um buffer
			byte[] arrayMessage = new byte[mensagem.length()];
			arrayMessage = mensagem.getBytes("UTF-8");

			// Prepara um pacote com o buffer e as informações do destinatário
			InetAddress ip = InetAddress.getByName("larc.inf.furb.br");
			DatagramPacket pack = new DatagramPacket(arrayMessage, arrayMessage.length, ip, 1011);

			// Cria um socket UDP e envia o pacote
			socket = new DatagramSocket();
			socket.send(pack);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Encerra o socket
			if (socket != null) {
				socket.close();
			}
		}
	}
	
	private String doConnection(Socket socket, String method, String user, String pass) throws Exception {
		// Coloca os dados em um buffer e envia para o servidor
		DataOutputStream d = new DataOutputStream(socket.getOutputStream());
		String sBuf = method + user + ":" + pass + System.lineSeparator();
		d.write(sBuf.getBytes("UTF-8"));

		// Prepara um buffer para receber a resposta do servidor
		InputStreamReader s = new InputStreamReader(socket.getInputStream());
		BufferedReader rec = new BufferedReader(s);

		// Lê os dados enviados pela aplicação servidora
		return rec.readLine();
	}
	
	private Socket createDefaultSocket(int port) throws UnknownHostException, IOException {
		return new Socket("larc.inf.furb.br", port);
	}
}
