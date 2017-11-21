package websocket.chat.client;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import user.domain.User;

public class Client {
	private WebSocketSession session;
	private User user;
			
	public WebSocketSession getSession() {
		return session;
	}
	public void setSession(WebSocketSession session) {
		this.session = session;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public void sendMessage(String message) throws IOException {
		session.sendMessage(new TextMessage(message));
	}
	
	public String getSubName() {
		return user.getSubName();
	}
	
	@Override
	public boolean equals(Object o) {
		return session.equals(o);
	}
	
}
