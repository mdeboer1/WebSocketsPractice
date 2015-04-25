/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var endPointURL = "ws://" + window.location.host + "/WebSocketsPractice/notify";
 
var chatClient = null;

function connect () {
    chatClient = new WebSocket(endPointURL);
    
}    
function disconnect () {
    chatClient.close();
}    

function getMessage (){
    chatClient.onmessage = function (event) {
        var messageArea = $("#message");
        var jsonObj = JSON.parse(event.data);
        var message = jsonObj.messageArea;
        messageArea.value = message;
    };
}


