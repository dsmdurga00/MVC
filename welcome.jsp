<%@page import="com.durga.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC Project</title>
</head>
<body>

<%
     User user =  (User) session.getAttribute("session_user");
%>

<h2 style="color: orange">Welcome <%= user.getUsername() %> to our Project profile...............</h2>

 <h3>Name : <%= user.getUsername() %></h3>
 <h3>Email ID: <%= user.getEmail() %></h3>
 <h3>City: <%= user.getCity() %></h3>
 
 <a href="LogoutServlet">Logout </a>
</body>
</html>