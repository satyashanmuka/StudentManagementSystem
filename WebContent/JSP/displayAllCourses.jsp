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
ArrayList<CourseBean> al =(ArrayList<CourseBean>)request.getAttribute("courses");
%>

<% if(al.size()>0) {%>
<center><h1>Courses</h1></center><br>
<center>
<table border="2" cellpadding="2" width="120%" >
<tr>
<th  bgcolor="#FF9999"><b>Course Number</b></th>
<th bgcolor="#FF9999"><b>Course Name</b></th>
<th bgcolor="#FF9999"><b>Semester</b></th>
<th bgcolor="#FF9999"><b>Year</b></th>
<th bgcolor="#FF9999"><b>Instructor Name</b></th>
<th bgcolor="#FF9999"><b>GPA</b></th>
<th bgcolor="#FF9999"><b>Action</b></th>

</tr>
<% 
for(CourseBean st:al){
%>
	<tr>
	<td><%out.println(st.getCourseNumber());%></td> 
	<td><%out.println(st.getCourseName());%></td>
	<td><%out.println(st.getSemester());%></td>
	<td><%out.println(st.getYear());%></td>
	<td><%out.println(st.getInstructorName());%></td>
	<td><%out.println(st.getGpa());%></td>
	<td><a href=<%=request.getContextPath() %>/MyDataServlet?page=courses&operation=updateCourse&userId=<%=st.getUserId() %>&courseId=<%=st.getCourseId()%>><b>Edit</b></a>
	<br/>
	<a href=<%=request.getContextPath() %>/MyDataServlet?page=courses&operation=deleteCourse&userId=<%=st.getUserId() %>&courseId=<%=st.getCourseId() %>><b>Remove</b></a>
	</td>
	</tr>
<%
}
%>
</table>
<a href=<%=request.getContextPath() %>/JSP/addNewCourse.jsp?status=initial><b>Add a New Course</b></a>
</center>

<%} %>


</div>
</div>
</div>


<%@include  file ='footer.jsp'%>	

</body>
</html>
