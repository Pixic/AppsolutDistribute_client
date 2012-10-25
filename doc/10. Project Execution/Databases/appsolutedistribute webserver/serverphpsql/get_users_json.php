<?php
include '../phpsql/mysql_connect.php';
$userdata = mysql_query("SELECT * FROM `users` ORDER BY `userid` ASC") or die (mysql_error());

$rows = array();
while($row = mysql_fetch_assoc($userdata)) {
    $rows[] = $row;
}
print json_encode($rows);
?>