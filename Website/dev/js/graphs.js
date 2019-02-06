var canvas = document.getElementById("graphs");
var ctx = canvas.getContext("2d");

canvas.width = 800;
canvas.height = 600;

var botswanaIds = ["680240", "680260", "680290", "680380", "680400", "680540", "682340", "682400", "682675"];

var datathings = {};

for (var id of botswanaIds) {
   var option = document.createElement("option");
   option.text = `Station ${id}`;
   document.getElementById("dropdown").appendChild(option);
}

var dataRequest = new XMLHttpRequest();
dataRequest.onreadystatechange = function() {
   if (this.readyState === 4 && this.status === 200) {
      var a = this.responseText.split("\n");

      datathings = {};

      for (var i = 1; i < a.length - 1; i++) {
         var b = a[i].split(";");
         datathings[b[0]] = {
            date: b[1],
            time: b[2],
            temp: b[3],
            dewp: b[4],
            stp: b[5],
            slp: b[6],
            visib: b[7],
            wdsp: b[8],
            prcp: b[9],
            sndp: b[10],
            frshht: b[11],
            cldc: b[12],
            wnddir: b[13]
         };
      }
   }
}

var currentStation = "Station: 680240";

function loop() {
   window.requestAnimationFrame(loop);

   ctx.fillStyle = "rgb(200, 200, 200)";
   ctx.fillRect(0, 0, canvas.width, canvas.height);

   var cs = currentStation.split(" ")[1];

   if (botswanaIds.includes(cs)) {
      if (datathings[cs]) {
         ctx.fillStyle = "rgb(0, 0, 0)";
         ctx.font = "15px Arial";

         ctx.fillText(`Date of measurement: ${datathings[cs].date} at ${datathings[cs].time}`, 20, 20)
         ctx.fillText(`Temperature: ${datathings[cs].temp}°C`, 20, 40);
         ctx.fillText(`Dew point: ${datathings[cs].dewp}°C`, 20, 60);
         ctx.fillText(`Air pressure at station level: ${datathings[cs].stp} millibar`, 20, 80);
         ctx.fillText(`Air pressure at sea level: ${datathings[cs].slp} millibar`, 20, 100);
         ctx.fillText(`Visibility : ${datathings[cs].visib} kilometers`, 20, 120);
         ctx.fillText(`Wind speed: ${datathings[cs].wdsp} km/h`, 20, 140);
         ctx.fillText(`Rainfall: ${datathings[cs].prcp} cm`, 20, 160);
         ctx.fillText(`Snowfall: ${datathings[cs].sndp} cm`, 20, 180);
         ctx.fillText(`Cloud Cover: ${datathings[cs].cldc} %`, 20, 200);
         ctx.fillText(`Winddirection: ${datathings[cs].wnddir % 360} degrees`, 20, 220);

         var h = [];
         if ((datathings[cs].frshht & 0b100000) == 0b100000) h.push("freezing");
         if ((datathings[cs].frshht & 0b010000) == 0b010000) h.push("raining");
         if ((datathings[cs].frshht & 0b001000) == 0b001000) h.push("snowing");
         if ((datathings[cs].frshht & 0b000100) == 0b000100) h.push("hailing");
         if ((datathings[cs].frshht & 0b000010) == 0b000010) h.push("storming");
         if ((datathings[cs].frshht & 0b000001) == 0b000001) h.push("there are tornadoes");

         var s = "It's ";
         var f = false;
         for (var i = 0; i < h.length - 1; i++) {
            s += h[i] + ", ";
            f = true;
         }
         if (f) s += "and ";
         if (h.length) s += h[h.length-1];
         if (s != "It's ") {
            ctx.fillText(s, 20, 240);
         }
      }
   }
}

loop();


function update() {
   currentStation = document.getElementById("dropdown").value;
}

setInterval(function() {
   dataRequest.open("GET", "smol.csv", true);
   dataRequest.send();
}, 1000);