<template>
  <div id="stopwatch">
  
  <span class="time">{{ time }}</span>
  
  <div class="btn-container">
    <a id="start">Start</a>
    <a id="stop">Stop</a>
    <a id="reset">Reset</a>
  </div>
  
</div>
</template>

<script>
export default {
    name: "stopwatch"

}
var clock = new Vue({
  el: '#clock',
  data: {
    time: '00:00:00'
  }
});

var timeBegan = null
, timeStopped = null
, stoppedDuration = 0
, started = null
, running = false;

document.getElementById("start").addEventListener("click", start);
document.getElementById("stop").addEventListener("click", stop);
document.getElementById("reset").addEventListener("click", reset);

function start() {
  if(running) return;
  
  if (timeBegan === null) {
    reset();
    timeBegan = new Date();
  }

  if (timeStopped !== null) {
    stoppedDuration += (new Date() - timeStopped);
  }

  started = setInterval(clockRunning, 10);	
  running = true;
}

function stop() {
  running = false;
  timeStopped = new Date();
  clearInterval(started);
}

function reset() {
  running = false;
  clearInterval(started);
  stoppedDuration = 0;
  timeBegan = null;
  timeStopped = null;
  clock.time = "00:00:00.000";
}

function clockRunning(){
  var currentTime = new Date()
  , timeElapsed = new Date(currentTime - timeBegan - stoppedDuration)
  , hour = timeElapsed.getUTCHours()
  , min = timeElapsed.getUTCMinutes()
  , sec = timeElapsed.getUTCSeconds()
  , ms = timeElapsed.getUTCMilliseconds();

  clock.time = 
    zeroPrefix(hour, 2) + ":" + 
    zeroPrefix(min, 2) + ":" + 
    zeroPrefix(sec, 2) + "." + 
    zeroPrefix(ms, 3);
};

function zeroPrefix(num, digit) {
  var zero = '';
  for(var i = 0; i < digit; i++) {
    zero += '0';
  }
  return (zero + num).slice(-digit);
}
</script>

<style>
$color: limegreen;
$color-light: limegreen;

@import url('https://fonts.googleapis.com/css2?family=Share+Tech+Mono&display=swap');

html, body {
  padding: 0; margin: 0;
  width: 100%;
  height: 100%;
}

body {
  background-color: rgb(10, 10, 10);
  font-family: 'Share Tech Mono', sans-serif;

  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: center;
  align-content: stretch;
  align-items: center;
}

#clock {
  order: 0;
  flex: 0 1 auto;
  align-self: center;

  color: $color;
  //text-shadow: 0px 0px 25px $color;

  .time {
    font-size: 6.5em;
  }

  .text {
    margin-top: 30px;
    font-size: 1em;
    color: rgba($color, .4);
    text-align: center;

    a {
      text-decoration: none;
      color: inherit;

      transition: color .1s ease-out;

      &:hover {
        color: $color;
      }
    }
  }

  .btn-container {
    display: flex;
    margin-top: 15px;

    a {
      text-align: center;
      font-family: 'Share Tech Mono', sans-serif;
      background: transparent;
      //border: 3px solid $color;
      border: none;
      color: $color;
      padding: 10px 15px;
      margin: 0 10px;
      text-transform: uppercase;
      font-size: 2em;
      cursor: pointer;
      //text-shadow: 0px 0px 10px $color;

      flex-grow: 1;

      transition: color .1s ease-out;

      &:hover {
        color: $color-light;
      }
    }
  }
}
</style>