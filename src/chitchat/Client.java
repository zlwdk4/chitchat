package chitchat;


import java.io.IOException;
import java.net.Socket;

public class Client extends Thread {
	
	String theMsg, hostName;
	int portNumber;
	
	
	public Client(String aMessage, String aHostName, int aPortNumber) {
		this.theMsg = aMessage;
		this.hostName = aHostName;
		this.portNumber = aPortNumber;
	}
	
	@Override
	public void run() {
		try {
			Socket s = new Socket(hostName, portNumber);
			s.getOutputStream().write(theMsg.getBytes());
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}