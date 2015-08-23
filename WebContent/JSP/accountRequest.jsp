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
	var firstName= document.f2.firstName.value;
	var lastName= document.f2.lastName.value;
	var emailId= document.f2.emailId.value;
	var kuId= document.f2.kuId.value;
	var kuOnlineId= document.f2.kuOnlineId.value;
	var userName= document.f2.userName.value;
	var password= document.f2.password.value;
	var department= document.f2.department.value;

	if(firstName=="")
	{
		alert("Please enter first name");
		return false;
	}
	if(lastName=="")
	{
		alert("Please enter last name");
		return false;
	}
	if(emailId=="")
	{
		alert("Please enter email ID");
		return false;
	}
	if(kuId=="")
	{
		alert("Please enter KU ID");
		return false;
	}
	if(kuOnlineId=="")
	{
		alert("Please enter KU Online ID");
		return false;
	}
	if(userName=="")
	{
		alert("Please enter Desired User Name");
		return false;
	}
	if(password=="")
	{
		alert("Please enter Password");
		return false;
	}
	if(department=="sb")
	{
		alert("Please Select a Department");
		return false;
	}
		
}
</script>
</head>
<body>

<%if(session.getAttribute("theName")==null);
{%>


<div id="header">
	<div id="logo">
		<h1>University of Kansas</h1>
		<h2>Student Academics Management System</h2>
	</div>
</div>

<div id="content">
	<div id="main">
	<div id="welcome" class="post">
	
	<center><h1>Request for a New Account</h1></center><br>
	
	<%if(request.getAttribute("status").equals("initialLoad")) {%>
	<form name ="f2" method =post action="<%=request.getContextPath() %>/AccountRequestServlet" onsubmit="return validate()">
	   
	 <table align ="center" border =0>
	 <tr><td> <div style="color:red">* All are required fields</div></tr>
	  <tr><td> <br></td></tr>
	 
	 <tr>
	 <td>
	 First Name
	 </td>
	 <td>
	 <input type="text" name="firstName" />
	 </td>
	 </tr>
	 
	 <tr>
	 <td>
	 Last Name
	 </td>
	 <td>
	 <input type="text" name="lastName" />
	 </td>
	 </tr>
	 
	 <tr>
	 <td>
	 Email ID
	 </td>
	 <td>
	 <input type="text" name="emailId" />
	 </td>
	 </tr>
	 
	 <tr>
	 <td>
	 KU ID Number
	 </td>
	 <td>
	 <input type="text" name="kuId" />
	 </td>
	 </tr>
	 
	 <tr>
	 <td>
	 KU Online ID
	 </td>
	 <td>
	 <input type="text" name="kuOnlineId" />
	 </td>
	 </tr>
	 
	 <tr>
	 <td>
	 Preferred User Name
	 </td>
	 <td>
	 <input type="text" name="userName" />
	 </td>
	 </tr>
	 
	 <tr>
	 <td>
	 Password
	 </td>
	 <td>
	 <input type="password" name="password" />
	 </td>
	 </tr>

	 <tr>
	  <td>
	  Department
	  </td>
	  <td>
	  
	  <%
	  		ArrayList<DepartmentBean>  departmentNames = (ArrayList<DepartmentBean>)request.getAttribute("departmentNames");
	   %>
	   
	 <select type="text" name="department" >
	 	<option value="sb">--Select a Deparment--</option>
	 	<% 
		for(DepartmentBean st:departmentNames)
		{ 
		%>
		<option value="<%=st.getDepartmentId()%>"><%=st.getDepartmentName() %> </option>
	 	<%}%>
	 </select> 
	  </td>
	 </tr>
	 
	 <tr><td> <br></td></tr>
	 <tr>
	 <td></td>
	 <td>
	 <input type= "submit" value="Submit"></input>
	 <input type="reset" value="Reset"></input>
	 <a href="index.jsp"><b>Go Back to Login Page</b></a>
	 </td>
	 </tr>
	 </table>
	</form>
<% }
	else if(request.getAttribute("status").equals("success")){	
	%>
	<h3>Account Request Submitted. An administrator will soon approve your request. In General it may take a couple of days to process an account request. 
	You will be able to login to the system once the account request is approved.<br/><br/><br/></h3>
	<a href="index.jsp"><b>Go Back to Login Page</b></a>
	
	<% } 
	else if(request.getAttribute("status").equals("failure")){	 
	%>
		<h3>Currently we are facing an issue updating your request. Please try again after some time!!!<br/><br/><br/></h3>
		<a href="index.jsp"><b>Go Back to Login Page</b></a>
	<% }
	%>
</div>
</div>
</div>
<div id="footer">
	<p id="legal">Copyright &copy; 2015 University of Kansas. All Rights Reserved. </p>
	
</div>

<%}%>	

</body>
</html>



