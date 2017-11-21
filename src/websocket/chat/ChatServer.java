package websocket.chat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import user.domain.User;
import websocket.chat.client.Client;

public class ChatServer extends TextWebSocketHandler{
	
	private Map<String, Client> clients;
	
	public ChatServer() {
		super();
		clients = new HashMap<>();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		User user = (User)session.getAttributes().get("user");		
		Client client = new Client();
		client.setSession(session);
		client.setUser(user);
		
		JSONObject sendMsg = new JSONObject();
		sendMsg.put("type", "adminMsg");
		sendMsg.put("message", "채팅에 접속 되었습니다." + client.getSubName() + "님  환영합니다.");		
		client.sendMessage(sendMsg.toString());
		
		sendMsg = new JSONObject();
		sendMsg.put("type", "adminMsg");
		sendMsg.put("message", client.getSubName() + "님이 채팅을 입장했습니다.");
		brodcastMessage(sendMsg.toString());
		
		clients.put(session.getId(), client);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		JSONObject sendMsg = new JSONObject();
		sendMsg.put("type", "adminMsg");
		sendMsg.put("message", clients.get(session.getId()).getSubName() + "님이 채팅을 나갔습니다.");
		clients.remove(session.getId());
		brodcastMessage(sendMsg.toString());
		super.afterConnectionClosed(session, status);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		JSONObject receiveMsg = new JSONObject(message.getPayload());
		String type = receiveMsg.getString("type");
		
		if(type.equals("msg")) {
			JSONObject sendMsg = new JSONObject();
			sendMsg.put("type", "msg");
			sendMsg.put("name", clients.get(session.getId()).getSubName());
			sendMsg.put("message", receiveMsg.getString("message"));
			
			brodcastMessage(sendMsg.toString());
		}
		
	}
	
	private void brodcastMessage(String message) throws IOException {
		for(Map.Entry<String, Client> entry: clients.entrySet()) {
			entry.getValue().sendMessage(message);
		}
	}
	
}
