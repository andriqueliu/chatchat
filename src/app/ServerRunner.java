package app;

import server.ChatChatServer;

public class ServerRunner {
	
	public static void main(String[] args) {
		ChatChatServer server = new ChatChatServer(1337);
		server.startServer();
	}
}
