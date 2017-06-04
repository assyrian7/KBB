/*
*	Paul Badalian
*	CIS 35B
*	Lab 5
*	Due: 3/10/17
*	Submitted: 3/10/17 
*/
package exception;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Fix100 {
	
	public Fix100(){
		
	}
	
	//Fix the error for a missing cars file
	public void fix1(){
		File file = new File("res/cars.dat");
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void fix1(String fileName){
		File file = new File(fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Fix the error for a missing log file
	public void fix2(){
		File file = new File("res/log.dat");
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Fix the error for a missing serialize file
	public void fix3(){
		File file = new File("res/serialize.dat");
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Fix the error for missing car prices
	public void fix4(){
		try {
			Scanner reader = new Scanner(new File("res/cars.dat"));
			Scanner input = new Scanner(System.in);
			StringBuffer fileData = new StringBuffer();
			String line;
			while(reader.hasNext()){
				line = reader.nextLine();
				if(line.charAt(0) != '\t'){
					String[] carElems = line.split(" ");
					System.out.print("Enter price for the " + carElems[0] + ": ");
					float price = input.nextFloat();
					line = line.replace(carElems[0], carElems[0] + " " + price);
				}
				fileData.append(line + '\n');
			}
			reader.close();
			input.close();
			FileWriter writer = new FileWriter("res/cars.dat");
			writer.write(fileData.toString());
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
