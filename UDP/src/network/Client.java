package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import io.OutputHandler;
import main.Application;
import user.User;
import user.UserManager;

public class Client {

	public static void sendBroadcastMessage(String json) {
		try {
			DatagramSocket socket = new DatagramSocket();

			DatagramPacket packet = new DatagramPacket(json.getBytes(), json.getBytes().length, InetAddress.getByName(Application.getBroadcastIp()), Application.getPort());

			OutputHandler.log("Sending broadcast: " + json + " to: " + Application.getBroadcastIp());
			
			socket.setBroadcast(true);
			
			socket.send(packet);
			socket.close();
		} catch (Exception e) {
			OutputHandler.error("Um erro ocorreu", e);
		}
	}

	public static void sendMulticastMessage(String json) {
		for (User user : UserManager.getArray()) {
			sendMessageToIp(user.getAddress(), json);
		}
	}

	public static void sendMessageToIp(String ip, String json) {
		try {
			DatagramSocket socket = new DatagramSocket();

			DatagramPacket packet = new DatagramPacket(json.getBytes(), json.getBytes().length, InetAddress.getByName(ip), Application.getPort());

			OutputHandler.log("Sending: " + json + " to: " + ip);
			

			socket.send(packet);
			socket.close();
		} catch (Exception e) {
			OutputHandler.error("Um erro ocorreu", e);
		}
	}

}
