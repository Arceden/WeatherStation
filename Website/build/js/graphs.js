var canvas = document.getElementById("graphs");
var ctx = canvas.getContext("2d");

canvas.width = 800;
canvas.height = 600;

ctx.fillStyle = "rgb(200, 200, 200)";
ctx.fillRect(0, 0, canvas.width, canvas.height);

var botswanaIds = ["680240", "680260", "680290", "680380", "680400", "680540", "682340", "682400", "682675"];
for (var id of botswanaIds) {
   var option = document.createElement("option");
   option.text = `Station ${id}`;
   document.getElementById("dropdown").appendChild(option);
}


function loop() {
   window.requestAnimationFrame(loop);

}

loop();
