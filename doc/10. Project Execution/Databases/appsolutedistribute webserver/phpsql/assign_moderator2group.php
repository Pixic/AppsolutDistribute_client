<?php
include 'mysql_connect.php';

$moderatorid = $_POST["moderatorid"];
$groupid = $_POST["gid"];
if(!$con){
	die('Could not connect: ' . mysql_error());
}
else{
	mysql_query("INSERT INTO `appdist`.`moderator2groups` VALUES('$moderatorid','$groupid')") or die('Could not connect: ' . mysql_error());
	echo ("The user $moderatorid has been added to group $groupid. Please go <a href=\"index.php\">back</a> to see updated information.");
}
?>