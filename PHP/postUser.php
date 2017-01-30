<?php
header('Content-Type: text/html; charset=utf-8');
$servername = "localhost";
$username = "yoshaviv";
$password = "rubinbentura";
$dbname = "rubinj_aviv";
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
mysql_query("SET NAMES 'utf8'", $conn);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
$usename = $_REQUEST["name"];
$passwd= $_REQUEST["password"];
$sql = "INSERT INTO `rubinj_aviv`.`user_table` (`name`,
`password`) VALUES (\"$usename\", \"$passwd\")";
if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();
?>