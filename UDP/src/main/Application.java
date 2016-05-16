package main;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import listener.Dispatcher;
import listener.Listener;
import listener.MessageEvent;

import com.fasterxml.jackson.core.JsonProcessingException;

import automated.SearchRepeater;
import gui.TelaUser;
import io.InputHandler;
import io.OutputHandler;
import network.Server;
import user.User;

public class Application {

	private static InputHandler client;
	private static Thread server;
	private static Thread searcher;

	public static void main(String args[]) throws JsonProcessingException {
		client = new InputHandler();
		final TelaUser telaUser = new TelaUser(client);
		Dispatcher.getInstance().addListener(new Listener() {
			@Override
			public void recebeMensagem(MessageEvent evento) {
				telaUser.insereMensagem(evento.getMessage());
				System.out.println(evento.getMessage());
			}
        });
		server = new Thread(new Server());
		searcher = new Thread(new SearchRepeater());

		if (!Config.init(args)) {
			return;
		}
		server.start();
		searcher.start();
	}

	public static void halt() {
		server.interrupt();
		searcher.interrupt();

		System.err.println("Application halted!");
	}

	public static int getPort() {
		return Config.port;
	}

	public static User getUser() {
		return Config.user;
	}

	public static String getBroadcastIp() {
		if (Config.broadcastAddress == null) {
			try {
				Config.broadcastAddress = Config.findBroadcastAddress();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		return Config.broadcastAddress;
	}

	public static int getLogLevel() {
		return Config.logLevel;
	}
	
	public static void initConfigForTests(){
		String[] args = {};
		Config.init(args);
	}

	public static class Config {
		private static User user;
		private static int port;
		private static int logLevel;
		private static String broadcastAddress;

		private static boolean init(String[] args) {

			Config.user = new User();

			if (args.length >= 1) {
				Config.user.setAddress(args[0]);
			} else {
				OutputHandler.log("É necessário informar um ip");
				return false;
			}

			if (args.length >= 2) {
				Config.user.setNickname(args[1]);
			} else {
				Config.user.setNickname("Vader");
			}

			if (args.length >= 3) {
				Config.logLevel = Integer.parseInt(args[2]);
			} else {
				Config.logLevel = OutputHandler.VERBOSE;
			}

			if (args.length >= 4) {
				Config.port = Integer.parseInt(args[3]);
			} else {
				Config.port = 9000;
			}

			return true;
		}

		private static String findBroadcastAddress() throws SocketException {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			
			while (interfaces.hasMoreElements()) {
				NetworkInterface networkInterface = interfaces.nextElement();
				
				if (networkInterface.isLoopback())
					continue;
				
				for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
					if(interfaceAddress.getBroadcast() == null)
						continue;
					
					if(user.getAddress().equals( interfaceAddress.getAddress().getHostAddress() )){
						return interfaceAddress.getBroadcast().getHostAddress();
					}
				}
			}
			
			return "";
		}

	}
}
