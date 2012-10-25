<?php 
include "mysql_connect.php";
$groupdata = mysql_query("SELECT * FROM `groups` ORDER BY `groupid` ASC") or die (mysql_error());
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

<form action="create_group.php" method="post">
<table class="input">
<tr><th class="tableheader" colspan="2">Add Group</th></tr>
<tr>
	<td>Group name:</td>
	<td><input type="text" name="gname" /></td>
</tr>
<tr><td><input type="submit" /></td></tr>
</tr></table>
</form>

<!-- Divisor START -->
</p>
<hr style="color: black, size: 1px">
<p>
<!-- Divisor END -->

<?php
Print "<table class=\"border1\" cellpadding=3>";
Print "<tr><th class=\"tableheader\" colspan=\"4\">GROUPS in appdist.groups</th></tr>";
Print "<tr>";
Print "<th>Group ID</th>";
Print "<th>Group name</th>";
Print "</tr>";
while($groups = mysql_fetch_array( $groupdata ))
{ 
Print "<tr class=\"hover\">";
Print "<td align='center'>".$groups['groupid'] . "</td> ";
Print "<td>".$groups['groupname'] . "</td> ";
Print "</tr>";
}
Print "</table>";
?>

</p>
<!-- "Main Page" ENDS -->
</td></tr></table>
</html>