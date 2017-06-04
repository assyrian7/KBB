<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="client.SelectCarOption" %>
<%@ page import="java.util.*" %>
<%@ page import="model.Automobile" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
</head>
<body>
 
<h3>Select Car</h3>

<%
SelectCarOption clientIO = new SelectCarOption();
String list = clientIO.getList();
String[] carList = list.split(";"); 
ArrayList<String> cars = new ArrayList<String>(Arrays.asList(carList));
%>

<!--  
<table border="1" style="margin-top: 20px;">

	<thead>
		<tr>
			<th>Automobiles</th>
		</tr>
	</thead>
	<tbody>
		<% for(int i = 0; i < carList.length; i++){ %>
			<tr>
				<td><%= carList[i]%></td>
			</tr>
		<% } %>
	</tbody>

</table>
-->

<form action="/CIS35B-Lab6/ConfigureServlet" method="POST">

	<table border="1" id="carContent">
	
		<tbody>
			<tr>
				<td>Make/Model:</td>
				<td>
					<select name="car" id="car" onChange="loadContent(this)">
						<option value="">-</option>
						<% for(int i = 0; i < carList.length; i++){ %>
							<option value="<%= carList[i]%>"><%= carList[i]%></option>
						<% } %>
					</select>
				</td>
			</tr>  
			<%--
			<%
				if(request.getParameter("carName") != null){
					String carName = request.getParameter("carName");
					Automobile car = clientIO.getAuto(carName);
					for(int i = 0; i < car.getOptionSetSize(); i++){
						String optionSetName = car.getOptionSetName(i);
			%>
			<tr>
				<td><%= optionSetName%></td>
				<td>
					<select name="<%= optionSetName%>">
						<% ArrayList<String> options = car.getOptionSetChoices(i); 
						   for(int j = 0; j < options.size(); j++){
							   String option = options.get(j);
						%>
						<option value="<%= option%>"><%= option%></option>
						<% } %>
					</select>
				</td>
			</tr>
			<%	   }
				} 
			%>
			--%>
		</tbody>
	
	</table>
	<button type="submit">Submit</button>
</form>

<script type="text/javascript">

	function loadCar(carObj){
		var carName = carObj.value;
		console.log("Loading");
		window.location = "/CIS35B-Lab6/?carName=" + carName;
	}	
	
	function loadContent(carSelect){
		var car = carSelect.value;
		var carContent = document.getElementById("carContent");
		$.ajax({
			type: 'POST',
            url : 'http://localhost:8080/CIS35B-Lab6/AjaxServlet?action=load&car=' + car,
            success : function(data) {
            	carContent.innerHTML = data;
            	console.log(data);
            	console.log("done");
            }
        });
	}

</script>

</body>
</html>