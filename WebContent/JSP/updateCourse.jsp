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
	var semester = document.f2.semester.value;
	var year = document.f2.year.value;

	if(semester=="sb")
	{
		alert("Please Select a Semester");
		return false;
	}
	if(year=="sb")
	{
		alert("Please Select a Year");
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
	<% CourseBean cb = (CourseBean) request.getAttribute("course");%>
	<center><h1>Update Course</h1></center><br>
	
	<%if(request.getAttribute("status")!= null && request.getAttribute("status").equals("success"))
	{
		UserBean ub = (UserBean)session.getAttribute("userBean");
	%>
	<h3>Course Details Updated Successfully!!!<br/><br/><br/></h3>
	<a href=<%=request.getContextPath()%>/MyDataServlet?page=courses&operation=loadCourses&userId=<%=ub.getUserId()%>><b>View All Courses</b></a>
	
	<% } 
	else if(request.getAttribute("status")!= null && request.getAttribute("status").equals("failure")){	 
	%>
		<h3>Currently we are facing an issue updating your request. Please try again after some time!!!<br/><br/><br/></h3>
	<% }
	 else {%>
	
	<form name ="f2" method =post action="<%=request.getContextPath() %>/MyDataServlet" onsubmit="return validate()">
	 <table align ="center" border =0>
	 <tr><td> <div style="color:red">* fields are required fields</div></tr>
	 <tr><td> <br></td></tr>
	 
	 <tr>
	 <td>
	 Course Number
	 </td>
	 <td>
	 <input type="text" name="courseNumber" readonly value=<%=cb.getCourseNumber() %>/>
	 </td>
	 </tr>
	 
	 <tr>
	 <td>
	 Course Name
	 </td>
	 <td>
	 <input type="text" name="courseName" readonly value=<%=cb.getCourseName() %>/>
	 </td>
	 </tr>
	 
	 <tr>
	 <td>
	 Instructor Name
	 </td>
	 <td>
	 <input type="text" name="instructorName" readonly value=<%=cb.getInstructorName()%>/>
	 </td>
	 </tr>
	 
	 <tr>
	 <td>
	 Semester*
	 </td>
	 <td>
	 <select type="text" name="semester" >
	 <option value="sb">--Select a Semester--</option>
	 <option value="Fall">Fall</option>
	 <option value="Spring">Spring</option>
	 <option value="Summer">Summer</option>
	 </select>
	 </td>
	 </tr>
	 
	 <tr>
	 <td>
	 Year*
	 </td>
	 <td>
	 <select type="text" name="year" >
	 <option value="sb">--Select a Year--</option>
	 <option value="2010">2010</option>
	 <option value="2011">2011</option>
	 <option value="2012">2012</option>
	 <option value="2013">2013</option>
     <option value="2014">2014</option>
	 <option value="2015">2015</option>
	 <option value="2016">2016</option>
	 </select>
	 </td>
	 </tr>
	 
	 <tr>
	 <td>
	 GPA
	 </td>
	 <td>
	 <input type="text" name="gpa" value=<%=cb.getGpa()%> />
	 </td>
	 </tr>
	 <tr><td> <br></td></tr>
	 <tr>
	 <td></td>
	 <td>
	 <input type= "hidden" name="page" value="courses">
	  <input type= "hidden" name="courseId" value=<%=cb.getCourseId() %>>
	 <input type= "hidden" name="operation" value="updateCourse">
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



