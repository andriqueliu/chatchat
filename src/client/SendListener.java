package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendListener implements ActionListener {
	
	private ChatChatClient client;
	
	public SendListener(ChatChatClient client) {
		this.client = client;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!client.getTextFieldText().isEmpty()) {
			client.sendMessage(client.getTextFieldText());
			client.clearTextField();
		}
	}
}
