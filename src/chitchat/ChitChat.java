package chitchat;


import java.awt.*;
import java.awt.event.*;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.border.Border;



public class ChitChat extends JFrame {
	private static final int LARGE_FONT_SIZE = 28;
	private static final int MEDIUM_FONT_SIZE = 20;

	// defined at the class level so addAMessage()
	//   can update it with incoming messages.
	private JPanel messagesReceivedPanel;
	private Server server;// = new Server(this, );
	private JTextArea messageTextArea;
	private JTextField ipTextField;
	private JTextField toPortNum, fromPortNum, senderNameTextField;
	private String ipAddr;
	
	public ChitChat() {
		setTitle("Chit-Chat");

		// The default layout for the content pane
		// of a JFrame is BorderLayout
		Container contentPane = getContentPane();

		// Note, to find our IP address under Windows
		//   execute ipconfig from cmd line.
		ipAddr = "localhost";
		JLabel ipLabel = new JLabel(ipAddr);
		try {
			ipAddr = Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ipLabel = new JLabel(ipAddr);
		ipLabel.setFont(new Font("SansSerif", Font.PLAIN, LARGE_FONT_SIZE));
		ipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(ipLabel,BorderLayout.NORTH);

		JComponent scrollableMessagesReceived = buildMessagesReceivedPanel();
		contentPane.add(scrollableMessagesReceived,BorderLayout.CENTER);

		JComponent sendMessagePanel = buildSendMessagePanel();
		contentPane.add(sendMessagePanel,BorderLayout.SOUTH);
	}

	private JComponent buildMessagesReceivedPanel() {
		messagesReceivedPanel = new JPanel();

		messagesReceivedPanel.setLayout(new GridBagLayout());

		//addAMessage("From:Larry @ 134.193.12.34\nHey Jimmy! What's up? Want to go play frisbee?");
		//addAMessage("From:Mindy @ 134.193.52.77\nJimmy, can you help me with my Java homework?");

		JScrollPane scrollPane = new JScrollPane(messagesReceivedPanel);

		Border titledBorder = BorderFactory.createTitledBorder("Receive");
		Border compoundBorder = BorderFactory.createCompoundBorder(
				titledBorder,
				scrollPane.getBorder());
		scrollPane.setBorder(compoundBorder);

		return scrollPane;
	}

	public void addAMessage(String name, String ipAddr, String msg) {
		GridBagConstraints constraints = new GridBagConstraints();

		// Defaults
		constraints.gridx = 0;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(2,2,2,2);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTH;

		String output = "From:" + name + " @ " + ipAddr + '\n' + msg;

		JTextArea messageTextArea = new JTextArea(output,4,20);
		messageTextArea.setEditable(false);
		messageTextArea.setLineWrap(true);
		messageTextArea.setWrapStyleWord(true);

		messageTextArea.setFont(new Font("SansSerif", Font.PLAIN, MEDIUM_FONT_SIZE));
		JScrollPane sourceScrollPane = new JScrollPane(messageTextArea);
		messagesReceivedPanel.add(sourceScrollPane,constraints);
		this.validate();
	}

	private JComponent buildSendMessagePanel() {
		JPanel messagePanel = new JPanel();

		messagePanel.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();

		// Defaults
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.fill = GridBagConstraints.NONE;
		constraints.insets = new Insets(2,2,2,2);
		constraints.anchor = GridBagConstraints.NORTHWEST;

		JLabel ipLabel = new JLabel("Receiver's IP:");
		ipLabel.setFont(new Font("SansSerif", Font.PLAIN, MEDIUM_FONT_SIZE));
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		messagePanel.add(ipLabel,constraints);

		constraints.gridy = 1;
		JLabel senderNameLabel = new JLabel("Sender's Name:");
		senderNameLabel.setFont(new Font("SansSerif", Font.PLAIN, MEDIUM_FONT_SIZE));
		messagePanel.add(senderNameLabel,constraints);

		constraints.weighty = 1;
		constraints.gridy = 2;
		JLabel messageLabel = new JLabel("Message:");
		messageLabel.setFont(new Font("SansSerif", Font.PLAIN, MEDIUM_FONT_SIZE));
		messagePanel.add(messageLabel,constraints);
		
		constraints.gridy = 3;
		JLabel toPortNumL = new JLabel("To port num: ");
		messagePanel.add(toPortNumL, constraints);
		
		constraints.gridy = 4;
		JLabel fromPortNumL = new JLabel("From port num: ");
		messagePanel.add(fromPortNumL, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		ipTextField = new JTextField("localhost",20);
		ipTextField.setFont(new Font("SansSerif", Font.PLAIN, MEDIUM_FONT_SIZE));
		messagePanel.add(ipTextField,constraints);

		constraints.gridy = 1;
		senderNameTextField = new JTextField(20);
		senderNameTextField.setFont(new Font("SansSerif", Font.PLAIN, MEDIUM_FONT_SIZE));
		messagePanel.add(senderNameTextField,constraints);
		
		constraints.gridy = 3;
		toPortNum = new JTextField("1337");
		messagePanel.add(toPortNum, constraints);
		
		constraints.gridy = 4;
		fromPortNum = new JTextField("1337");
		messagePanel.add(fromPortNum, constraints);

		constraints.gridy = 2;
		constraints.weighty = 1;
		messageTextArea = new JTextArea(5,20);
		messageTextArea.setLineWrap(true);
		messageTextArea.setWrapStyleWord(true);
		

		messageTextArea.setFont(new Font("SansSerif", Font.PLAIN, MEDIUM_FONT_SIZE));
		JScrollPane sourceScrollPane = new JScrollPane(messageTextArea);
		messagePanel.add(sourceScrollPane,constraints);


		constraints.weighty = 0;
		constraints.gridy = 5;
		constraints.fill = GridBagConstraints.NONE;
		JButton sendButton = new JButton("Send");
		
		sendButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sendActionPerformed(evt);
			}

		});
		
		JButton listenButton = new JButton("Listen");

		listenButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				listenActionPerformed(evt);
			}

		});
		
		sendButton.setFont(new Font("SansSerif", Font.PLAIN, MEDIUM_FONT_SIZE));
		messagePanel.add(sendButton,constraints);
		constraints.gridy = 6;
		listenButton.setFont(new Font("SansSerif", Font.PLAIN, MEDIUM_FONT_SIZE));
		messagePanel.add(listenButton, constraints);

		Border titledBorder = BorderFactory.createTitledBorder("Send");
		messagePanel.setBorder(titledBorder);
		return messagePanel;
	}
	
	private void listenActionPerformed(ActionEvent evt) {
		server = new Server(this, Integer.parseInt(toPortNum.getText()));
		server.start();
	}
	
	private void sendActionPerformed(java.awt.event.ActionEvent evt) {
		String msgipAndName = senderNameTextField.getText() + '\n' + ipAddr + '\n' + messageTextArea.getText();
		Client client = new Client(msgipAndName, ipTextField.getText(), Integer.parseInt(fromPortNum.getText()));
		client.start();
	}

	public static void main(String[] args) {
		JFrame frame = new ChitChat();
		frame.pack();
		frame.setVisible(true);
	}
	
}