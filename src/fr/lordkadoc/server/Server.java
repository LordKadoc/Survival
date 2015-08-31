package fr.lordkadoc.server;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.lordkadoc.entities.Player;
import fr.lordkadoc.terrain.Terrain;

public class Server implements Observer{
	
	private int maxPlayers;

	private ServerSocket socket;
	
	private ExecutorService executor;
	
	private Map<ClientSocket,Player> players;
	
	private Terrain terrain;
	
	public Server(int port){
		try {
			this.socket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.maxPlayers = 5;
		this.executor = Executors.newFixedThreadPool(maxPlayers);
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
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				try {
					clientSocket.getOos().writeObject(message);
					clientSocket.getOos().flush();
					clientSocket.getOos().reset();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		executor.submit(r);
		
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
		}else if(message.getHeader().equals("destination")){
			players.get(clientSocket).setDestination((Point2D.Double)message.getContent());
		}
	}
	
	public void createGame(){
		this.terrain = new Terrain();
		this.terrain.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		List<ClientSocket> tmp = new ArrayList<ClientSocket>();
		tmp.addAll(players.keySet());
		for(ClientSocket clientSocket : tmp){
			PlayerUpdate upd = new PlayerUpdate(terrain, players.get(clientSocket), 320);
			sendMessage(clientSocket, new SocketMessage(upd));
		}
	}
	
}
