e<div id="login" class="popup field dark_ui invisible">
    <div class="screen">
        <div class="close">x</div>
        <form action="/login.php" method="POST">
            <h2>Login</h2>
            
            <label for="login_email_input">E-Mail</label><br/>
            <input name="username" type="text" id="login_email_input" placeholder="DaWeatherStrijder"/><br/><br/>

            <label for="login_password_input">Password</label><br/>
            <input name="password" type="password" id="login_password_input"/><br/><br/><br/>

            <input name="submit" type="submit" id="login_submit" value="Login"/>

        </form>
    </div>
</div>