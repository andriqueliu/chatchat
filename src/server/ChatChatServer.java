package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ChatChatServer {
	
	// !!! Also need to remove usernames when they log off TODO
	
	private int port;
	private Set<String> usernames = new HashSet<String>();
	private ArrayList<PrintWriter> serverOuts = new ArrayList<PrintWriter>();
	
	public ChatChatServer(int port) {
		this.port = port;
	}
	
	public void startServer() {
		System.out.println("Waiting for clients...");
		
		try (ServerSocket serverSocket = new ServerSocket(this.port)) {
			
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Connection established...");
				// TODO: other information...
				
				ConversationHandler convoHandler = new ConversationHandler(this, socket, usernames);
				convoHandler.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized boolean storeUsername(String username) {
		if (!usernames.contains(username)) {
			usernames.add(username);
			return true;
		}
		return false;
	}
	
	public ArrayList<PrintWriter> getServerOuts() {
		return this.serverOuts;
	}
	
	public void addServerOut(PrintWriter serverOut) {
		this.serverOuts.add(serverOut);
	}
}
