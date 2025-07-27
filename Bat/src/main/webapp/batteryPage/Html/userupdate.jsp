<%@page import="com.batterybean.BeanClass"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.batteryServe.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style>
table {
	text-align: center;
	width: 800px;
	height: 800px;
	background-color: aqua
}

h1 {
	text-align: center;
}

form {
	margin-top: 30px;
	font-size: xx-large;
	margin-left: 250px;
}

label {
	color: rgb(186, 26, 36);
}

.head {
	display: flex;
	justify-content: space-between;
}

.head li {
	display: inline;
}

.Com-Name {
	color: rgb(11, 176, 226);
	position: relative;
	margin-left: 80vh;
	margin-top: 0px;
	font-weight: bold;
	font-size: 50px;
	overflow: hidden;
}

.Com-Name::before {
	position: absolute;
	content: "";
	background-color: rgb(229, 251, 121);
	width: 0%;
	height: 100%;
	transition: 1s;
}

.Com-Name:hover::before {
	background-color: transparent;
	border-radius: 60%;
	width: 90%;
}

.Com-Name:hover {
	color: rgb(229, 251, 121);
}

.head a {
	color: rgb(11, 176, 226);
	position: relative;
	margin-left: 20px;
	text-decoration: none;
	font-size: x-large;
	font-weight: bold;
	overflow: hidden;
}

.head a::before {
	position: absolute;
	content: "";
	background-color: rgb(229, 251, 121);
	width: 0%;
	height: 100%;
	overflow: hidden;
	transition: 1s;
}

.head a:hover::before {
	background-color: transparent;
	border-radius: 60%;
	width: 90%;
	height: 100%;
	z-index: 1;
}

.head a:hover, .head a.active {
	color: rgb(208, 245, 20);
}

body {
	background-image: url("../image/bg.jpg");
	background-position: center;
	background-size: cover;
}

#heading {
	font-size: xx-large;
	text-align: center;
	padding-top: 20px;
	color: yellow;
	border: 2px solid black;
	height: 50px;
	width: 120vh;
	margin-left: 40vh;
	border-radius: 50%;
	background-color: blueviolet;
}
</style>
</head>
<body>
	<%
	BeanClass bean = (BeanClass)request.getAttribute("bean");
	%>

	<header class="head">
		<h1 class="Com-Name">Profile</h1>
	</header>

	<form action="/Bat/userupdate" method="post" submit="return check()">
		<table>
			<tbody>
				<tr>
					<td><label for=""> UserID:</label></td>
					<td><input type="text" name="userid" id="userid" value="<%=bean.getId()%>" readonly></td>
				</tr>
				<tr>
					<td><label for=""> Name:</label></td>
					<td><input type="text" name="username" id="username" value="<%=bean.getName()%>"/>
					</td>
				</tr>
				<tr>
					<td><label for="">Phone Number: </label></td>
					<td><input type="number" name="usernumber" id="" value="<%=bean.getNumber()%>"/></td>
				</tr>
				<tr>
					<td><label for=""> Mail Id: </label></td>
					<td><input type="mail" name="usermail" id="" value="<%=bean.getEmail()%>"/></td>
				</tr>
				<tr>
					<td><label for="">Password </label></td>
					<td><input type="text" name="userpass" id="" value="<%=bean.getPass()%>"
						placeholder="none" readonly /></td>
				</tr>

				<tr>
					<td><label for="">Gender </label></td>
					<td><input type="text" name="usergender" id="" value="<%=bean.getGender()%>"/></td>
				</tr>
				<tr>
					<td  style="margin-left: 40 0px"><input
						type="submit" name="" id="" style="background-color: burlywood;" />
					</td>
					<td  style="margin-left: 40 0px"><input
						type="submit"  style="background-color: burlywood;" name="action" value="delete_<%=bean.getId()%>" />
					</td>
				</tr>
				
			</tbody>
		</table>
	</form>
	<script src="../Javascript/regestrationpage.js"></script>
</body>

</html>