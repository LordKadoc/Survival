package fr.lordkadoc.launcher;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.lordkadoc.server.Client;
import fr.lordkadoc.server.ClientSocket;
import fr.lordkadoc.server.Server;
import fr.lordkadoc.server.SocketMessage;

public class ConnectionPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8837782512585902651L;
	
	private JTextField address, port;
	
	private JButton create, join;
	
	public ConnectionPanel(){
		this.init();
	}

	private void init() {
			
		JPanel panel = new JPanel(new GridLayout(3,2,10,10));
		this.setLayout(new BorderLayout());
		
		this.address = new JTextField("localhost",20);
		this.port = new JTextField("10000",20);
		this.create = new JButton("Create");
		this.join = new JButton("Join");
		this.create.addActionListener(this);
		this.join.addActionListener(this);
		
		panel.add(new JLabel("Adresse serveur : "));
		panel.add(address);
		panel.add(new JLabel("Port du serveur : "));
		panel.add(port);
		panel.add(create);
		panel.add(join);
		
		this.add(panel,BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(create)){
			int p = getPort();
			createServer(p);
			joinServer(address.getText(), p);
		}else if(e.getSource().equals(join)){
			int p = getPort();
			joinServer(address.getText(), p);
		}
	}
	
	public int getPort(){
		try{
			return Integer.parseInt(port.getText());
		}catch(NumberFormatException ex){
			port.setText("10000");
			return 10000;
		}
	}
	
	public void createServer(int port){
		new Server(port);
	}
	
	public void joinServer(String address, int port){
		try {
			Client client = new Client(new ClientSocket(new Socket(address,port)));
			client.sendMessage(new SocketMessage("Connection", null));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
