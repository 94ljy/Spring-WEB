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
	private MessageMaker messageMaker;
	
	public ChatServer() {
		super();
		clients = new HashMap<>();
		messageMaker = new MessageMaker();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		User user = (User)session.getAttributes().get("user");		
		Client client = new Client();
		client.setSession(session);
		client.setUser(user);
			
		String message = messageMaker.adminMsg("채팅에 접속 되었습니다." + client.getSubName() + "님  환영합니다.");	
		unicastMessage(client, message);
		message = messageMaker.list(clients);
		unicastMessage(client, message);
		
		clients.put(session.getId(), client);
		message = messageMaker.joinChat(client.getSubName());
		
		brodcastMessage(message);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		Client client = clients.remove(session.getId());
		String message = messageMaker.leaveChat(client.getSubName());
		brodcastMessage(message);
		super.afterConnectionClosed(session, status);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Client client = clients.get(session.getId());
		JSONObject receiveMsg = new JSONObject(message.getPayload());
		String type = receiveMsg.getString("type");
	
		if(type.equals("msg")) {
			String sendMsg = messageMaker.msg(client.getSubName(), receiveMsg);
			brodcastMessage(sendMsg);
		}else if(type.equals("list")) {
			String sendMsg = messageMaker.list(clients);
			unicastMessage(client, sendMsg);
		}
		
	}
	
	public void brodcastMessage(String message) throws IOException {
		for(Map.Entry<String, Client> entry: clients.entrySet()) {
			entry.getValue().sendMessage(message);
		}
	}
	
	public void unicastMessage(Client client, String message) throws IOException {
		client.sendMessage(message);
	}

}
