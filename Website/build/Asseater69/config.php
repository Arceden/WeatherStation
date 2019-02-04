<?php
/* Database credentials. Assuming you are running MySQL
server with default setting (user 'root' with no password) */
$dbServer = "localhost";
$dbUsername = "root";
$dbPassword = "";
$dbName = "mydb";
 
/* Attempt to connect to MySQL database */
try{
    $pdo = new PDO("mysql:host=$dbServer;dbname=$dbName", $dbUsername, $dbPassword);
    // Set the PDO error mode to exception
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch(PDOException $e){
    die("ERROR: Could not connect. " . $e->getMessage());
}

?>