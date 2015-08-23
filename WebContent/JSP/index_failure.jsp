<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html >
<head>

<title>University of Kansas Student Academics Management System</title>

<link href="<%=request.getContextPath() %>/CSS/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function validate()
{
	var uname= document.form1.t1.value;
	var pwd= document.form1.t2.value;
	
	if(uname=="")
	{
	 alert("Please enter the username");
		return false;
	}
	if(pwd=="")
	{
		alert("Please enter the password");
		return false;
	}
	
}
</script>
</head>


<body>

<div id="header">

	<div id="logo">
		<h1>University of Kansas</h1>
		<h2>Student Profile Management System</h2>
	</div>

	
	

</div>


<div id="content">
	<div id="sidebar">
		<div id="login" class="boxed">
			<h2 class="title">Login</h2>
			<div class="content">
				<form id="form1" name="form1" method="post" action="<%=request.getContextPath() %>/LoginServlet" onsubmit="return validate()">
					<fieldset>
					<legend>Sign-In</legend>
					<label for="inputtext1">User Name:</label>
					<input id="inputtext1" type="text" name="t1" value="" />
					<label for="inputtext2">Password:</label>
					<input id="inputtext2" type="password" name="t2" value="" />
					<p><font color="red" size="2">*Your Details are Invalid...<br/>
					please enter a valid User Name or Password</font></p>
					<input id="inputsubmit1" type="submit" name="inputsubmit1" value="Log In" />
					<a href="<%=request.getContextPath()  %>/AccountRequestServlet?operation=load"><b>Request a New Account</b></a>
					</fieldset>
				</form>
			</div>
		</div>
		
	</div>
	<div id="main">
		<div id="welcome" class="post">
			<h2 class="title">Welcome to Student Profile  Management System</h2>
			
			<div class="story">
				<img src="<%=request.getContextPath() %>/images/KU logo.png" alt="" width="120" height="120" class="left" />
				<p>This system provides features that allows the Administrators and Students of University to update 
				and keep track of the students academic information. Based on the user type(i.e., Admin or Student), 
				the privileges provided will be differed.</p>
			</div>
			
		</div>
		
	</div>
</div>
<div id="footer">
	<p id="legal">Copyright &copy; 2015 University of Kansas. All Rights Reserved. </p>
	
</div>
</body>
</html>
