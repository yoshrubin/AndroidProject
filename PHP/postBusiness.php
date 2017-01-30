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
$idn = $_REQUEST["IDN"];
$name = $_REQUEST["name"];
$country = $_REQUEST["country"];
$city = $_REQUEST["city"];
$street = $_REQUEST["street"];
$housenum = $_REQUEST["housenum"];
$phoneNum = $_REQUEST["phoneNum"];
$email = $_REQUEST["email"];
$site = $_REQUEST["site"];
$user = $_REQUEST["user"];
$sql = "INSERT INTO `rubinj_aviv`.`business_table` (`IDN`, `name`,
`country`, `city`, `street`, `housenum`, `phoneNum`, `email`, `site`, `user`) VALUES (\"$idn\",\"$name\", \"$country\", \"$city\", \"$street\", \"$housenum\", \"$phoneNum\", \"$email\",\"$site\", \"$user\")";
if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();
?>