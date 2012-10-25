<?php 
include "mysql_connect.php";
$user2groupdata = mysql_query("SELECT * FROM `users2groups` ORDER BY `users_userid` ASC") or die (mysql_error());
$names_user2groupdata = mysql_query("(SELECT `username` FROM `users` WHERE `userid` IN (SELECT `users_userid` FROM `users2groups`)) UNION (SELECT `groupname` FROM `groups` WHERE `groupid` IN (SELECT `groups_groupid` FROM `users2groups`)) ORDER BY `username` ASC") or die (mysql_error());
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

<form action="assign_user2group.php" method="post">
<table class="input" border="0">
<tr><th class="tableheader" colspan="2">Add User to Group</th></tr>
<tr>
	<td>Add UserID:</td>
	<td><input type="text" name="uid" /></td>
</tr><tr>
	<td>...to GroupID:</td>
	<td><input type="text" name="gid" /></td>
</tr>
<tr>	
	<td>
	<input type="submit" /><br/>
	</td>
</tr>
</table>
</form>

<!-- Divisor START -->
</p>
<hr style="color: black, size: 1px">
<p>
<!-- Divisor END -->

<?php
Print "<table cellpadding=3><tr><td>";
Print "<table class=\"border1\" cellpadding=3>";
Print "<tr><th class=\"tableheader\" colspan=\"4\">UserID in GroupID appdist.users2groups</th></tr>";
Print "<tr>";
Print "<th>User ID</th>";
Print "<th>Group ID</th>";
Print "</tr>";
while($users2groups = mysql_fetch_array( $user2groupdata ))
{ 
Print "<tr class=\"hover\">";
Print "<td>".$users2groups['users_userid'] . "</td> ";
Print "<td>".$users2groups['groups_groupid'] . "</td>";
Print "</tr>";
}
Print "</table>";
?>

<!--
mysql_query("SELECT * FROM `users2groups` ORDER BY `users_userid` ASC")
mysql_query("SELECT * FROM `users` WHERE `userid` IN (SELECT `users_userid` FROM `users2groups` WHERE `groups_groupid`='4') ORDER BY `userid` ASC")
-->
<?php
Print "<table cellpadding=3><tr><td>";
Print "<table class=\"border1\" cellpadding=3>";
Print "<tr><th class=\"tableheader\" colspan=\"4\">UserID in GroupID appdist.users2groups</th></tr>";
Print "<tr>";
Print "<th>User ID</th>";
Print "<th>Group ID</th>";
Print "</tr>";
while($users2groups = mysql_fetch_array( $user2groupdata ))
{
$u2g_user = $users2groups['users_userid'];
$u2g_group = $users2groups['groups_groupid']
$uname=mysql_query("SELECT `username` FROM `users` WHERE `userid` EQUALS $u2g_user");
$gname=mysql_query("SELECT `groupname` FROM `groups` WHERE `groupid` = $u2g_group");
$us = mysql_fetch_array($uname);
$gr = mysql_fetch_array($gname);

Print "<tr class=\"hover\">";
Print "<td>".$us['username']. "</td> ";
Print "<td>".$gr['groupname']. "</td>";
Print "</tr>";
}
Print "</table>";
?>


<!--?php
Print "<table cellpadding=3><tr><td>";
Print "<table class=\"border1\" cellpadding=3>";
Print "<tr><th class=\"tableheader\" colspan=\"4\">UserID in GroupID appdist.users2groups</th></tr>";
Print "<tr>";
Print "<th>User ID</th>";
Print "<th>Group ID</th>";
Print "</tr>";
while($usernames2groupnames = mysql_fetch_array( $names_user2groupdata ))
{ 
Print "<tr class=\"hover\">";
Print "<td>".$usernames2groupnames['username'] . "</td> ";
Print "<td>".$usernames2groupnames['groupname'] . "</td>";
Print "</tr>";
}
Print "</table>";
?-->



</p>
<!-- "Main Page" ENDS -->
</td></tr></table>
</html>