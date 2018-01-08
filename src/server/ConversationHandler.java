package server;

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
	
	public ConversationHandler(ChatChatServer server, Socket socket, Set<String> usernames) {
		this.server = server;
		
		this.socket = socket;
		
		// write to logs...
		
	}
	
	@Override
	public void run() {
		try (
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
			
			out.println("NAMEREQUIRED");
			username = in.readLine();
			while (!server.storeUsername(username)) {
				out.println("NAMEEXISTS");
				username = in.readLine();
			}
			out.println("NAMEACCEPTED" + username); // TODO... fix?
			
			server.addServerOut(out);
			
			while (true) {
				String message = in.readLine();
				
				// log...
				
				for (PrintWriter serverOut : server.getServerOuts()) {
					serverOut.println(username + ":" + message);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
