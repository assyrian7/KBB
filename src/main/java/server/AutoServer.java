/*
*	Paul Badalian
*	CIS 35B
*	Lab 5
*	Due: 3/10/17
*	Submitted: 3/10/17 
*/

package server;

import java.util.ArrayList;
import java.util.Properties;

import model.Automobile;

public interface AutoServer {
	public void acceptProperties(Properties props);
	public String getList();
	public Automobile getAuto(String auto);
}
