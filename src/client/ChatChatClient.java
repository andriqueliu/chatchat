package client;

import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatChatClient {
	
	// TODO: error handling everywhere! In all classes!
	
	private JFrame chatWindow = new JFrame("Chat Application");
	private JTextArea chatArea = new JTextArea(22, 40);
	private JTextField textField = new JTextField(40);
	private JLabel blankLabel = new JLabel("          ");
	private JButton sendButton = new JButton("Send");
	
	private BufferedReader in;
	private PrintWriter out;
	
	private JLabel nameLabel = new JLabel("          ");
	
	public ChatChatClient() {
		startGui();
	}
	
	public void startChat() {
		
	}
	
	private void startGui() {
		this.chatWindow.setLayout(new FlowLayout());
		
		this.chatWindow.add(nameLabel);
		this.chatWindow.add(new JScrollPane(chatArea));
		this.chatWindow.add(blankLabel);
		this.chatWindow.add(textField);
		this.chatWindow.add(sendButton);
		
		this.chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.chatWindow.setSize(475, 500);
		this.chatWindow.setVisible(true);
		
		this.textField.setEditable(false);
		this.chatArea.setEditable(false);
		
//		this.sendButton.addActionListener(new Listener());
//		this.textField.addActionListener(new Listener());
	}
}
