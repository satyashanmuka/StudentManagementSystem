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
	var subject= document.f2.subject.value;
	var content= document.f2.content.value;
	var department= document.f2.department.value;

	if(subject=="")
	{
		alert("Please enter a subject for the message");
		return false;
	}
	if(content=="")
	{
		alert("Please enter content for the message");
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
<%@include  file ='header.jsp'%>
<body>
<div id="content">
	<div id="main">
	<div id="welcome" class="post">
	
	<center><h1>Create a New Message</h1></center><br>
	
	<form name ="f2" method =post action="<%=request.getContextPath() %>/MessageServlet" onsubmit="return validate()">
	 <table align ="center" border =0>
	 <tr><td> <div style="color:red">* All are required fields</div></tr>
	 <tr><td> <br></td></tr>
	 
	 <tr>
	 <td>
	 Subject
	 </td>
	 <td>
	 <input type="text" name="subject" />
	 </td>
	 </tr>
	 
	 <tr>
	 <td>
	 Content
	 </td>
	 <td>
	 <input type="text" name="content" />
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
	 <input type= "submit" value="Post"></input>
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



