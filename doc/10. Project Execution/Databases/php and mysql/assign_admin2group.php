<?php
include 'mysql_connect.php';

$adminid = $_POST["adminid"];
$groupid = $_POST["gid"];
if(!$con){
	die('Could not connect: ' . mysql_error());
}
else{
	mysql_query("INSERT INTO `appdist`.`admin2groups` VALUES('$adminid','$groupid')") or die('Could not connect: ' . mysql_error());
	echo ("The user $adminid has been added to group $groupid. Please go <a href=\"index.php\">back</a> to see updated information.");
}
?>