/**
 * Only edit the Javascript files in ./dev/js/.
 * All Javascript files in ./build/js/ may be overwritten.
 * Arnold Buining
 */
(function () {
  //Check website location
  switch (window.location.hash) {
    case '#login':
      open_popup('login');
      break;

    case '#user_create':
      open_popup('user_create');
      break;

    default:
      break;
  } //Initialize the listener and popuphandler


  var listen = new Listener();
  var phandler = new PopupHandler(); //Add all required elements

  listen.newElements('login', document.getElementsByClassName("call_login"), new PopupHandler().newPopup(document.getElementById('login')));
  listen.newElements('user_create', document.getElementsByClassName("call_user_create"), new PopupHandler().newPopup(document.getElementById('user_create')));
  listen.newElements('user_edit', document.getElementsByClassName('call_user_edit'), new PopupHandler().newPopup(document.getElementById('user_edit')));
  listen.newElements('user_renew', document.getElementsByClassName('call_new_password'), new PopupHandler().newPopup(document.getElementById('user_renew_password')));
  listen.applyHandler('login');
  listen.applyHandler('user_create');
  listen.applyHandler('user_edit');
  listen.applyHandler('user_renew');
})();