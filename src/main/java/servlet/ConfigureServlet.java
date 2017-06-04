package servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Automobile;
import client.SelectCarOption;

public class ConfigureServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		
		String carName = request.getParameter("carName");
		SelectCarOption option = new SelectCarOption();
		Automobile auto = option.getAuto(carName);
		request.setAttribute("car", auto);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("configure.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		
		String carName = request.getParameter("car");
		
		SelectCarOption clientIO = new SelectCarOption();
		Automobile car = clientIO.getAuto(carName);
		
		Map<String, String[]> paramMap = request.getParameterMap();
		Iterator<Entry<String, String[]>> it = paramMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, String[]> pair = (Map.Entry<String, String[]>)it.next();
	        String key = pair.getKey();
	        String[] value = pair.getValue();
	        if(!key.equals("car")){
	        	car.setOptionChoice(key, value[0]);
	        }
	    }
		
	    request.setAttribute("car", car);
	    
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("configure.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
