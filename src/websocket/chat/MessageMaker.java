package websocket.chat;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import websocket.chat.client.Client;

public class MessageMaker {

	
	
	public String msg(String subName, JSONObject data){
		JSONObject sendMsg = new JSONObject();
		sendMsg.put("type", "msg");
		sendMsg.put("name", subName);
		sendMsg.put("message", data.getString("message"));
		
		return sendMsg.toString();
	}
	
	public String adminMsg(String message) {
		JSONObject sendMsg = new JSONObject();
		sendMsg.put("type", "adminMsg");
		sendMsg.put("message", message);
		
		return sendMsg.toString();
	}
	
	public String joinChat(String subName) {
		JSONObject sendMsg = new JSONObject();
		sendMsg.put("type", "joinChat");
		sendMsg.put("name", subName);
		
		return sendMsg.toString();
	}
	
	public String leaveChat(String subName) {
		JSONObject sendMsg = new JSONObject();
		sendMsg.put("type", "leaveChat");
		sendMsg.put("name", subName);
		
		return sendMsg.toString();
	}
	
	public String list(Map<String, Client> clients) {
		JSONObject sendMsg = new JSONObject();
		sendMsg.put("type", "list");
		JSONArray list = new JSONArray();
		
		for(Map.Entry<String, Client> entry: clients.entrySet()) {
			list.put(entry.getValue().getSubName());
		}
		sendMsg.put("list", list);
		
		
		return sendMsg.toString();
	}
	
}
