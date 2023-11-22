var stompClient = null;

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#conversation").show();
  }
  else {
    $("#conversation").hide();
  }
  $("#greetings").html("");
}

function connect() {
  var socket = new SockJS('/gs-guide-websocket');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/greetings', function (greeting) {
      showGreeting(JSON.parse(greeting.body).content);
    });
    console.log("id is : " + $("#id"));
    if ($("#id").val() == "admin") {
      stompClient.subscribe('/topic/admin', function (admin) {
        showAdmin(JSON.parse(admin.body).content);
      });
    } else if ($("#id").val() == "dev") {
      stompClient.subscribe('/topic/dev', function (dev) {
        showDev(dev.body);
      });
    }
  });
}

function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
  console.log("Disconnected");
}

function sendName() {
  stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
  if ($("#name").val() == "black") {
    stompClient.send("/app/black", {}, JSON.stringify({'name': $("#name").val()}));
  }
}

function showGreeting(message) {
  $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

function showAdmin(message) {
  $("#admin").append("<tr><td>" + message + "</td></tr>");
}

function showDev(message) {
  $("#dev").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
  $("form").on('submit', function (e) {
    e.preventDefault();
  });
  $( "#connect" ).click(function() { connect(); });
  $( "#disconnect" ).click(function() { disconnect(); });
  $( "#send" ).click(function() { sendName(); });
});