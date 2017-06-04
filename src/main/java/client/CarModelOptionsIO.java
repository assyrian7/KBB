/*
*	Paul Badalian
*	CIS 35B
*	Lab 5
*	Due: 3/10/17
*	Submitted: 3/10/17 
*/

package client;

import util.FileIO;
import adapter.BuildAuto;
import adapter.CreateAuto;

public class CarModelOptionsIO {

	private Client client;
	private FileIO fileIO;
	private String input;
	
	public CarModelOptionsIO(String input){
		this.input = input;
		fileIO = new FileIO();
	}
	
	public void loadAuto(){
		String fileName = input.substring(input.indexOf(' ') + 1, input.length());
		String fileType = fileName.substring(fileName.indexOf('.') + 1, fileName.length());
		CreateAuto createAuto = new BuildAuto();
		createAuto.buildAuto(fileName, fileType);
	}
	
}
