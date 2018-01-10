package app;

import java.io.IOException;
import java.net.UnknownHostException;

import client.ChatChatClient;

public class ClientRunner {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		ChatChatClient client = new ChatChatClient(1337);
		client.startChat();
	}
}
