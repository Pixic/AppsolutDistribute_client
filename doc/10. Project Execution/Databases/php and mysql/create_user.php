<?php
include 'mysql_connect.php';


$username = $_POST["uname"];
$password = $_POST["pw"];
$firstname = $_POST["fname"];
$lastname = $_POST["lname"];
$email = $_POST["mail"];
if(!$con){
	die('Could not connect: ' . mysql_error());
}
else{
	mysql_query("INSERT INTO `appdist`.`users` VALUES('','$username', '$password', '$firstname', '$lastname', '$email')") or die('Could not connect: ' . mysql_error());
	echo ("The user has been added. Please go <a href=\"index.php\">back</a> to see your new account.");
}
?>