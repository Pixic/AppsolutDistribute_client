<?php
include '../phpsql/mysql_connect.php';
$userdata = mysql_query("SELECT * FROM `users` ORDER BY `userid` ASC") or die (mysql_error());

while($users = mysql_fetch_array( $userdata ))
{ 
Print "<row>";
Print "<userid>".$users['userid']."</userid>";
Print "<username>".$users['username']."</username>";
Print "<userpw>".$users['userpw']."</userpw>";
Print "<user_first_name>".$users['user_first_name']."</user_first_name> ";
Print "<user_last_name>".$users['user_last_name'] . "</user_last_name> ";
Print "<user_email>".$users['user_email']."</user_email>";
Print "</row>";
}
?>