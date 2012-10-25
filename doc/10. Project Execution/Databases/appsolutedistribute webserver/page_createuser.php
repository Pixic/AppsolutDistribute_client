 <?php 
 include "mysql_connect.php";
 $userdata = mysql_query("SELECT * FROM `users` ORDER BY `userid` ASC") or die (mysql_error());
 ?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<TITLE> AppsolutDistribute</TITLE>
	<META NAME="Author" CONTENT="Mattias Isene - for Group25 in DAT255 course at Chalmers">
	<META NAME="Subject" CONTENT="AppsoluteDistribute Android App - Databases">
	<META NAME="Description" CONTENT="This webserver is setup for handling users and groups for Android app AppsolutDistribute">
	<META NAME="Keywords" CONTENT="Android, App, AppsolutDistribute, Appsolut, Distribute">
	<META NAME="Language" CONTENT="en-us">
	<META NAME="Abstract" CONTENT="AppsolutDistribute Android App">
	<META NAME="Copyright" CONTENT="© Copyright Mattias Isene">
	<META NAME="Designer" CONTENT="Mattias Isene">
	<META NAME="Revisit-After" CONTENT="30 Days">
	<META NAME="Distribution" CONTENT="Global">
	<META NAME="Robots" CONTENT="INDEX, FOLLOW">
	<META NAME="RATING" CONTENT="General">
	<META NAME="GOOGLEBOT" CONTENT="NOARCHIVE">
	<META NAME="ROBOTS" CONTENT="NOARCHIVE">
	<META HTTP-EQUIV="Pragma" CONTENT="NO">
	<META HTTP-EQUIV="imagetoolbar" CONTENT="NO">
	<META HTTP-EQUIV="Content-Type" content="text/html; charset=iso-8859-1">
  <meta http-equiv="content-type" content="text/html; charset=windows-1250">
  <meta name="generator" content="notepad">
	
	<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
</head>

<body>
<!-- Table for all page -->
<table cellspacing="3">
<tr>
<td>
<div class="menu"><?php include("menu.php"); ?></div>
</td>
<td>



<!-- "Main Page" STARTS -->
<p>

<form action="create_user.php" method="post">
<table class="input">
<tr><th class="tableheader" colspan="2">Create account (user)</th></tr>
<tr>
	<td>Username:</td>
	<td><input type="text" name="uname" onchange="javascript: regex(this.value);" /></td>
</tr><tr>
	<td>Password:</td>
	<td><input type="text" name="pw" /></td>
</tr><tr>
	<td>First Name:</td>
	<td><input type="text" name="fname" /></td>
</tr><tr>
	<td>Last Name:</td>
	<td><input type="text" name="lname" /></td>
</tr><tr>
	<td>Email:</td>
	<td><input type="text" name="mail" /></td>
</tr><tr>
	<td colspan="2"><input type="submit" /></td>
</tr></table>
</form>

<!-- Divisor START -->
</p>
<hr style="color: black, size: 1px">
<p>
<!-- Divisor END -->


<?php
Print "<table cellpadding=3><tr><td>";
Print "<table class=\"border1\" cellpadding=3>";
Print "<tr><th class=\"tableheader\" colspan=\"6\">USERS in appdist.users :</th></tr>";
Print "<tr>";
Print "<th>User ID</th>";
Print "<th>Username</th>";
Print "<th>Password</th>";
Print "<th>First name</th>";
Print "<th>Last name</th>";
Print "<th>Email</th></tr>";
while($users = mysql_fetch_array( $userdata ))
{ 
Print "<tr class=\"hover\">";
Print "<td align='center'>".$users['userid'] . "</td> ";
Print "<td>".$users['username'] . "</td> ";
Print "<td>".$users['userpw'] . "</td> ";
Print "<td>".$users['user_first_name'] . "</td> ";
Print "<td>".$users['user_last_name'] . "</td> ";
Print "<td>".$users['user_email'] . "</td>";
Print "</tr>";
}
Print "</table>";
?>

</p>
<!-- "Main Page" ENDS -->
</td></tr></table>
</html>