<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <%@page import= "java.util.ArrayList" %>
 <%@page import="com.ku.eecs.beans.*" %>
<head>

	<title>University of Kansas Student Academics Management System</title>
	
	<link href="<%=request.getContextPath()%>/CSS/default_home.css"
		rel="stylesheet" type="text/css" />
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/JavaScript/menu.js"></script>
</head>

<body>
	<%
		if (session.getAttribute("theName") != null && session.getAttribute("role").toString().equalsIgnoreCase("admin")) 
		{	%>
	
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

	<div id="content">
		<br/> <br/>
		<div id="main">
			<div id="updates" class="boxed">
				<h2 class="title">Quick Links</h2>
				<div class="content">
					<ul>
						<li>
							<h3>
								<a href="<%=request.getContextPath()%>/MessageServlet?operation=retrieveAll">Manage Program Messages</a>
							</h3>
							<h5>Post/Edit notification messages on to home page of students of each department.</h5>

						</li>
						<li></li>
						<li>
							<h3>
								<a href="<%=request.getContextPath()%>/AccountRequestServlet?operation=retreive">Enable Account Requests</a>
							</h3>
							<h5>Enable the accounts requested by the students to the system.</h5>
						</li>
						<li></li>	
						<li>
							<h3>
								<a href="<%=request.getContextPath()%>/BrowseUsersServlet?operation=retrieveAll">Browse Existing Students</a>
							</h3>
							<h5>Browse/Edit the account details of the existing students. </h5>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		<p id="legal">Copyright &copy; 2015 University of Kansas. All
			Rights Reserved.</p>
	</div>
	<% }
	
	else if (session.getAttribute("theName") != null && session.getAttribute("role").toString().equalsIgnoreCase("student")) { 
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

	<div id="content">
		<br/> <br/>
		<div id="main">
			<div id="updates" class="boxed">
				<h2 class="title">Messages</h2>
				<div class="content">
				<ul>
				<% ArrayList<MessageBean> messages = (ArrayList<MessageBean>)session.getAttribute("messages"); 
					for(MessageBean message : messages)	{ %>		
						<li>
							<h2><b><%=message.getSubject() %></b></h2>
							<h4><%=message.getContent() %></h4>
							<h5>Posted On : <%=message.getPostedDate() %></h5>
						</li>
						<li></li>
						<hr/>
						<%} %>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		<p id="legal">Copyright &copy; 2015 University of Kansas. All
			Rights Reserved.</p>
	</div>
<%}
 else { %>

	<jsp:forward page='index.jsp' />
<%} %>
</body>
</html>
