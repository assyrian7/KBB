<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="model.Automobile" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	Automobile car = (Automobile)request.getAttribute("car");
%>

<h3>Configure</h3>

<table border="1">

	<tbody>
	
	<tr>
		<td><%= car.getName()%></td>
		<td>Base Price</td>
		<td><%= car.getPrice()%></td>
	</tr>
	<% for(int i = 0; i < car.getOptionSetSize(); i++){ 
	       String optionSet = car.getOptionSetName(i);
	%>
	<tr>
		<td><%= optionSet%></td>
		<td><%= car.getOptionChoice(optionSet)%></td>
		<td><%= car.getOptionChoicePrice(optionSet)%></td>
	</tr>
	<% } %>
	<tr>
		<td>Total Cost</td>
		<td></td>
		<td><%= "$" + car.getTotalPrice()%></td>
	</tr>
	</tbody>

</table>
</body>
</html>