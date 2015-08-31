package fr.lordkadoc.server;

import java.io.IOException;
import java.util.Observable;

import fr.lordkadoc.launcher.Global;

public class Client extends Observable{
	
	private ClientSocket clientSocket;
	

	public Client(ClientSocket clientSocket){
		this.clientSocket = clientSocket;
		this.receiveServerMessage();
	}
	
	public void sendMessage(Object message){
		try {
			clientSocket.getOos().writeObject(message);
			clientSocket.getOos().flush();
			clientSocket.getOos().reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void receiveServerMessage(){
		
		Runnable rn = new Runnable(){

			@Override
			public void run() {
				
				Object message;
				
				while(true){
					try {
						message = clientSocket.getOis().readObject();
						try{
							onSocketMessageReception((SocketMessage)message);
						}catch(ClassCastException e){
							onRegularMessageReception(message);
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		};
		
		new Thread(rn).start();		
		
	}
	
	public void onRegularMessageReception(Object message){
		System.out.println("<Server>" + message);
	}
	
	public void onSocketMessageReception(SocketMessage message){
		if(message.getHeader().equals("Connection")){
			Global.launcher.showGame(this);
			Global.launcher.revalidate();
			Global.launcher.pack();
			Global.launcher.setLocationRelativeTo(null);
			addObserver(Global.launcher.getTerrainVue());
		}else if(message.getHeader().equals("PlayerUpdate")){
			notifyObservers(message.getContent());
		}
	}
	
	@Override
	public void notifyObservers(Object content){
		this.setChanged();
		super.notifyObservers(content);
	}
	
	public ClientSocket getClientSocket(){
		return clientSocket;
	}

}
