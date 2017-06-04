/*
*	Paul Badalian
*	CIS 35B
*	Lab 5
*	Due: 3/10/17
*	Submitted: 3/10/17 
*/
package adapter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import model.Automobile;
import scale.EditOptions;
import util.FileIO;
import client.Client;
import exception.AutoException;
import exception.Fix100;

public abstract class ProxyAutomobile {

	private static LinkedHashMap<String, Automobile> autoMap = new LinkedHashMap<String, Automobile>();
	private FileIO f = new FileIO();
	
	//Create an automobile object from the car data file
	public void buildAuto(){
		try {
			Automobile auto = f.buildAutoObject();
			autoMap.put(auto.getName(), auto);
		} catch (AutoException e) {
			// TODO Auto-generated catch block
			fix(e.getErrno());
		}
	}
	
	//Print autoMap
	public void printAuto(String modelName){
		Iterator<Entry<String, Automobile>> it = autoMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, Automobile> auto = (Map.Entry<String, Automobile>)it.next();
			if(auto.getKey().equals(modelName)){
				System.out.println(auto.getValue().print());
			}
		}
	}
	
	//Update an option set name for autoMap
	public void updateOptionSetName(String modelName, String optionSetName, String newName){
		Iterator<Entry<String, Automobile>> it = autoMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, Automobile> auto = (Map.Entry<String, Automobile>)it.next();
			if(auto.getKey().equals(modelName)){
				if(auto.getValue().setOptionSetName(optionSetName, newName)){
					System.out.println("Updated option set name");
				}
				else{
					System.out.println("Couldn't find option set to update");
				}
			}
		}
	}
	
	public Automobile getAuto(String auto){
		Iterator<Entry<String, Automobile>> it = autoMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, Automobile> automobile = (Map.Entry<String, Automobile>)it.next();
			if(automobile.getKey().equals(auto)){
				return automobile.getValue();
			}
		}
		return new Automobile();
	}
	
	public String getList(){
		String delimiter = ";";
		StringBuffer buffer = new StringBuffer();
		Iterator<Entry<String, Automobile>> it = autoMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, Automobile> auto = (Map.Entry<String, Automobile>)it.next();
			buffer.append(auto.getKey());
			buffer.append(delimiter);
		}
		String list = buffer.toString();
		if(list.length() > 0) return list.substring(0, list.length() - 1);
		else return "";
	}
	
	//Update an option price for autoMap
	public void updateOptionPrice(String modelName, String optionSetName, String option, float price){
		Iterator<Entry<String, Automobile>> it = autoMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, Automobile> auto = (Map.Entry<String, Automobile>)it.next();
			if(auto.getKey().equals(modelName)){
				if(auto.getValue().setOptionPrice(optionSetName, option, price)){
					System.out.println("Updated option price");
				}
				else{
					System.out.println("Couldn't find option to update");
				}
			}
		} 
	}
	
	//Edit the automobiles through threads
	public void edit(int ops, String[] input, boolean sync){
		EditOptions edit = new EditOptions(ops, input, sync);
	}
	
	public void acceptProperties(Properties props) {
		Automobile car = null;
		String CarMake = props.getProperty("CarMake");
		if(!CarMake.equals(null)){
			String CarModel = props.getProperty("CarModel");
			String CarPrice = props.getProperty("CarPrice");
			car = new Automobile(CarMake, CarModel);
			car.setPrice(Float.parseFloat(CarPrice));
			
			int i = 1;
			String option = null;
			while((option = props.getProperty("Option" + i)) != null){
				car.addOptionSet(option);
				int j = 0;
				char val = (char)('A' + j);
				String optionVal = null;
				while((optionVal = props.getProperty("OptionValue" + i + val)) != null){
					String optionPrice = props.getProperty("OptionPrice" + i + val);
					car.addOption(i - 1, optionVal, Float.parseFloat(optionPrice));
					j++;
					val = (char)('A' + j);
				}
				i++;
			}
		}
		autoMap.put(car.getName(), car);
	}
	
	public void buildAuto(String fileName, String fileType) {
		if(fileType.equals("txt")){
			f = new FileIO(fileName);
			try {
				Automobile auto = f.buildAutoObject();
				autoMap.put(auto.getName(), auto);
			} catch (AutoException e) {
				// TODO Auto-generated catch block
				fix(e.getErrno());
			}
		}
		else if(fileType.equals("prop")){
			Properties props = f.loadProperties(fileName);
			String localhostName = "";
			try {
				localhostName = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(localhostName);
			Client client = new Client(localhostName, 4040, "load", props);
		}
	}
	
	public void fix(int errno){
			
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
	}
