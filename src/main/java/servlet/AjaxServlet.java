package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Automobile;
import client.SelectCarOption;

public class AjaxServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		String action = request.getParameter("action");
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(action.equals("load")){
			String carName = request.getParameter("car");
			SelectCarOption clientIO = new SelectCarOption();
			Automobile car = clientIO.getAuto(carName);
			String[] list = clientIO.getList().split(";");
			writer.write("<tbody>\n");
			writer.write("<tr>\n");
			writer.write("<td>Make/Model:</td>\n");
			writer.write("<td>\n");
			writer.write("<select name=\"car\" id=\"car\" onChange=\"loadCar(this)\">\n");
			writer.write("<option value=\"\">-</option>\n");
			for(int i = 0; i < list.length; i++){
				if(list[i].equals(carName)){
					writer.write("<option value=\"" + list[i] + "\" selected>" + list[i] + "</option>\n");
				}
				else{
					writer.write("<option value=\"" + list[i] + "\">" + list[i] + "</option>\n");
				}
			}
			writer.write("</select>\n");
			writer.write("</td>\n");
			writer.write("</tr>\n");
			for(int i = 0; i < car.getOptionSetSize(); i++){
				String optionSet = car.getOptionSetName(i);
				writer.write("<tr>");
				writer.write("<td>" + optionSet + "</td>\n");
				writer.write("<td>\n");
				writer.write("<select name=\"" + optionSet + "\">\n");
				ArrayList<String> options = car.getOptionSetChoices(i); 
			    for(int j = 0; j < options.size(); j++){
				    String option = options.get(j);
				    writer.write("<option value=\"" + option + "\">" + option + "</option>\n");
			    }
			    writer.write("</select>\n");
			    writer.write("</td>\n");
			    writer.write("</tr>\n");
			}
			writer.write("</tbody>\n");
		}
	}
	
}
