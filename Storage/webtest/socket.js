// var exampleSocket = new WebSocket("ws://13.94.198.160:7790");
var exampleSocket = new WebSocket('ws://192.168.178.108:7790');
exampleSocket.onopen = function(evt){

}

exampleSocket.onmessage = function(event){
    console.log("Got a message!?");
    console.log(event);
}