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
$update = $_REQUEST["update"];
$sql = "INSERT INTO `rubinj_aviv`.`updated` (`last_update`) VALUES (CURRENT_TIMESTAMP)";
if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();
?>