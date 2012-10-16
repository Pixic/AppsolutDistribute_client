<?php
include 'mysql_connect.php';

$userid = $_POST["uid"];
$groupid = $_POST["gid"];
if(!$con){
	die('Could not connect: ' . mysql_error());
}
else{
	mysql_query("INSERT INTO `appdist`.`users2groups` VALUES('$userid','$groupid')") or die('Could not connect: ' . mysql_error());
	echo ("The user $userid has been added to group $groupid. Please go <a href=\"index.php\">back</a> to see your new account.");
}
?>