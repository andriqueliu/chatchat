package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Set;

public class ConversationHandler extends Thread {
	
	private ChatChatServer server;
	private Socket socket;
	private String username;
	private Set<String> usernames;
	
	public ConversationHandler(ChatChatServer server, Socket socket, Set<String> usernames) {
		this.server = server;
		
		this.socket = socket;
		
		// write to logs...
		
		this.usernames = usernames;
	}
	
	@Override
	public void run() {
		try (
				BufferedReader handlerIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter handlerOut = new PrintWriter(socket.getOutputStream(), true)) {
			
			handlerOut.println("NAMEREQUIRED");
			username = handlerIn.readLine();
			while (!server.storeUsername(username)) {
				handlerOut.println("NAMEEXISTS");
				username = handlerIn.readLine();
			}
			handlerOut.println("NAMEACCEPTED");
			
			server.addServerOut(handlerOut);
			
			while (true) {
				String message = handlerIn.readLine();
				
				// log...
				
				for (PrintWriter out : server.getServerOuts()) {
					out.println(username + ":" + message);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
