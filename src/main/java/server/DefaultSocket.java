/*
*	Paul Badalian
*	CIS 35B
*	Lab 5
*	Due: 3/10/17
*	Submitted: 3/10/17 
*/

package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class DefaultSocket extends Thread{
	
	private BufferedReader reader;
    private PrintWriter writer;
    private ObjectInputStream objReader;
    private ObjectOutputStream objWriter; 
    private Socket sock;
    
    public abstract boolean openConnection();
	public abstract void setStreams();
	public abstract void handleSession();
    public abstract void closeSession();
    public abstract void run();
    
    public BufferedReader getReader() {
		return reader;
	}
	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}
	public PrintWriter getWriter() {
		return writer;
	}
	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}
	public ObjectInputStream getObjReader() {
		return objReader;
	}
	public void setObjReader(ObjectInputStream objReader) {
		this.objReader = objReader;
	}
	public ObjectOutputStream getObjWriter() {
		return objWriter;
	}
	public void setObjWriter(ObjectOutputStream objWriter) {
		this.objWriter = objWriter;
	}
	public Socket getSock() {
		return sock;
	}
	public void setSock(Socket sock) {
		this.sock = sock;
	}
	public boolean isConnected(){
		return sock.isConnected();
	}
}
