<?php
include 'mysql_connect.php';

$username = $_POST["uname"];
$password = $_POST["pw"];
$firstname = $_POST["fname"];
$lastname = $_POST["lname"];
$email = $_POST["mail"];

function createUser($username, $password, $firstname, $lastname, $email){
	if(!con){
		die('Could not connect: ' . mysql_error());
	}
	else{
		echo("got connection to the server and database...good job!");
		//INSERT INTO users (username, userpw, user_first_name, user_last_name, user_email);
		// VALUES ($username, $password, $firstname, $lastname, $email);
	}
	mysql_close($con);
}
?>