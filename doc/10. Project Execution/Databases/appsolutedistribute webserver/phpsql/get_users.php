<?php
include 'mysql_connect.php';

$userdata = mysql_query("SELECT * FROM `users` ORDER BY `userid` ASC") or die (mysql_error());
echo "$userdata";
?>