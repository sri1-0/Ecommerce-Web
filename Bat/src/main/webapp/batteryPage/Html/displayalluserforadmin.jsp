<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page import="com.batterybean.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
		<%
		BeanClass bean = (BeanClass) request.getAttribute("bean");
		List<BeanClass> beans = (List<BeanClass>) request.getAttribute("beans");
		
		%>

		<table>
			<thead>

				<tr>
					<td><label>USER ID</label></td>
					<td><label>USER NAME</label></td>
					<td><label>USER NUMBER</label></td>
					<td><label>USER MAIL</label></td>
					<td><label>USER PASS</label></td>
					<td><label>USER GENDER</label></td>
				</tr>
			</thead>
			<tbody>
				<%for(BeanClass bea: beans){%>
				<form action="/Bat/modifyuser" method="post">
				<tr>
					<td><input type="text" name="userid"
						value="<%=bea.getId()%>"></td>
					<td><input type="text" name="username"
						value="<%=bea.getName()%>"></td>
					<td><input type="number" name="usernumber"
						value="<%=bea.getNumber()%>"></td>
					<td><input type="text" name="usermail"
						value="<%=bea.getEmail()%>"></td>
					<td><input type="password" name="userpass"
						value="<%=bea.getPass()%>"></td>
					<td><input type="text" name="usergender"
						value="<%=bea.getGender()%>"></td>
					<td><input type="submit" name="action" value="view<%=bea.getId()%>"></td>
				</tr>
				</form>
				<%} %> 

			</tbody>

		</table>


</body>
</html>