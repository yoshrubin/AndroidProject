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
$attraction = $_REQUEST["attraction"];
$country = $_REQUEST["country"];
$startdate = $_REQUEST["startdate"];
$enddate = $_REQUEST["enddate"];
$price = $_REQUEST["price"];
$description = $_REQUEST["description"];
$idn = $_REQUEST["IDN"];
$user = $_REQUEST["user"];
$sql = "INSERT INTO `rubinj_aviv`.`action_table` (`attraction`,
`country`, `startdate`, `enddate`, `price`, `description`, `IDN`, `user`, `last_update`) VALUES (\"$attraction\", \"$country\", \"$startdate\", \"$enddate\", \"$price\", \"$description\", \"$idn\", \"$user\", CURRENT_TIMESTAMP)";
if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();
?>