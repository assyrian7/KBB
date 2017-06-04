/*
*	Paul Badalian
*	CIS 35B
*	Lab 5
*	Due: 3/10/17
*	Submitted: 3/10/17 
*/
package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Opset implements Serializable{

	private String name;
	private int optionSize;
	private ArrayList<Option> options;
	private Option choice;
	
	public Opset(){
		optionSize = 0;
		name = "";
		options = new ArrayList<Option>();
		choice = new Option();
	}
	
	//Construct option set with a size and initialize all Options
	public Opset(int optionSize){
		this.optionSize = optionSize;
		options = new ArrayList<Option>();
		choice = new Option();
		for(int i = 0; i < optionSize; i++){
			options.add(new Option());
		}
		name = "";
	}
	
	public Opset(String name){
		optionSize = 0;
		options = new ArrayList<Option>();
		choice = new Option();
		this.name = name;
	}
	
	//Initialize or reinitialize the options array with a new size and a name for the option set 
	protected void setOptions(){
		for(int i = 0; i < optionSize; i++){
			options.add(new Option());
		}
	}
	
	//Set the number of options in this option set
	protected void setOptionSize(int optionSize){
		this.optionSize = optionSize;
	}
	
	//Get the number of options in the option set
	protected int getOptionSize(){
		return optionSize;
	}
	
	//Set the name
	protected void setName(String name){
		this.name = name;
	}
	
	//Get the name
	protected String getName(){
		return name;
	}
	
	//Get all options
	protected ArrayList<Option> getOptions(){
		return options;
	}
	
	protected void setChoiceName(String name){
		choice.setName(name);
	}
	
	protected void setChoicePrice(float price){
		choice.setPrice(price);
	}
	
	protected void setChoice(Option choice){
		this.choice = choice;
	}
	
	protected Option getChoice(){
		return choice;
	}
	
	//Set a specific option using a raw name and price
	protected void setOption(int index, String name, float price){
		options.get(index).setName(name);
		options.get(index).setPrice(price);
	}
	
	protected void addOption(String name, float price){
		Option op = new Option();
		op.setName(name);
		op.setPrice(price);
		options.add(op);
		optionSize++;
	}
	
	//Get a specific Option
	protected Option getOption(int index){
		return options.get(index);
	}
	
	//Builds StringBuffer that contains data about the Opset
	protected StringBuffer print(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("\tOptionSet: " + name + " Size: " + optionSize + "\n");
		for(int i = 0; i < optionSize; i++){
			buffer.append(options.get(i).print());
		}
		return buffer;
	}
	
	public class Option implements Serializable{
		
		private String name;
		private float price;
		
		public Option(){
			name = "";
			price = 0.0f;
		}
		
		//Construct Option with a name
		public Option(String name){
			this.name = name;
			price = 0.0f;
		}
		
		//Construct Option with a name and price
		public Option(String name, float price){
			this.name = name;
			this.price = price;
		}
		
		//Set the name of the option
		protected void setName(String name){
			this.name = name;
		}
		
		//Get the name of the option
		protected String getName(){
			return name;
		}
		
		//Set the price of the option
		protected void setPrice(float price){
			this.price = price;
		}
		
		//Get the price of the option
		protected float getPrice(){
			return price;
		}
		
		//Builds a StringBuffer that contains data about the Option
		protected StringBuffer print(){
			StringBuffer buffer = new StringBuffer();
			buffer.append("\t\tOptionName: " + name + " Price: " + price + "\n");
			return buffer;
		}
	}
	
}
