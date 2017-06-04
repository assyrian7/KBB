/*
*	Paul Badalian
*	CIS 35B
*	Lab 5
*	Due: 3/10/17
*	Submitted: 3/10/17 
*/
package adapter;

public interface UpdateAuto {

	public void updateOptionSetName(String modelName, String optionSetName, String newName);
	
	public void updateOptionPrice(String modelName, String optionName, String option, float newprice);
	
}
