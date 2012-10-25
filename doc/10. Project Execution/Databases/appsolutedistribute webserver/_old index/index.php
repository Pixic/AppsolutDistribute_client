 <?php 
 include "../mysql_connect.php";
 #$userdata = mysql_query("request_all_users.sql") or die (mysql_error());
 $userdata = mysql_query("SELECT * FROM `users` ORDER BY `userid` ASC") or die (mysql_error());
 $groupdata = mysql_query("SELECT * FROM `groups` ORDER BY `groupid` ASC") or die (mysql_error());
 $user2groupdata = mysql_query("SELECT * FROM `users2groups` ORDER BY `users_userid` ASC") or die (mysql_error());
 $featuredata = mysql_query("SELECT * FROM `features` ORDER BY `featureid` ASC") or die (mysql_error());
 $groups2featuresdata = mysql_query("SELECT * FROM `groups2features` ORDER BY `groups_groupid` ASC") or die (mysql_error());
 
 $usersingroupdata = mysql_query("SELECT * FROM `users` WHERE `userid` IN (SELECT `users_userid` FROM `users2groups` WHERE `groups_groupid`='4') ORDER BY `userid` ASC") or die (mysql_error());
 $moderatorsingroupdata = mysql_query("SELECT * FROM `users` WHERE `userid` IN (SELECT `users_moderatorid` FROM `moderator2groups` WHERE `groups_groupid`='4') ORDER BY `userid` ASC") or die (mysql_error());
 $adminsingroupdata = mysql_query("SELECT * FROM `users` WHERE `userid` IN (SELECT `users_adminid` FROM `admin2groups` WHERE `groups_groupid`='4') ORDER BY `userid` ASC") or die (mysql_error());
 $featuresingroupdata = mysql_query("SELECT * FROM `features` WHERE `featureid` IN (SELECT `features_featureid` FROM `groups2features` WHERE `groups_groupid`='4') ORDER BY `feature` ASC") or die (mysql_error());
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
	
	<link rel="stylesheet" href="../css/style.css" type="text/css" media="screen" />
</head>

<body>

<table cellspacing="3">
<tr>
<td>



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

</td>
<td class="nobg" style="width: 20pt";>
</td>
<td>

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

</td>
<td class="nobg" style="width: 20pt";>
</td>
<td>

<form action="assign_feature2group.php" method="post">
<table class="input" border="0">
<tr><th class="tableheader" colspan="2">Add Feature to Group</th></tr>
<tr>
	<td>Add FeatureID:</td>
	<td><input type="text" name="fid" /></td>
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

</td>
</tr>
<tr>
<td>

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


</td>
<td class="nobg" style="width: 20pt";>
</td>
<td>

<form action="assign_moderator2group.php" method="post">
<table class="input" border="0">
<tr><th class="tableheader" colspan="2">Make user Moderator in Group</th></tr>
<tr>
	<td>Add ModeratorID (UserID):</td>
	<td><input type="text" name="moderatorid" /></td>
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

</td>
<td class="nobg" style="width: 20pt";>
</td>
<td>

<form action="assign_admin2group.php" method="post">
<table class="input" border="0">
<tr><th class="tableheader" colspan="2">Make user Admin in Group</th></tr>
<tr>
	<td>Add AdminID (UserID):</td>
	<td><input type="text" name="adminid" /></td>
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

</td>
<td class="nobg" style="width: 20pt";>
</td>
<td>


</td>
</tr>
</table>
<hr style="color: black, size: 1px">


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
Print "<td>".$users['userid'] . "</td> ";
Print "<td>".$users['username'] . "</td> ";
Print "<td>".$users['userpw'] . "</td> ";
Print "<td>".$users['user_first_name'] . "</td> ";
Print "<td>".$users['user_last_name'] . "</td> ";
Print "<td>".$users['user_email'] . "</td>";
Print "</tr>";
}
Print "</table>";


Print "</td><td>";


Print "<table class=\"border1\" cellpadding=3>";
Print "<tr><th class=\"tableheader\" colspan=\"4\">GROUPS in appdist.groups</th></tr>";
Print "<tr>";
Print "<th>Group ID</th>";
Print "<th>Group name</th>";
Print "</tr>";
while($groups = mysql_fetch_array( $groupdata ))
{ 
Print "<tr class=\"hover\">";
Print "<td>".$groups['groupid'] . "</td> ";
Print "<td>".$groups['groupname'] . "</td> ";
Print "</tr>";
}
Print "</table>";


Print "</td><td>";


Print "<table class=\"border1\" cellpadding=3>";
Print "<tr><th class=\"tableheader\" colspan=\"4\">FEATURES in appdist.features</th></tr>";
Print "<tr>";
Print "<th>Feature ID</th>";
Print "<th>Feature</th>";
Print "</tr>";
while($features = mysql_fetch_array( $featuredata ))
{ 
Print "<tr class=\"hover\">";
Print "<td>".$features['featureid'] . "</td> ";
Print "<td>".$features['feature'] . "</td>";
Print "</tr>";
}
Print "</table>";

