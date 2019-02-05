<?php

    require_once  "config.php";


    //define variables
    $username = $password = $confirm_password = "";
    $username_err = $password_err = $confirm_password_err = "";


    //processing form data when the form is submitted

    if($_SERVER["REQUEST_METHOD"] == "POST")
    //CHECK IF SESSION LOGIN IS ADMIN
    {

        // Validate username
        if(empty(trim($_POST["username"]))){
            $username_err = "Please enter a username.";

        } else{
            // Prepare a select statement
            $sql = "SELECT idUser FROM user WHERE username = :username";

            if($stmt = $pdo->prepare($sql)){
                // Bind variables to the prepared statement as parameters
                $stmt->bindParam(":username", $param_username, PDO::PARAM_STR);

                // Set parameters
                $param_username = trim($_POST["username"]);

                // Attempt to execute the prepared statement
                if($stmt->execute()){
                    if($stmt->rowCount() == 1){
                        $username_err = "This username is already taken.";
                    } else{
                        $username = trim($_POST["username"]);
                    }
                } else{
                    echo "Something went wrong, please try again.";
                }
            }

            // Close statement
            unset($stmt);
        }

        // Validate password
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
            $confirm_password_err = "Please confirm password.";
        } else{
            $confirm_password = trim($_POST["confirm_password"]);
            if(empty($password_err) && ($password != $confirm_password)){
                $confirm_password_err = "Password did not match.";
            }
        }

        // Check input errors before inserting in database
        if(empty($username_err) && empty($password_err) && empty($confirm_password_err)){

            // Prepare an insert statement
            $sql = "INSERT INTO user (username, password) VALUES (:username, :password)";

            if($stmt = $pdo->prepare($sql)){

                // Bind variables to the prepared statement as parameters
                $stmt->bindParam(":username", $param_username, PDO::PARAM_STR);
                $stmt->bindParam(":password", $param_password, PDO::PARAM_STR);

                // Set parameters
                $param_username = $username;
                $param_password = password_hash($password, PASSWORD_DEFAULT); // Creates a password hash

                // Attempt to execute the prepared statement
                if($stmt->execute()){
                    // Redirect to login page
                    header("location: login.php");
                } else{
                    echo "Something went wrong. Please try again later.";
                }
            }

            // Close statement
            unset($stmt);
        } else{
            if(!empty($username_err)){
                echo $username_err;
            }elseif(!empty($password_err)){
                echo $password_err;
            }else{
                 echo $confirm_password_err;
            }
        }

        // Close connection
        unset($pdo);

    }

?>