package app;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ChatChatServer {
	
	private Set<String> usernames = new HashSet<String>();
	private ArrayList<PrintWriter> serverOut = new ArrayList<PrintWriter>();
	
	public ChatChatServer() {
		
	}
}
