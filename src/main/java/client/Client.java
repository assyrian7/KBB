/*
*	Paul Badalian
*	CIS 35B
*	Lab 5
*	Due: 3/10/17
*	Submitted: 3/10/17 
*/

package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

import model.Automobile;
import server.DefaultSocket;

public class Client extends DefaultSocket{

    private String strHost;
    private int iPort;
    private String input;
    private Object objIn;
    private volatile Object response;
    private boolean received;
    
	public Client(String strHost, int iPort, String input, Object objIn){
    	this.strHost = strHost;
    	this.iPort = iPort;
    	this.input = input;
    	this.objIn = objIn;
    	response = null;
    	received = false;
    	start();
    }

	@Override
	public boolean openConnection() {
		// TODO Auto-generated method stub
		try {
			setSock(new Socket(strHost, iPort));
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
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
		String fromServer = null;
		String fromUser = null;
		try {
			while((fromServer = getReader().readLine()) != null){
				if(fromServer.equals("bye")){
					break;
				}
				fromUser = input;
				if(!fromUser.equals(null)){
					getWriter().println(fromUser);
					handleInput(fromUser);
				}
				else{
					getWriter().println("");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void handleInput(String input){
		if(input.equals("load")){
			if(objIn instanceof Properties){
				try {
					getObjWriter().writeObject((Properties)objIn);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if(input.equals("list")){
			String list = null;
			try {
				list = (String)getObjReader().readObject();
			} catch (ClassNotFoundException e) {
				//TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e){
				e.printStackTrace();
			}
			response = list;
			//System.out.println("List received " + response);
			received = true;
		}
		else if(input.equals("get")){
			Automobile auto = new Automobile();
			getWriter().println((String)objIn);
			auto = null;
			try {
				auto = (Automobile)getObjReader().readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e){
				e.printStackTrace();
			}
			response = auto;
			//System.out.println("List received " + response);
			received = true;
		}
		input = null;
	}
	
	@Override
	public void closeSession() {
		// TODO Auto-generated method stub
		try {
			setWriter(null);
			setReader(null);
			setObjWriter(null);
			setObjReader(null);
			getSock().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(openConnection()){
			setStreams();
			handleSession();
			closeSession();
		}
	}
	
	public Object getResponse() {
		while(response == null){}
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}
	
	public String getStrHost() {
		return strHost;
	}

	public void setStrHost(String strHost) {
		this.strHost = strHost;
	}

	public int getiPort() {
		return iPort;
	}

	public boolean isReceived() {
		return received;
	}

	public void setReceived(boolean received) {
		this.received = received;
	}

	public void setiPort(int iPort) {
		this.iPort = iPort;
	}

}
