<%@page import="java.util.ArrayList"%>
<%@page import="com.dhruv.database.DBHandler"%>
<%@page import="com.dhruv.beans.Friend"%>
<%@page import="com.dhruv.beans.User" %>
<%@page import="com.dhruv.beans.Wpost" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
User user=(User)session.getAttribute("User");
DBHandler db=new DBHandler();
%>
<h3>Welcome <%= db.getUserName(user.getEmail()) %></h3>

<table>
<tr>
<td width="25">
</td>
<td width="50">
<h3>Send Friend Request</h3>
<form action="SendRequest" method="post">
<input type="text" name="rec" placeholder="Enter email here">
<input type="submit" value="Send Request">
</form>
<%
if(request.getParameter("rmessage")!=null){
	%>
	<span id="Requestmessage"><b><font color="red"><%= request.getParameter("rmessage")%></font></b></span>
	<%
}
%>
</td>

</td>
</tr>
<tr>
<td width="250">
<%
ArrayList<Friend> friends=(ArrayList<Friend>)request.getAttribute("friends");
for(Friend afriend:friends){
	if(afriend.getSender().equals(user.getEmail()))
	{
	%>
	
	<a href="ViewProfile?email=<%=afriend.getRec()%>"><b><%= db.getUserName(afriend.getRec())%></b></a>
	
	
	<br>
	<%
}
	else{
		%>
		<a href="ViewProfile?email=<%=afriend.getSender()%>"><b><%= db.getUserName(afriend.getSender())%></b></a>
		
		<%
	}
}
%>
</td>


</td>
<td width="50">
<h3>What's in your mind?</h3>
<form action="SavePost" method="post">
<input type="text" name="msg" placeholder="Enter message here">
<input type="submit" value="SavePost">
</form>
<%
if(request.getParameter("pmessage")!=null){
	%>
	<span id="Postmessage"><b><font color="red"><%= request.getParameter("pmessage")%></font></b></span>
	<%
}
%>
</td>


<td width="50">
<%
ArrayList<Friend> pfriends = (ArrayList<Friend>) request.getAttribute("pfriends");
    for (Friend friend : pfriends) {
%>
    <b><%= friend.getSender() %></b>
    <a href="Accept?fid=<%= friend.getFid() %>">Accept</a>
    <a href="Reject?fid=<%= friend.getFid() %>">Reject</a>
    <br>
<%
    }

%>
</td>


<td width="50"></td>
<td width="25">

</tr>

</table>
<table>
<tr>
<td width="250"></td>
<td width="250">
<%
ArrayList<Wpost> wpost=(ArrayList<Wpost>)request.getAttribute("wpost");

for(Wpost posts:wpost){
	%>
	<b><%=db.getUserName(posts.getSender())%></b><br>
	<b><%=posts.getDop() %></b><br>
	<b><%=posts.getMsg() %></b><br>
	<hr>
	<%
}
%>
</tr>
</td>
</table>
</body>
</html>