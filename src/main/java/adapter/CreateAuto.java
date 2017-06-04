/*
*	Paul Badalian
*	CIS 35B
*	Lab 5
*	Due: 3/10/17
*	Submitted: 3/10/17 
*/
package adapter;

import java.util.Properties;

public interface CreateAuto {

	public void buildAuto();
	public void buildAuto(String fileName, String fileType);
	public void printAuto(String modelName);
}
