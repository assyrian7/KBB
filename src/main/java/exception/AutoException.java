/*
*	Paul Badalian
*	CIS 35B
*	Lab 5
*	Due: 3/10/17
*	Submitted: 3/10/17 
*/
package exception;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AutoException extends Exception{

	private int errno;
	private String errmsg;
	
	//Default constructor
	public AutoException(){
		super();
		errno = -1;
		errmsg = "Not provided";
		log();
	}
	
	//Construct with an error number
	public AutoException(int errno){
		super();
		this.errno = errno;
		errmsg = "Not provided";
		log();
	}
	
	//Construct with an error message
	public AutoException(String errmsg){
		super();
		this.errmsg = errmsg;
		errno = -1;

		log();
	}
	
	//Construct with and error number and error message
	public AutoException(int errno, String errmsg){
		super();
		this.errno = errno;
		this.errmsg = errmsg;
		log();
	}
	
	//Get error number
	public int getErrno(){
		return errno;
	}
	
	//Get error message
	public String getErrmsg(){
		return errmsg;
	}
	
	//Log the exception details to the log file
	public void log(){
		FileWriter fileWriter;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		StringBuffer fileData = new StringBuffer();
		try {
			Scanner fileReader = new Scanner(new File("res/log.dat"));
			while(fileReader.hasNext()){
				fileData.append(fileReader.nextLine() + '\n');
			}
			fileReader.close();
			fileWriter = new FileWriter("res/log.dat");
			fileWriter.write(fileData.toString() + dateFormat.format(date) + " Errno: " + errno + " " + errmsg);
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Fix the exception error based on the error number
	/*
	public void fix(){
		
		Fix100 fix = new Fix100();
		
		switch(errno){
		case 1:
			fix.fix1();
			break;
		case 2:
			fix.fix2();
			break;
		case 3:
			fix.fix3();
			break;
		case 4:
			fix.fix4();
			break;
		default:
			break;
		}
	}
	
	//Fix the exception with a custom error number
	public void fix(int errno){
		
		Fix100 fix = new Fix100();
		
		switch(errno){
		case 1:
			fix.fix1();
			break;
		case 2:
			fix.fix2();
			break;
		default:
			break;
		}
	}
	*/
}
