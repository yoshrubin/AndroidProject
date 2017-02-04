<?php
header('Content-Type: text/html; charset=utf-8'); 
$servername = "localhost";
$username = "yoshaviv";
$password = "rubinbentura";
$dbname = "rubinj_aviv";
    
$conn = new mysqli($servername, $username, $password, $dbname); 
mysql_query("SET NAMES 'utf8'", $conn); // Check connection 
if ($conn->connect_error) {     
	die("Connection failed: " . $conn->connect_error); 
	}  
$sql = "SELECT max(last_update) last_update FROM `updated`";
 $result = $conn->query($sql); 
 $data = array(); 
 if ($result->num_rows > 0) {     // output data of each row    
	//while ($row = $result->fetch_assoc()) {        
		//array_push($data,$row);   
          $row = $result->fetch_assoc();
       // } 
	echo json_encode($row);
   } 
 else {     echo "0 results"; 
	  echo json_encode("Didnt work");}  
    $conn->close();
?> 