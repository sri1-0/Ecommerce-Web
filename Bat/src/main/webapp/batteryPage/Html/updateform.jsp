<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page import="com.batterybean.*" %>>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
		<%
		BeanClass bean = (BeanClass) request.getAttribute("bean");
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
				<form action="/Bat/updateexistinguser" method="post">
				<tr>
					<td><input type="text" name="userid"
						value="<%=bean.getId()%>"></td>
					<td><input type="text" name="username"
						value="<%=bean.getName()%>"></td>
					<td><input type="number" name="usernumber"
						value="<%=bean.getNumber()%>"></td>
					<td><input type="text" name="usermail"
						value="<%=bean.getEmail()%>"></td>
					<td><input type="password" name="userpass"
						value="<%=bean.getPass()%>"></td>
					<td><input type="text" name="usergender"
						value="<%=bean.getGender()%>"></td>
					<td><input type="submit" ></td>
				</tr>
				</form>

			</tbody>

		</table>


</body>

</html>