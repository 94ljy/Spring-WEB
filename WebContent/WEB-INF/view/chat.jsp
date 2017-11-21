<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<script src="/assert/js/jquery.min.js"></script>
<link href="/assert/css/bootstrap.min.css" rel="stylesheet"/>
<script src="/assert/js/bootstrap.min.js"></script>
<title>채팅</title>
<script>
	var socket;
	
	$(function() {
		console.log(window.location.host);
		socket = new WebSocket("ws://"+window.location.host+"/chatjoin");
		
		socket.onopen = onOpen;
		
		socket.onmessage = onMessage;
		
		socket.onclose = onClose;
		
	});
	
	function onOpen(){
		console.log("접속 완료");
	}
	
	function onMessage(event){
		console.log(event);
		var receiveMsg = JSON.parse(event.data);
		$("#chatBox").append("<p>" + receiveMsg.message + "</p>");
	}
	
	function onClose(){
		
	}
	
	function sendMessage(){
		var message = $("input[name=message]").val();
		$("input[name=message]").val("");
		var msg = {
			type : "msg",
			message : message
		};
		console.log(JSON.stringify(msg));
		socket.send(JSON.stringify(msg));
	}
	
</script>
</head>
<body class="container">

	<div style="display: table; height: 100%; margin: 0 auto;">
		<div class="text-center" style="display: table-cell; vertical-align: middle; width: 500px; height: 400px;">
			<div id="chatBox" style="width: 600px; height: 400px; border: 1px solid;"></div>
			<div>
				<input name="message" type="text">
				<button onclick="sendMessage()">전송</button>
			</div>
		</div>
	</div>
</body>
</html>