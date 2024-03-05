<%@page import="com.dhruv.beans.User" %>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>Profile</h3>
<%
ArrayList<User> profiles = (ArrayList<User>) request.getAttribute("profile");
for (User profile : profiles) {
    // Assuming you have a link to display each profile with email as parameter
%>
    
        <h3>Email:&nbsp;<%= profile.getEmail() %></h3>
        <h3>Name:&nbsp;<%= profile.getName() %></h3>
    </a>
    <br>
<%
}
%>
</body>
</html>