package client;

import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatChatClient {
	
	// TODO: error handling everywhere! In all classes!
	
	private int port;
	
	private JFrame chatWindow = new JFrame("Chat Application");
	private JTextArea chatArea = new JTextArea(22, 40);
	private JTextField textField = new JTextField(40);
	private JLabel blankLabel = new JLabel("          ");
	private JButton sendButton = new JButton("Send");
	
	private BufferedReader in;
	private PrintWriter out;
	
	private JLabel nameLabel = new JLabel("          ");
	
	public ChatChatClient(int port) {
		this.port = port;
		
		startGui();
	}
	
	public void startChat() throws UnknownHostException, IOException {
		String ipAddress = JOptionPane.showInputDialog(
				chatWindow,
				"Enter IP Address: ",
				"IP Address Required!",
				JOptionPane.PLAIN_MESSAGE);
		
		try (
				Socket socket = new Socket(ipAddress, port);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
			
			while (true) {
				String str = in.readLine();
				
				if (str.equals("NAMEREQUIRED")) {
					String name = JOptionPane.showInputDialog(
							chatWindow,
							"Enter a unique name:",
							"Name required!",
							JOptionPane.PLAIN_MESSAGE);
					out.println(name);
				} else if (str.equals("NAMEEXISTS")) {
					String name = JOptionPane.showInputDialog(
							chatWindow,
							"Enter another name:",
							"Name already exists!",
							JOptionPane.WARNING_MESSAGE);
					out.println(name);
				} else if (str.startsWith("NAMEACCEPTED")) {
					textField.setEditable(true);
					nameLabel.setText("You are logged in as: " + str.substring(12));
				} else {
					chatArea.append(str + "\n");
				}
			}
		}
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
		
		this.sendButton.addActionListener(new SendListener(this));
		this.textField.addActionListener(new SendListener(this));
	}
	
	public String getTextFieldText() {
		return this.textField.getText();
	}
	
	public void clearTextField() {
		this.textField.setText("");
	}
	
	public void sendMessage(String message) {
		this.out.println(message);
	}
}
