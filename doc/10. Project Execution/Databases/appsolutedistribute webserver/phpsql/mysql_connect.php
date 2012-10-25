<?php
// Connects to appdist database
$db_host = "localhost";
$db_username = "root";
$db_pass = "tjenatjena1";
$db_name = "appdist";

$con = @mysql_connect("$db_host","$db_username","$db_pass") or die ("Could not connect to MySQL<p>" . mysql_error() );
$condb = @mysql_select_db("$db_name") or die ("No database<p>" . mysql_error());

//echo "Successful Connection - Connected to MySQL Database and the appdist database";
?>