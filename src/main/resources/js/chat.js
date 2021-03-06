var ws;

function init() {
    ws = new WebSocket("ws://localhost:8000/chat");
    ws.onopen = function (event) {};
    ws.onmessage = function (event) {
        var $textarea = document.getElementById("messages");
        $textarea.value = $textarea.value + event.data + "\n";
    }
    ws.onclose = function (event) {};
};

function sendMessage() {
    var messageField = document.getElementById("message");
    var userNameField = document.getElementById("username").innerText;
    var message = userNameField + ": " + messageField.value;
    ws.send(message);
    messageField.value = '';
};