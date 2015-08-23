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
ArrayList<AccountRequestBean> al =(ArrayList<AccountRequestBean>)request.getAttribute("accountRequests");
%>

<% if(al.size()>0) {%>
<center><h1>Active Account Requests</h1></center><br>
<center>
<form name ="f1" method ="post" action="<%=request.getContextPath() %>/AccountRequestServlet">
<table border="2" cellpadding="2" width="120%" >
<tr>
<th  bgcolor="#FF9999"><b>Request ID</b></th>
<th bgcolor="#FF9999"><b>First Name</b></th>
<th bgcolor="#FF9999"><b>Last Name</b></th>
<th bgcolor="#FF9999"><b>Requested Date</b></th>
<th bgcolor="#FF9999"><b>KU ID</b></th>
<th bgcolor="#FF9999"><b>KU Online ID</b></th>
<th bgcolor="#FF9999"><b>Email ID</b></th>
<th bgcolor="#FF9999"><b>Decision</b></th>
</tr>
<% 
for(AccountRequestBean st:al){
%>
	<tr>
	<td><% out.println(st.getRequestId());%></td> 
	<td><%out.println(st.getFirstName());%></td>
	<td><%out.println(st.getLastName());%></td>
	<td><%out.println(st.getRequestedDate());%></td>
	<td><%out.println(st.getKuId());%></td>
	<td><%out.println(st.getKuOnlineId());%></td>
	<td><%out.println(st.getEmailId());%></td>
	<td><a href=<%=request.getContextPath() %>/AccountRequestServlet?operation=processRequest&requestId=<%=st.getRequestId()%>>Process Request</a>
	</td>
	</tr>
<%
}
%>
</table>
</form>
</center>
<%} else
{  %>
<center><h3>There are no account requests pending currently!!!</h3></center><br>

<%} %>


</div>
</div>
</div>


<%@include  file ='footer.jsp'%>	

</body>
</html>
