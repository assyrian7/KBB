/*
*	Paul Badalian
*	CIS 35B
*	Lab 5
*	Due: 3/10/17
*	Submitted: 3/10/17 
*/
package driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import model.Automobile;
import util.FileIO;
import client.CarModelOptionsIO;
import client.Client;
import client.SelectCarOption;

public class Driver {
	
	
	public static void main(String[] args){
		/*
		CreateAuto build = new BuildAuto();
		build.buildAuto();
		
		build = new BuildAuto();
		build.buildAuto();
		
		
		Scanner input = new Scanner(System.in);
		String[] inputArr = new String[3];
		//String optionSetName = "";
		//String newName = "";
		//String optionName = "";
		//float newPrice = 0.0f;
		
		System.out.print("Enter a model to change: ");
		inputArr[0] = input.next();
		System.out.print("Enter an option set name to change: ");
		inputArr[1] = input.next();
		System.out.print("Enter a new name for that option set: ");
		inputArr[2] = input.next();
		
		
		EditThread[] e = new EditThread[2];
		for(int i = 0; i < e.length; i++){
			e[i] = new BuildAuto();
		}
		for(int i = 0; i < e.length; i++){
			e[i].edit(1, inputArr, true);
		}
		/*
		UpdateAuto update = new BuildAuto();
		update.updateOptionSetName("BMW740IL", optionSetName, newName);
		
		build.printAuto("BMW740IL");
		
		
		System.out.print("\nEnter an option set name for changing price: ");
		optionSetName = input.next();
		System.out.print("Enter an option name: ");
		optionName = input.next();
		System.out.print("Enter a new price: ");
		newPrice = input.nextFloat();
		
		update.updateOptionPrice("BMW740IL", optionSetName, optionName, newPrice);
		
		build.printAuto("BMW740IL");
		input.close();
		*/
		FileIO fileIO = new FileIO();
		String input = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to the car configuration api.");
		do{
			System.out.print("Enter command (h for list of functions): ");
			try {
				input = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(input.contains("load")){
				String[] inputArgs = input.split(" ");
				CarModelOptionsIO optionsIO = new CarModelOptionsIO(inputArgs[1]);
				optionsIO.loadAuto();
			}
			else if(input.equalsIgnoreCase("list")){
				SelectCarOption options = new SelectCarOption();
				String cars = options.getList();
				String[] names = cars.split(";");
				for(int i = 0; i < names.length; i++){
					System.out.println(names[i]);
				}
			}
			else if(input.contains("get")){
				String[] inputArgs = input.split(" ");
				SelectCarOption options = new SelectCarOption();
				Automobile car = options.getAuto(inputArgs[1]);
				if(car.getName().equals("")){
					System.out.println("Car not found");
				}
				else{
					System.out.println(car.print());
				}
			}
			else if(input.equalsIgnoreCase("h")){
				printOperations();
			}
		}while(!input.equals("quit"));
		String localhostName = "";
		try {
			localhostName = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Client client = new Client(localhostName, 4040, "quit", null);
		/*
		FileIO file = new FileIO();
		AutoServer create = new BuildAuto();
		Properties props = file.loadProperties("res/properties.prop");
		create.acceptProperties(props);
		*/
	}
	
	public static void printOperations(){
		System.out.println("\tload \"filepath\"");
		System.out.println("\tlist");
		System.out.println("\tget \"carName\"");
	}
	

}
/* Test Runs

Welcome to the car configuration api.
Enter command (h for list of functions): load res/properties.prop
Enter command (h for list of functions): list
ToyotaPrius
Enter command (h for list of functions): get ToyotaPrius
Make: Toyota Model: Prius Price: 0.0
	OptionSet: Transmission Size: 2
		OptionName: Manual Price: 0.0
		OptionName: Automatic Price: 0.0
	OptionSet: Brakes Size: 2
		OptionName: Regular Price: 0.0
		OptionName: ABS Price: 0.0

Enter command (h for list of functions): quit
*/