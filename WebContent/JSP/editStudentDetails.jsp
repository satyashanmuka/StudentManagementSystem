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
	var firstName = document.f2.firstName.value;
	var lastName = document.f2.lastName.value;
	var emailId = document.f2.emailId.value;

	if(firstName=="")
	{
		alert("Please enter a first name");
		return false;
	}
	if(lastName=="")
	{
		alert("Please enter a last name");
		return false;
	}
	if(emailId=="")
	{
		alert("Please enter a email id");
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
	<% UserBean cb = (UserBean) request.getAttribute("user");%>
	<center><h1>Update Course</h1></center><br>
	
	<%
	UserBean ub = (UserBean)session.getAttribute("userBean");
	if(request.getAttribute("status")!= null && request.getAttribute("status").equals("success"))
	{
		
	%>
	<h3>Student Details Updated Successfully!!!<br/><br/><br/></h3>
	<% } 
	else if(request.getAttribute("status")!= null && request.getAttribute("status").equals("failure")){	 
	%>
		<h3>Currently we are facing an issue updating your request. Please try again after some time!!!<br/><br/><br/></h3>
	<% }
	 else {%>
	
	<form name ="f2" method =post action="<%=request.getContextPath() %>/BrowseUsersServlet" onsubmit="return validate()">
	 <table align ="center" border =0>
	 <tr><td> <br></td></tr>
	 
	 <tr>
	 <td>
	 User Name
	 </td>
	 <td>
	 <input type="text" name="userName" readonly value=<%=cb.getUserName() %>/>
	 </td>
	 </tr>
	 
	 <tr>
	 <td>
	 First Name
	 </td>
	 <td>
	 <input type="text" name="firstName" value=<%=cb.getFirstName() %>/>
	 </td>
	 </tr>
	 
	 <tr>
	 <td>
	 Last Name
	 </td>
	 <td>
	 <input type="text" name="lastName" value=<%=cb.getLastName() %>/>
	 </td>
	 </tr>
	 
	 <tr>
	 <td>
	 Email ID
	 </td>
	 <td>
	 <input type="text" name="emailId" value=<%=cb.getEmailId() %>/>
	 </td>
	 </tr>
	 
	 <tr>
	 <td>
	 KU ID
	 </td>
	 <td>
	 <input type="text" name="kuId" readonly value=<%=cb.getKuId() %>/>
	 </td>
	 </tr>
	 
	 <tr>
	 <td>
	 KU Online ID
	 </td>
	 <td>
	 <input type="text" name="kuOnlineId" readonly value=<%=cb.getKuOnlineId() %>/>
	 </td>
	 </tr>
	 <tr><td> <br></td></tr>
	 <tr>
	 <td></td>
	 <td>
	 <input type= "hidden" name="userId" value=<%=cb.getUserId() %>>
	 <input type= "submit" value="Update"></input>
	 </td>
	 </tr>
	 </table>
	</form>
	
	<%}   %>
</div>
</div>
</div>

<%@include  file ='footer.jsp'%>	
</body>
</html>