Print "</td></tr></table>";

Print "<hr>";

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

Print "</td><td>";

Print "<table><tr><td>";
Print "<table class=\"border1\" cellpadding=3>";
Print "<tr><th class=\"tableheader\" colspan=\"12\">Users in Group 4 appdist.users2groups</th></tr>";
Print "<tr>";
Print "<th>User ID</th>";
Print "<th>Username</th>";
Print "<th>Password</th>";
Print "<th>First name</th>";
Print "<th>Last name</th>";
Print "<th>Email</th>";
Print "</tr>";
while($usersingroup4 = mysql_fetch_array( $usersingroupdata ))
{ 
Print "<tr class=\"hover\">";
Print "<td>".$usersingroup4['userid'] . "</td> ";
Print "<td>".$usersingroup4['username'] . "</td> ";
Print "<td>".$usersingroup4['userpw'] . "</td> ";
Print "<td>".$usersingroup4['user_first_name'] . "</td> ";
Print "<td>".$usersingroup4['user_last_name'] . "</td> ";
Print "<td>".$usersingroup4['user_email'] . "</td> ";
Print "</tr>";
}
Print "</table>";
Print "</td></tr><tr><td>";
Print "<table class=\"border1\" cellpadding=3>";
Print "<tr><th class=\"tableheader\" colspan=\"12\">Moderators in Group 4 appdist.moderator2groups</th></tr>";
Print "<tr>";
Print "<th>User ID</th>";
Print "<th>Username</th>";
Print "<th>Password</th>";
Print "<th>First name</th>";
Print "<th>Last name</th>";
Print "<th>Email</th>";
Print "</tr>";
while($moderatorsingroup4 = mysql_fetch_array( $moderatorsingroupdata ))
{ 
Print "<tr class=\"hover\">";
Print "<td>".$moderatorsingroup4['userid'] . "</td> ";
Print "<td>".$moderatorsingroup4['username'] . "</td> ";
Print "<td>".$moderatorsingroup4['userpw'] . "</td> ";
Print "<td>".$moderatorsingroup4['user_first_name'] . "</td> ";
Print "<td>".$moderatorsingroup4['user_last_name'] . "</td> ";
Print "<td>".$moderatorsingroup4['user_email'] . "</td> ";
Print "</tr>";
}
Print "</table>";
Print "</td></tr><tr><td>";
Print "<table class=\"border1\" cellpadding=3>";
Print "<tr><th class=\"tableheader\" colspan=\"12\">Admins in Group 4 appdist.moderator2groups</th></tr>";
Print "<tr>";
Print "<th>User ID</th>";
Print "<th>Username</th>";
Print "<th>Password</th>";
Print "<th>First name</th>";
Print "<th>Last name</th>";
Print "<th>Email</th>";
Print "</tr>";
while($adminsingroup4 = mysql_fetch_array( $adminsingroupdata ))
{ 
Print "<tr class=\"hover\">";
Print "<td>".$adminsingroup4['userid'] . "</td> ";
Print "<td>".$adminsingroup4['username'] . "</td> ";
Print "<td>".$adminsingroup4['userpw'] . "</td> ";
Print "<td>".$adminsingroup4['user_first_name'] . "</td> ";
Print "<td>".$adminsingroup4['user_last_name'] . "</td> ";
Print "<td>".$adminsingroup4['user_email'] . "</td> ";
Print "</tr>";
}
Print "</table>";
Print "</td></tr></table>";


Print "</td></tr><tr><td>";

Print "<table class=\"border1\" cellpadding=3>";
Print "<tr><th class=\"tableheader\" colspan=\"4\">FEATURES by GROUPS in appdist.groups2features</th></tr>";
Print "<tr>";
Print "<th>Group ID</th>";
Print "<th>Feature ID</th>";
Print "</tr>";
while($groups2features = mysql_fetch_array( $groups2featuresdata ))
{ 
Print "<tr class=\"hover\">";
Print "<td>".$groups2features['groups_groupid'] . "</td> ";
Print "<td>".$groups2features['features_featureid'] . "</td>";
Print "</tr>";
}
Print "</table>";

Print "</td><td>";

Print "<table class=\"border1\" cellpadding=3>";
Print "<tr><th class=\"tableheader\" colspan=\"2\">Features in Group 4 appdist.groups2features</th></tr>";
Print "<tr>";
Print "<th>Feature name</th>";
Print "</tr>";
while($featuresingroup4 = mysql_fetch_array( $featuresingroupdata ))
{ 
Print "<tr class=\"hover\">";
Print "<td>".$featuresingroup4['feature'] . "</td> ";
Print "</tr>";
}
Print "</table>";

Print "</td></tr></table>";

Print "<hr>";
?>
</td></tr></table>
</html>