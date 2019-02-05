<?php
 
 session_start();
// Include config file
require_once "config.php";
 
// Define variables and initialize with empty values
$new_password = $confirm_password = $old_password = "";
$new_password_err = $confirm_password_err = $old_password_err = "";

$session_username = $_SESSION['username'];
 
// Processing form data when form is submitted
if($_SERVER["REQUEST_METHOD"] == "POST"){

    $old_password = trim($_POST["old_password"]);
    $stmt = $pdo->query("SELECT password FROM user WHERE username = '$session_username'");
    $hashed_password = $stmt->fetch();
    if(empty($old_password)){
        $old_password_err = "Please enter your old password.";
    } elseif(!password_verify($old_password, $hashed_password)){
        $old_password_err = "Old password isn't correct!";
    }

 
    // Validate new password
    if(empty(trim($_POST["password"]))){
            $password_err = "Please enter a password.";
        } elseif(strlen(trim($_POST["password"])) < 8) {
            $password_err = "Password must have atleast 8 characters.";
        }  elseif(!preg_match("#[0-9]+#", trim($_POST["password"]))){
            $password_err = "Password must contain at least 1 number." ;}
         elseif(!preg_match("#[a-zA-Z]+#", trim($_POST["password"]))){
            $password_err = "Password must contain at least 1 letter." ;}
         else{
            $password = trim($_POST["password"]);}
    
    // Validate confirm password
    if(empty(trim($_POST["confirm_password"]))){
        $confirm_password_err = "Please confirm the password.";
    } else{
        $confirm_password = trim($_POST["confirm_password"]);
        if(empty($new_password_err) && ($new_password != $confirm_password)){
            $confirm_password_err = "Password did not match.";
        }
    }
        
    // Check input errors before updating the database
    if(empty($new_password_err) && empty($confirm_password_err) && empty($old_password_err)){
        // Prepare an update statement
        $sql = "UPDATE users SET password = :password WHERE id = :id";
        
        if($stmt = $pdo->prepare($sql)){
            // Bind variables to the prepared statement as parameters
            $stmt->bindParam(":password", $param_password, PDO::PARAM_STR);
            $stmt->bindParam(":id", $param_id, PDO::PARAM_INT);
            
            // Set parameters
            $param_password = password_hash($new_password, PASSWORD_DEFAULT);
            $param_id = $_SESSION["id"];
            
            // Attempt to execute the prepared statement
            if($stmt->execute()){
                // Password updated successfully. Destroy the session, and redirect to login page
                session_destroy();
                header("location: /index.php");
                exit();
            } else{
                echo "Oops! Something went wrong. Please try again later.";
            }
        }
        
        // Close statement
        unset($stmt);
    }
    
    // Close connection
    unset($pdo);
}
?>