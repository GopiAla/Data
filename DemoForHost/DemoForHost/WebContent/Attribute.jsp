<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Attribute Details</title>
</head>
	<br>
	<br>
	<h2><p align="center">Enter Attribute</p></h2>
	<p align="justify">
	<a href="http://localhost:8080/Apple_Base/Login.jsp">Home</a></p>
	
<body>
	<form action = "AttributeDetails" method="post" style="height: 93px; background-color: Orange">
		<table>
			<tr>
		<td>Emp ID : </td>
		<td><input type="text" name="userid"> </td>
		<td>Emp Name : </td>
		<td><input type="text" name="userid"> </td>
		</tr>
		<tr><td></td></tr>
		<tr>
		<td>Date :</td>
		<td><input type="text" name="layer"> </td>
		</tr>
		<tr><td></td></tr>
		<tr>
		<td>Process :</td>
		<td><input type="text" name="process"> </td>
		</tr>
		<tr><td></td></tr>
		<tr>
		<td>Status :</td>
		<td>
		<select>
		  <option value="comp">Comp</option>
		  <option value="hold">hold</option>
		  <option value="wip">wip</option>
		  </select>
		</td>
		<td></td>
		<td> Category :</td>
		<td><input type="checkbox"> Yes
			<input type="checkbox"> No</td>
		</tr>
		</table> 
		
		<input type="submit" value="Export" style="height: 31px; width: 66px">
	</form>
</body>
</html>