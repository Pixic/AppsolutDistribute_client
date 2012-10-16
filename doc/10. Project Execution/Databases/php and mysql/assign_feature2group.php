<?php
include 'mysql_connect.php';
$featureid = $_POST["fid"];
$groupid = $_POST["gid"];
if(!$con){
	die('Could not connect: ' . mysql_error());
}
else{
	mysql_query("INSERT INTO `appdist`.`groups2features` VALUES('$groupid','$featureid')") or die('Could not connect: ' . mysql_error());
	echo ("The user $featureid has been added to group $groupid. Please go <a href=\"index.php\">back</a> to see updated info...");
}
?>