package fr.lordkadoc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import fr.lordkadoc.entities.Player;
import fr.lordkadoc.terrain.Terrain;

public class Server implements Observer{
	
	private int maxPlayers;

	private ServerSocket socket;
	
	private Map<ClientSocket,Player> players;
	
	private Terrain terrain;
	
	public Server(int port){
		try {
			this.socket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.maxPlayers = 20;
		this.players = new HashMap<ClientSocket,Player>();
		this.acceptConnections();
		this.createGame();
	}

	private void acceptConnections() {
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				ClientSocket clientSocket;
				while(players.size() < maxPlayers){
					try {
						clientSocket = new ClientSocket(socket.accept());
						new MessageReceptionThread(Server.this, clientSocket).start();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		new Thread(r).start();
		
	}
	
	public void sendMessage(ClientSocket clientSocket, Object message){
		try {
			clientSocket.getOos().writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void broadcastMessage(Object message){
		for(ClientSocket clientSocket : players.keySet()){
			sendMessage(clientSocket,message);
		}
	}
	
	public void onRegularMessageReception(ClientSocket clientSocket, Object message){
		System.out.println(message);
	}
	
	public void onSocketMessageReception(ClientSocket clientSocket, SocketMessage message){
		if(message.getHeader().equals("Connection")){
			players.put(clientSocket, terrain.addPlayer());
			sendMessage(clientSocket, new SocketMessage("Connection",null));
		}
	}
	
	public void createGame(){
		this.terrain = new Terrain();
		this.terrain.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		for(ClientSocket clientSocket : players.keySet()){
			sendMessage(clientSocket, new SocketMessage(new PlayerUpdate(terrain, players.get(clientSocket), 320)));
		}
	}
	
}
