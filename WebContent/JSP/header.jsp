<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import= "java.util.ArrayList" %>
<%@page import="com.ku.eecs.beans.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>University of Kansas Student Academics Management System</title>

<link href="<%=request.getContextPath() %>/CSS/default_home.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/JavaScript/menu.js"></script>
</head>
<body>
<%if(session.getAttribute("theName")!=null && session.getAttribute("role").toString().equalsIgnoreCase("admin"))
{ %>
	
<div id="header">
		<h2>
			<b>Logged in as <%=session.getAttribute("theName")%></b>
		</h2>
		<div id="menu">
			<div id="ddtopmenubargutter" class="menugutterleft"
				style="width: 190px">&nbsp;</div>
			<div id="ddtopmenubar">
				<ul>
					<li><a href="<%=request.getContextPath()%>/JSP/home_menu.jsp">Home</a></li>
					<li><a href="<%=request.getContextPath()%>/MyDataServlet?page=courses&operation=retrieveAll">Browse Academic Data</a></li>
					<li><a href="<%=request.getContextPath()%>/JSP/accountSettings.jsp">Account Settings</a></li>
					<li><a href="<%=request.getContextPath()%>/JSP/logout.jsp">LogOut</a></li>
				</ul>
			</div>
		</div>
</div>
<%}
else if(session.getAttribute("theName")!=null && session.getAttribute("role").toString().equalsIgnoreCase("student"))
{ 
	UserBean ub = (UserBean)session.getAttribute("userBean");
%>
<div id="header">
		<h2>
			<b>Logged in as <%=session.getAttribute("theName")%></b>
		</h2>
		<div id="menu">
			<div id="ddtopmenubargutter" class="menugutterleft"
				style="width: 190px">&nbsp;</div>
			<div id="ddtopmenubar">
				<ul>
					<li><a href="<%=request.getContextPath()%>/JSP/home_menu.jsp">Home</a></li>
					<li><a href="<%=request.getContextPath()%>/MyDataServlet?page=courses&operation=loadCourses&userId=<%=ub.getUserId()%>">My Data</a></li>
					<li><a href="<%=request.getContextPath()%>/JSP/accountSettings.jsp">Account Settings</a></li>
					<li><a href="<%=request.getContextPath()%>/JSP/logout.jsp">LogOut</a></li>
				</ul>
			</div>
		</div>
</div>
<%}
 else{
	 %>
 <jsp:forward page='index.jsp' /><%} %>
</body>
</html>