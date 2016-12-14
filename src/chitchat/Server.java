package chitchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server extends Thread {
	int usablePort = 1337;
	ChitChat cc;
	ServerSocket server;
	
	public Server (ChitChat gui, int portNum) {
		this.cc = gui;
		this.usablePort = portNum;
		try {
			server = new ServerSocket(portNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		Socket clientSocket;
		try {
			while ((clientSocket = server.accept()) != null) {
				InputStream is = clientSocket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				Scanner s = new Scanner(br);
				String theName = s.nextLine();
				String theIP = s.nextLine();
				String theMessage = s.nextLine();
				s.close();
				if (theMessage != null) {
					cc.addAMessage(theName, theIP, theMessage);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}