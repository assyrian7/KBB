/*
*	Paul Badalian
*	CIS 35B
*	Lab 5
*	Due: 3/10/17
*	Submitted: 3/10/17 
*/

package driver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import server.BuildCarModelOptions;
import server.Server;
import util.FileIO;

public class DriverServer {

	public static void main(String[] args){
		
		FileIO f = new FileIO();
		Properties p = f.loadProperties("res/properties.prop");
		BuildCarModelOptions carBuild = new BuildCarModelOptions(p);
		
		try {
			ServerSocket serverSocket = new ServerSocket(4040);
			while(true){
				Socket socket = serverSocket.accept();
				System.out.println("Client connecting");
				Server server = new Server(socket);
				if(server.shouldExit()){
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
