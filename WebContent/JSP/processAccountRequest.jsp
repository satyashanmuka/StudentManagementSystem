<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ku.eecs.beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>University of Kansas Student Academics Management System</title>

</head>
<%@include  file ='header.jsp'%>
<body>

<div id ="content">
<%
AccountRequestBean ab = (AccountRequestBean)request.getAttribute("accountRequest");

 //System.out.println("the size of arraylist is"+al.size());
%>

<center><h1>Account Request Details</h1></center><br>
<center>

<table border="0" cellpadding="2" width="120%" align="center">
<tr><td ><b>Request ID</b></td><td><% out.println(ab.getRequestId());%></td></tr> 
<tr><td ><b>First Name</b></td><td><%out.println(ab.getFirstName());%></td></tr>
<tr><td ><b>Last Name</b></td><td><%out.println(ab.getLastName());%></td></tr>
<tr><td ><b>Requested Date</b></td><td><%out.println(ab.getRequestedDate());%></td></tr>
<tr><td ><b>KU ID</b></td><td><%out.println(ab.getKuId());%></td></tr>
<tr><td ><b>KU Online ID</b></td><td><%out.println(ab.getKuOnlineId());%></td></tr>
<tr><td ><b>Email ID</b></td><td><%out.println(ab.getEmailId());%></td></tr>
<tr><td ><b>Department</b></td><td><%out.println(ab.getDepartmentName());%></td></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>	
<tr>
<td>
 &nbsp;&nbsp;&nbsp;&nbsp; 
 <a  href="<%=request.getContextPath() %>/AccountRequestServlet?operation=approve&requestId=<%=ab.getRequestId()%>">Approve</a>
</td>

<td>
 &nbsp;&nbsp;&nbsp;&nbsp;
 
  <a  href="<%=request.getContextPath() %>/AccountRequestServlet?operation=reject&requestId=<%=ab.getRequestId()%>">Reject</a>
</td>
</tr>
</table>
</center>
</div>
<%@include  file ='footer.jsp'%>	

</body>
</html>
</body>
</html>