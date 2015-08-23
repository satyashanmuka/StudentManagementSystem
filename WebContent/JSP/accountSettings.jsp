<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import= "java.util.ArrayList" %>
 <%@page import="com.ku.eecs.beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>University of Kansas Student Academics Management System</title>
<link href="<%=request.getContextPath() %>/CSS/calendar.css" rel="stylesheet" type="text/css" id="style1"/> 
<link href="<%=request.getContextPath() %>/CSS/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/JavaScript/calendar.js"></script> 

<script type="text/javascript">
function validate()
{
	var displayName= document.f2.displayName.value;
	var emailId= document.f2.emailId.value;
	var newpassword = document.f2.newPassword.value;
	var repeatnewpassword = document.f2.repeatNewPassword.value;
	if(displayName=="")
	{
		alert("Please enter a displayName");
		return false;
	}
	if(emailId=="")
	{
		alert("Please enter a Email Id");
		return false;
	}
	if(newpassword!="" && newpassword!=repeatnewpassword)
	{
		alert("Both the passwords do not match!! Please check before saving.");
		return false;
	}
		
}
</script>
</head>
<%@include  file ='header.jsp'%>
<body>
<div id="content">
	<div id="main">
	<div id="welcome" class="post">
	
	<center><h1>Account Settings </h1></center><br>
	
	<%
	UserBean ub = (UserBean)session.getAttribute("userBean");
	if(request.getAttribute("status")!= null && request.getAttribute("status").equals("success"))
	{
		
	%>
	<h3>Account Details Updated Successfully!!!</h3>
	<% } 
	else if(request.getAttribute("status")!= null && request.getAttribute("status").equals("failure")){	 
	%>
		<h3 ><font color="red">Current Password you entered doesn't match with our records. Please provide a correct password!!!</font></h3>
	<% } %>
	 
	
	<form name ="f2" method =post action="<%=request.getContextPath() %>/AccountSettingsServlet" onsubmit="return validate()">
	 <table align ="center" border =0>
	 <tr><td> <br></td></tr>
	 <tr>
	 <td>
	 Display  Name:
	 </td>
	 <td>
	 <input type="text" name="displayName" value=<%=ub.getUserName()%> />	 
	 </td>
	 </tr>
	  <tr><td> <br></td></tr>
	 <tr>
	 <td>
	 Desired Email Id:
	 </td>
	 <td>
	 <input type="text" name="emailId" value=<%=ub.getEmailId() %> />
	 </td>
	 </tr>
	  <tr><td> <br></td></tr>
	 <tr>
	 <td>
	 <h3>Change Password</h3>
	 </td>
	 </tr>
	 <tr><td> <br></td></tr>	 
	 <tr>
	 <td>
	 Current Password
	 </td>
	 <td>
	 <input type="password" name="currentPassword" />
	 </td>
	 </tr>
	  <tr><td> <br></td></tr>
	 <tr>
	 <td>
	 New Password
	 </td>
	 <td>
	 <input type="password" name="newPassword" />
	 </td>
	 </tr>
	  <tr><td> <br></td></tr>
	 <tr>
	 <td>
	 Repeat New Password
	 </td>
	 <td>
	 <input type="password" name="repeatNewPassword" />
	 </td>
	 </tr>
	  <tr><td> <br></td></tr>
	 <tr>
	 <td></td>
	 <td>
	 <input type= "submit" value="Update"></input>
	 <input type="reset" value="Reset"></input>
	 </td>
	 </tr>
	 </table>
	</form>
</div>
</div>
</div>

<%@include  file ='footer.jsp'%>	
</body>
</html>



