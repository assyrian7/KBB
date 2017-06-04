/*
*	Paul Badalian
*	CIS 35B
*	Lab 5
*	Due: 3/10/17
*	Submitted: 3/10/17 
*/

package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Properties;

import model.Automobile;
import adapter.BuildAuto;

public class Server extends DefaultSocket{
	
	private volatile String exiting;
	
	public Server(Socket sock){
		//this.sock = sock;
		exiting = null;
		setSock(sock);
		start();
	}
	
	@Override
	public boolean openConnection() {
		// TODO Auto-generated method stub
		//System.out.println("Client connected: " + sock.isConnected());
		//return sock.isConnected();
		return isConnected();
	}

	@Override
	public void setStreams() {
		// TODO Auto-generated method stub
		try {
			setWriter(new PrintWriter(getSock().getOutputStream(), true));
			setReader(new BufferedReader(new InputStreamReader(getSock().getInputStream())));
			setObjWriter(new ObjectOutputStream(getSock().getOutputStream()));
			setObjReader(new ObjectInputStream(getSock().getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void handleSession() {
		// TODO Auto-generated method stub
		String input = "";
		try {
			getWriter().println("");
			while((input = getReader().readLine()) != null){
				handleInput(input);
				getWriter().write("");
				if(input.equals("bye")){
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void closeSession() {
		try {
			setReader(null);
			setWriter(null);
			setObjReader(null);
			setObjWriter(null);
			getSock().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		if(openConnection()){
			System.out.println("Client connected on Thread #" + getId());
			setStreams();
			handleSession();
			System.out.println("Session handled on Thread #" + getId());
			closeSession();
		}
	}

	public void handleInput(String input){
		if(input.equals("load")){
			exiting = "stay";
			loadAuto();
			closeClientConnection();
		}
		else if(input.equals("list")){
			exiting = "stay";
			sendList();
			closeClientConnection();
		}
		else if(input.equals("get")){
			exiting = "stay";
			sendAuto();
			closeClientConnection();
		}
		else if(input.equals("quit")){
			exiting = "quit";
			closeClientConnection();
		}
	}
	
	public synchronized void loadAuto(){
		Properties props = null;
		while(props == null){
			try {
				props = (Properties)(getObjReader().readObject());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		BuildCarModelOptions ops = new BuildCarModelOptions(props);
		//ops.acceptProperties(props);
	}
	
	public synchronized void sendList(){
		AutoServer getAuto = new BuildAuto();
		String list = getAuto.getList();
		try {
			getObjWriter().writeObject(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void sendAuto(){
		String autoName = null;
		while(autoName == null){
			try {
				autoName = getReader().readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		AutoServer getAuto = new BuildAuto();
		Automobile auto = getAuto.getAuto(autoName);
		try {
			getObjWriter().writeObject(auto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeClientConnection(){
		getWriter().println("bye");
	}
	
	public boolean shouldExit() {
		while(exiting == null){}
		if(exiting.equals("quit")) return true;
		return false;
	}

}
