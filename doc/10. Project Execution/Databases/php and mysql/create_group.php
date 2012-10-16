<?php
include 'mysql_connect.php';

$groupname = $_POST["gname"];

if(!$con){
	die('Could not connect: ' . mysql_error());
}
else{
	mysql_query("INSERT INTO `appdist`.`groups` VALUES('','$groupname')") or die('Could not connect: ' . mysql_error());
	echo ("The GROUP has been added. Please go <a href=\"index.php\">back</a> to see your new account.");
}
?>