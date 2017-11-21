package websocket.chat;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class SessionInterceptor extends HttpSessionHandshakeInterceptor{

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler,
			Map<String, Object> attributes) throws Exception {		
		HttpSession session = ((ServletServerHttpRequest)request).getServletRequest().getSession();
		
		attributes.put("user", session.getAttribute("user"));
		
		return super.beforeHandshake(request, response, webSocketHandler, attributes);
	}
	
}
