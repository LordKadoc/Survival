package fr.lordkadoc.server;

import java.io.Serializable;

public class SocketMessage implements Serializable{
	
	private final String header;
	
	private final Object content;
	
	public SocketMessage(String header, Object content){
		this.header = header;
		this.content = content;
	}
	
	public SocketMessage(Object content){
		this.header = content.getClass().getName().substring(content.getClass().getName().lastIndexOf('.')+1);
		this.content = content;
	}

	public String getHeader() {
		return header;
	}
	
	public Object getContent() {
		return content;
	}
	
	@Override
	public String toString(){
		return header + " : " + content;
	}

}
