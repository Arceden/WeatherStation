<?php

// Initialize the session
session_start();
// Check if session login is admin
if($_SESSION["type"] == 1) {
?>


<div id="user_create" class="popup field dark_ui invisible">
    <div class="screen">
        <div class="close">x</div>
        <form action="/Asseater69/AddAccount.php" method="POST">
            <h2>Create New User</h2>
            
            <label for="user_create_email_input">Username</label><bzr/>
            <input name="username" type="text" id="user_create_email_input" placeholder="DaWeatherStrijder"/><br/><br/>

            <label for="user_create_password_input">Password</label><br/>
            <input name="password" type="password" id="user_create_password_input"/><br/><br/><br/>

            <input name="submit" type="submit" id="user_create_submit" value="Add User"/>
        </form>
    </div>
</div>

<?php
}
else {echo "Couldn't find this page";}
?>