<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.ArrayList" %>
<%@ page import="com.ku.eecs.beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>University of Kansas Student Academics Management System</title>

</head>
<%@include  file ='header.jsp'%>
<body>
<div id ="content">
<div id="main">
<div id="welcome" class="post">
<%
ArrayList<MessageBean> al =(ArrayList<MessageBean>)request.getAttribute("messages");
%>

<% if(al.size()>0) {%>
<center><h1>Browse Messages</h1></center><br>
<center>
<table border="2" cellpadding="2" width="120%" >
<tr>
<th  bgcolor="#FF9999"><b>Message ID</b></th>
<th bgcolor="#FF9999"><b>Subject</b></th>
<th bgcolor="#FF9999"><b>Content</b></th>
<th bgcolor="#FF9999"><b>Department</b></th>
<th bgcolor="#FF9999"><b>Posted Date</b></th>
</tr>
<% 
for(MessageBean st:al){
%>
	<tr>
	<td><% out.println(st.getMessageId());%></td> 
	<td><%out.println(st.getSubject());%></td>
	<td><%out.println(st.getContent());%></td>
	<td><%out.println(st.getDepartmentName());%></td>
	<td><%out.println(st.getPostedDate());%></td>
	</tr>
<%
}
%>
</table>
<a href=<%=request.getContextPath() %>/MessageServlet?operation=loadCreateMessagePage><b>Create a New Message</b></a>
</center>
<%} else
{  %>
<center><h3>There are no messages to display!!!</h3></center><br>

<%} %>


</div>
</div>
</div>


<%@include  file ='footer.jsp'%>	

</body>
</html>
