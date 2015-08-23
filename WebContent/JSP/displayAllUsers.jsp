<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import= "java.util.ArrayList" %>
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
<div id="main">
<div id="welcome" class="post">
<%
ArrayList<UserBean> al =(ArrayList<UserBean>)request.getAttribute("users");
%>

<% if(al.size()>0) {%>
<center><h1>Browse Students</h1></center><br>
<center>
<table border="0" cellpadding="2" width="120%" >
<tr>
<th align="center"><b>Name</b></th>
<th align="center"><b>KU ID</b></th>
<th align="center"><b>Department</b></th>
</tr>
<% 
for(UserBean st:al){
%>
	<tr>
	<td><a href=<%=request.getContextPath() %>/MyDataServlet?page=courses&operation=loadCourses&userId=<%=st.getUserId() %>><b><%out.println(st.getUserName());%></b></a></td> 
	<td><%out.println(st.getKuId());%></td>
	<td><%out.println(st.getDepartmentName());%></td>
	</tr>
<%
}
%>
</table>
</center>

<%} %>


</div>
</div>
</div>


<%@include  file ='footer.jsp'%>	

</body>
</html>
