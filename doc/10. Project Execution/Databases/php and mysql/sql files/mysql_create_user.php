<?php
// DENNA SKA MODIFIERAS TILL EN MySQL-fr�ge-funktion 
// f�r att kunna ta emot indata fr�n Android...
include 'mysql_connect.php';
function createUser( $username , $password ,$firstname , $lastname , $email){
	if(!$con){
		die('Could not connect: ' . mysql_error());
	}
	else{
		mysql_query("INSERT INTO `appdist`.`users` VALUES('','$username', '$password', '$firstname', '$lastname', '$email')") or die('Could not connect: ' . mysql_error());
		echo ("The user has been added. Please go <a href=\"index.php\">back</a> to see your new account.");
	}
}
?>