<?php

/**
 * This will be the main entry point of the dashboard. 
 * 
 * TODO:
 *  Check if the user has been authenticated
 *  Check if the user is an administrator or not
 *  Allow the Administrator to create new users
 *  Allow the Administrator to edit users
 *  Allow the Administrator to remove users
 *  Allow the authenticated user to download the Weather Data
 */

//$user->loggedIn() //True if logged in
//$user->isAdmin() //True if admin
// Initialize the session
session_start();
 
// Check if the user is logged in, if not then redirect him to login page
if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true){
    header("location: /index.php");
    exit;
}

include_once($_SERVER['DOCUMENT_ROOT'].'/templates/head.html');
include_once($_SERVER['DOCUMENT_ROOT'].'/templates/dashboard.php');

//forms
	include_once($_SERVER['DOCUMENT_ROOT'].'/templates/forms/login_form.php');
	include_once($_SERVER['DOCUMENT_ROOT'].'/templates/forms/user_create.html');
	include_once($_SERVER['DOCUMENT_ROOT'].'/templates/forms/user_renew_password.html');
	include_once($_SERVER['DOCUMENT_ROOT'].'/templates/forms/user_edit.html');
	include_once($_SERVER['DOCUMENT_ROOT'].'/templates/footer_dashboard.html');

	include_once($_SERVER['DOCUMENT_ROOT'].'/Asseater69/UserManagement.php');

?>