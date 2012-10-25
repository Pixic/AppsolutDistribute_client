<?php
include '../phpsql/mysql_connect.php';

$username = $_POST["uname"];
$userdata=mysql_query("SELECT * FROM `users` WHERE `username` = '".$username."'")
or die (mysql_error());

$rows = array();
while($row = mysql_fetch_assoc($userdata)) {
    $rows[] = $row;
}
print json_encode($rows);
?>

<!--SELECT * FROM `users` ORDER BY `userid` ASC -->