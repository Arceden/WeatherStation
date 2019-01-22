/**
 * Only edit the Javascript files in ./dev/js/.
 * All Javascript files in ./build/js/ may be overwritten.
 * Arnold Buining
 */

(function(){

    //Check website location
    switch(window.location.hash){
      case '#login':
        open_popup('login');
        break;
      default:
        break;
    }
  
    //Initialize the listener and popuphandler
    var listen = new Listener();
    var phandler = new PopupHandler();
  
    //Add all required elements
    listen.newElements(
      'login',
      document.getElementsByClassName("call_login"), 
      new PopupHandler().newPopup(document.getElementById('login'))
    );
  
    listen.applyHandler('login');
    
  })();