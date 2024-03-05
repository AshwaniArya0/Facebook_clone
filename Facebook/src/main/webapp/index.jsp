<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MY FACEBOOK</title>
<script>
function checkAll() {
    var isValid = true;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var repassword = document.getElementById("repassword").value;
    var name = document.getElementById("name").value;

    if (email.length == 0) {
        isValid = false;
        document.getElementById("error_email").innerHTML = "<font color=red>Empty Field</font>";
    }
    if (password.length == 0) {
        isValid = false;
        document.getElementById("error_password").innerHTML = "<font color=red>Empty Field</font>";
    }
    if (repassword.length == 0) {
        isValid = false;
        document.getElementById("error_repassword").innerHTML = "<font color=red>Empty Field</font>";
    }
    if (name.length == 0) {
        isValid = false;
        document.getElementById("error_name").innerHTML = "<font color=red>Empty Field</font>";
    }
    if (password !== repassword) {
        isValid = false;
        document.getElementById("error_password").innerHTML = "<font color=red>Password mismatch</font>";
    }
    return isValid;
}



function requiredField(val,id){
	if(val.length==0)
		document.getElementById(id).innerHTML="<font color=red>Empty Field</font>";
	else
		document.getElementById(id).innerHTML="";
}

</script>
</head>
<body>
<h3>Login Details</h3>
<form action="Login" method="post">
<table border="1" width="30%">
<tr>
<td>Email</td><td><input type="text" name="email" placeholder="Enter Email Here"></td>
</tr>
<tr>
<td>Password</td><td><input type="password" name="password" placeholder="Enter Password Here"></td>
</tr>
<tr>
<td><input type="submit" value="Login" ></td>
</tr>
</table>
<%
if(request.getParameter("lmessage")!=null){
	%>
	<span id="Loginmessage"><b><font color="red"><%= request.getParameter("lmessage")%></font></b></span>
	<%
}
%>
</form>
<h3>Signup Details</h3>
<form action="Signup" method="post" onSubmit="return checkAll()">
<table border="1" width="30%">
<tr> 
<td>Email</td><td><input type="text" id="email" onblur="requiredField(this.value,'error_email')" name="email" placeholder="Enter Email Here"><br><span id="error_email"></span></td>
</tr>
<tr>
<td>Password</td><td><input type="password" id="password" onblur="requiredField(this.value,'error_password')" name="password" placeholder="Enter Password Here"><br><span id="error_password"></span></td>
</tr>
<tr>
<td>re-TypePassword</td><td><input type="password" id="repassword" onblur="requiredField(this.value,'error_repassword')" name="repassword" placeholder="Enter Password Here"><br><span id="error_repassword"></span></td>
</tr>
<tr>
<td>Name</td><td><input type="text" id="name" onblur="requiredField(this.value,'error_name')" name="name" placeholder="Enter Name Here"><br><span id="error_name"></span></td>
</tr>
<tr>
<td><input type="submit" value="Signup" ></td>
</tr>
</table>
<%
if(request.getParameter("smessage")!=null){
	%>
	<span id="Signupmessage"><b><font color="red"><%= request.getParameter("smessage")%></font></b></span>
	<%
}
%>
</form>
</body>
</html>