/*
*	Paul Badalian
*	CIS 35B
*	Lab 5
*	Due: 3/10/17
*	Submitted: 3/10/17 
*/
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;
import java.util.Scanner;

import model.Automobile;
import exception.AutoException;

public class FileIO {

	
	private String fileName;
	private Scanner scanner;
	
	//Construct IO object with a fileName
	public FileIO(String fileName){
		this.fileName = fileName;
		try {
			scanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			File file = new File(fileName);
		}
	}
	
	public FileIO(){
		fileName = "res/cars.dat";
		try {
			scanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			File file = new File(fileName);
		}
	}
	
	//Serialize an Automotive object to a file
	public static void serializeAutomotive(Automobile car){
		
		ObjectOutputStream outObj; 
		try {
			outObj = new ObjectOutputStream(new FileOutputStream("res/serialize.dat"));
			outObj.writeObject(car);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Deserialize an Automotive object from a file
	public static Automobile deserializeAutomotive() throws AutoException{
		
		ObjectInputStream objIn;
		try {
			objIn = new ObjectInputStream(new FileInputStream("res/serialize.dat"));
			Automobile car = (Automobile) objIn.readObject();
			objIn.close();
			return car;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AutoException(3, "Couldn't find serialization file");
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Properties loadProperties(String fileName){
		
		Properties prop = new Properties();
		try {
			FileInputStream propInput = new FileInputStream(fileName);
			prop.load(propInput);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}
	
	//Create an Automotive from the class' input file using the Scanner
	public Automobile buildAutoObject() throws AutoException{
		
		
		String line = scanner.nextLine();
		if(line == null){
			scanner.close();
		}
		else{
			Automobile car;
			String[] elements = line.split(" ");
			if(elements.length < 4){
				throw new AutoException(4, "Automobile price is missing");
			}
			String make = elements[0];
			String model = elements[1];
			float price = (float)Double.parseDouble(elements[2]);
			int optionSetSize = Integer.parseInt(elements[3]);
			car = new Automobile(make, model, price, optionSetSize);
			for(int i = 0; i < car.getOptionSetSize(); i++){
				String opsetData = scanner.nextLine();
				opsetData = opsetData.replaceAll("\t", "");
				String[] opsetElements = opsetData.split(" ");
				String opName = opsetElements[0];
				int optionSize = Integer.parseInt(opsetElements[1]);
				car.setOptionSet(i, optionSize, opName);
				for(int j = 0; j < optionSize; j++){
					String optionData = scanner.nextLine();
					optionData = optionData.replaceAll("\t", "");
					String[] optionElements = optionData.split(" ");
					String optionName = optionElements[0];
					float optionPrice = (float)Double.parseDouble(optionElements[1]);
					car.setOption(i, j, optionName, optionPrice);
				}
			}
			return car;
		}
		
		return null;
	}
	
	//Close the Scanner
	public void close(){
		scanner.close();
	}
	
}
