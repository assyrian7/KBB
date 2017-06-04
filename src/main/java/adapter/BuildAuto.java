/*
*	Paul Badalian
*	CIS 35B
*	Lab 5
*	Due: 3/10/17
*	Submitted: 3/10/17 
*/
package adapter;

import server.AutoServer;

public class BuildAuto extends ProxyAutomobile implements CreateAuto, UpdateAuto, FixAuto, EditThread, AutoServer{

}
