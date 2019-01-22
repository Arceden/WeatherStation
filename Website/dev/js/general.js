/**
 * Only edit the Javascript files in ./dev/js/.
 * All Javascript files in ./build/js/ may be overwritten.
 * 
 * TODO: 
 *  Make classes, this is just a mess. A working mess atleast.
 */

(function(){

    if(window.location.hash=='#login'){
        open_popup('login');
    }

    /**
     * This handles the calls for the popups and to close the popups
     */
    var popup_state = false;
    var login_elements = document.getElementsByClassName("call_login");
    //Assign the event listener to all elements
    for(var x=0;x<login_elements.length;x++){
        login_elements[x].addEventListener("click", function(){
            if(popup_state==false){
                open_popup('login');
            }
        });
    }

    var close_popup_elements = document.getElementsByClassName("close");
    //Assign the event listener to all elements
    for(var x=0;x<close_popup_elements.length;x++){
        close_popup_elements[x].addEventListener("click", function(){
            close_popup('login')
        })
    }


    function open_popup(type){
        if(type=='login'){
            popup_state=true;
            document.getElementsByClassName("popup")[0].classList.remove('invisible');
            window.location.hash = 'login'
        }
    }

    function close_popup(type){
        switch(type){
            case 'login':
                popup_state=false;
                document.getElementsByClassName("popup")[0].classList.add('invisible');
                window.location.hash = '';
                break;
            default:
                console.warn("Popup type not found");
        }
    }


})();