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

import model.Opset.Option;

public class Automobile implements Serializable{

	private float price;
	private int optionSetSize;
	private String name;
	private String make;
	private String model;
	private ArrayList<Opset> optionSets;
	private ArrayList<Option> choices;
	
	public Automobile(){
		make = "";
		model = "";
		name = "";
		optionSetSize = 0;
		optionSets = new ArrayList<Opset>();
		choices = new ArrayList<Option>();
	}
	
	//Construct the model with a size for the option sets
	public Automobile(int optionSetSize){
		make = "";
		model = "";
		name = "";
		price = 0.0f;
		optionSets = new ArrayList<Opset>();
		choices = new ArrayList<Option>();
		for(int i = 0; i < optionSetSize; i++){
			Opset opset = new Opset();
			optionSets.add(opset);
			choices.add(opset.getChoice());
		}
	}
	
	public Automobile(String make, String model){
		this.make = make;
		this.model = model;
		this.name = make + model;
		this.optionSetSize = 0;
		optionSets = new ArrayList<Opset>();
		choices = new ArrayList<Option>();
	}
	
	//Construct the model with a size for the option sets and an Automotive name	
	public Automobile(String make, String model, int optionSetSize){
		this.make = make;
		this.model = model;
		this.name = make + model;
		this.optionSetSize = optionSetSize;
		optionSets = new ArrayList<Opset>();
		choices = new ArrayList<Option>();
		for(int i = 0; i < optionSetSize; i++){
			Opset opset = new Opset();
			optionSets.add(opset);
			choices.add(opset.getChoice());
		}	
	}
	
	//Construct the model with a size for the option sets, a price for the car, and an Automotive name
	public Automobile(String make, float price, int optionSetSize){
		this.make = make;
		this.model = "Unclear";
		this.name = make + model;
		this.price = price;
		this.optionSetSize = optionSetSize;
		optionSets = new ArrayList<Opset>();
		choices = new ArrayList<Option>();
		for(int i = 0; i < optionSetSize; i++){
			Opset opset = new Opset();
			optionSets.add(opset);
			choices.add(opset.getChoice());
		}
	}

	public Automobile(String make, String model, float price, int optionSetSize){
		this.make = make;
		this.model = model;
		this.name = make + model;
		this.price = price;
		this.optionSetSize = optionSetSize;
		optionSets = new ArrayList<Opset>();
		choices = new ArrayList<Option>();
		for(int i = 0; i < optionSetSize; i++){
			Opset opset = new Opset();
			optionSets.add(opset);
			choices.add(opset.getChoice());
		}
	}

	
	//Initialize all the options in an option set with a size
	public void addOptionSet(String name){
		Opset op = new Opset(name);
		optionSets.add(op);
		choices.add(op.getChoice());
		optionSetSize++;
	}
	
	public void setOptionSet(int index, int optionSize, String name){
		optionSets.get(index).setOptionSize(optionSize);
		optionSets.get(index).setName(name);
		optionSets.get(index).setOptions();
	}
	
	//Update the name for a specific option set
	public  boolean setOptionSetName(String optionSetName, String newName){
		for(int i = 0; i < optionSets.size(); i++){
			if(optionSets.get(i).getName().equals(optionSetName)){
				optionSets.get(i).setName(newName);
				return true;
			}
		}
		return false;
	}
	
	//Update the price for a specific option
	public  boolean setOptionPrice(String optionSetName, String optionName, float price){
		for(int i = 0; i < optionSets.size(); i++){
			if(optionSets.get(i).getName().equals(optionSetName)){
				Opset opSet = optionSets.get(i);
				for(int j = 0; j < optionSets.get(i).getOptionSize(); j++){
					Option op = opSet.getOption(j);
					if(op.getName().equals(optionName)){
						op.setPrice(price);
						return true;
					}
				}
			}
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	//Get price of this Automotive
	public float getPrice() {
		return price;
	}

	//Set price of this Automotive
	public void setPrice(float price) {
		this.price = price;
	}

	//Get the array of Opsets
	public ArrayList<Opset> getOptionSets() {
		return optionSets;
	}
	
	//Get a specific Opset
	public Opset getOptionSet(int index){
		return optionSets.get(index);
	}

	//Set the array of Opsets
	public void setOptionSets(ArrayList<Opset> optionSets) {
		this.optionSets = optionSets;
	}

	//Get the number of option sets
	public int getOptionSetSize() {
		return optionSetSize;
	}

	//Set the number of option sets
	public void setOptionSetSize(int optionSetSize) {
		this.optionSetSize = optionSetSize;
	}
	
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	/*
	public void printChoices(){
		for(Option op: choices){
			System.out.println(op.print());
		}
	}
	*/

	public float getTotalPrice(){
		float sum = 0.0f;
		sum += price;
		for(Option choice: choices){
			sum += choice.getPrice();
		}
		return sum;
	}
	
	public String getOptionChoice(String optionSetName){
		for(int i = 0; i < optionSets.size(); i++){
			if(optionSetName.equals(optionSets.get(i).getName())){
				return choices.get(i).getName();
			}
		}
		return null;
	}
	
	public float getOptionChoicePrice(String optionSetName){
		for(int i = 0; i < optionSets.size(); i++){
			if(optionSetName.equals(optionSets.get(i).getName())){
				return choices.get(i).getPrice();
			}
		}
		return -1;
	}
	
	public void setOptionChoice(String optionSetName, String option){
		for(int i = 0; i < optionSetSize; i++){
			if(optionSets.get(i).getName().equals(optionSetName)){
				Opset opset = optionSets.get(i);
				for(int j = 0; j < opset.getOptionSize(); j++){
					Option op = opset.getOption(j);
					if(op.getName().equals(option)){
						System.out.println(op.print());
						opset.setChoiceName(op.getName());
						opset.setChoicePrice(op.getPrice());
					}
				}
			}
		}
	}
	
	public String getOptionSetName(int index){
		return optionSets.get(index).getName();
	}
	
	public ArrayList<String> getOptionSetChoices(int index){
		ArrayList<Option> options = optionSets.get(index).getOptions();
		ArrayList<String> optionNames = new ArrayList<String>();
		for(int i = 0; i < options.size(); i++){
			optionNames.add(options.get(i).getName());
		}
		return optionNames;
	}
	
	public ArrayList<String> getOptionSetNames(){
		ArrayList<String> optionSetNames = new ArrayList<String>();
		for(Opset opset: optionSets){
			optionSetNames.add(opset.getName());
		}
		return optionSetNames;
	}
	
	//Set a specific option
	public void setOption(int opset, int option, String name, float price){
		optionSets.get(opset).setOption(option, name, price);
	}
	
	public void addOption(int opset, String name, float price){
		optionSets.get(opset).addOption(name, price);
	}
	
	//Build a StringBuffer containing data about the Automotive
	public StringBuffer print(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("Make: " + make + " Model: " + model + " Price: " + price + "\n");
		for(int i = 0; i < optionSetSize; i++){
			buffer.append(optionSets.get(i).print());
		}
		return buffer;
	}
	
}
