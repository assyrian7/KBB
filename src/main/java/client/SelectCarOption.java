/*
*	Paul Badalian
*	CIS 35B
*	Lab 5
*	Due: 3/10/17
*	Submitted: 3/10/17 
*/

package client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import model.Automobile;

public class SelectCarOption {

	private Client client;
	
	public SelectCarOption(){
		
	}
	
	public String getList(){
		String localhostName = "";
		try {
			localhostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client = new Client(localhostName, 4040, "list", null);
		Object obj = client.getResponse();
		
		if(obj instanceof String){
			return (String)obj;
		}
		return null;
	}
	
	public Automobile getAuto(String auto){
		String localhostName = "";
		try {
			localhostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client = new Client(localhostName, 4040, "get", auto);
		Object obj = client.getResponse();

		if(obj instanceof Automobile){
			return (Automobile)obj;
		}
		return null;
	}
	
}
