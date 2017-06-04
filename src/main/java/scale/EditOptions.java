/*
*	Paul Badalian
*	CIS 35B
*	Lab 5
*	Due: 3/10/17
*	Submitted: 3/10/17 
*/
package scale;

import adapter.ProxyAutomobile;

public class EditOptions extends ProxyAutomobile implements Runnable{
	
	private int ops;
	private String[] input;
	private Thread t;
	private boolean beingUsed;
	private boolean sync;
	private EditOptionsThread operations;
	
	//Construct 
	public EditOptions(int ops, String[] input, boolean sync){
		this.ops = ops;
		beingUsed = false;
		this.input = input;
		this.sync = sync;
		operations = new EditOptionsThread();
		t = new Thread(this);
		t.start();
	}
	
	//Executes when thread starts
	public void run(){
		switch(ops){
		case 1: op1(sync); break;
		}
		System.out.println("Thread: " + t.getId() + " is done");
		t = null;
	}
	
	
	public void op1(boolean sync){
		
		if(sync) operations.op1a();
		else operations.op1b();
		
	}
	
	//Helper class for edit operations
	public class EditOptionsThread{
		
		public synchronized void op1a(){
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
			updateOptionSetName(input[0], input[1], input[2]);
			beingUsed = false;
			notifyAll();
		}
		
		public void op1b(){
			updateOptionSetName(input[0], input[1], input[2]);

		}
		
	}
	
	

}
