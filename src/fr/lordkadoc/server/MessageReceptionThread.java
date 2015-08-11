package fr.lordkadoc.server;

import java.io.IOException;

public class MessageReceptionThread extends Thread{
	
	private Server server;
	
	private ClientSocket clientSocket;
	
	public MessageReceptionThread(Server server, ClientSocket clientSocket){
		this.server = server;	
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run(){
		
		Object message;
		
		while(true){
			try {
				message = clientSocket.getOis().readObject();
				try{
					server.onSocketMessageReception(clientSocket, (SocketMessage)message);
				}catch(ClassCastException e){
					server.onRegularMessageReception(clientSocket, message);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

}
