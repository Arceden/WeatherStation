(function(){switch(window.location.hash){case"#login":open_popup("login");break;case"#user_create":open_popup("user_create");break;default:}var a=new Listener,b=new PopupHandler;a.newElements("login",document.getElementsByClassName("call_login"),new PopupHandler().newPopup(document.getElementById("login"))),a.newElements("user_create",document.getElementsByClassName("call_user_create"),new PopupHandler().newPopup(document.getElementById("user_create"))),a.newElements("user_edit",document.getElementsByClassName("call_user_edit"),new PopupHandler().newPopup(document.getElementById("user_edit"))),a.newElements("user_renew",document.getElementsByClassName("call_new_password"),new PopupHandler().newPopup(document.getElementById("user_renew_password"))),a.applyHandler("login"),a.applyHandler("user_create"),a.applyHandler("user_edit"),a.applyHandler("user_renew")})();