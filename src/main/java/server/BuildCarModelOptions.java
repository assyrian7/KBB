/*
*	Paul Badalian
*	CIS 35B
*	Lab 5
*	Due: 3/10/17
*	Submitted: 3/10/17 
*/

package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;

import adapter.ProxyAutomobile;

public class BuildCarModelOptions extends ProxyAutomobile implements Runnable, AutoServer{

	private int ops;
	private Thread t;
	private boolean beingUsed;
	private boolean sync;
	private Properties props;
	
	public BuildCarModelOptions(Properties props){
		this.props = props;
		beingUsed = false;
		ops = 1;
		t = new Thread(this);
		t.start();
	}
	
	@Override
	public synchronized void acceptProperties(Properties props) {
		
		synchronized(this){
			while(beingUsed){
				try{
					System.out.println("Thread: " + t.getId() + " is waiting");
					wait();
				}
				catch(InterruptedException e){
					System.out.println("Thread: " + t.getId() + " is about to update");
				}
			}
			
			beingUsed = true;
			super.acceptProperties(props);
			beingUsed = false;
			notifyAll();
		}
		
		//super.acceptProperties(props);
	}

	public void run() {
		// TODO Auto-generated method stub
		switch(ops){
		case 1:
			acceptProperties(props);
		}
	}

}
