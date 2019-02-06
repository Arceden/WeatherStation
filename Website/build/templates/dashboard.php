<h1>Dashboard</h1>

<?php

// Check if session login is admin
if($_SESSION["type"] == 1) {

?>
<a class="call_user_create" href="javascript:void(0)">Add new user</a><br/>
<a class="call_user_edit" href="javascript:void(0)">Edit user</a><br/>
<a class="call_user_remove" href="javascript:void(0)">Remove user</a><br/>
<?php
}
?>

<a class="call_new_password" href="javascript:void(0)">Renew Password</a><br/>
<a href="/Asseater69/logout.php">Sign Out</a>

<p>Request a data download</p>
<a href="javascript:void(0)">Request raw data file</a>

<p>Live data from the weather stations</p>
<select id="dropdown" name="weatherstation" onchange="update()">
</select>
<br>

<canvas id="graphs"></canvas>
<script src="../js/graphs.js"></script>
