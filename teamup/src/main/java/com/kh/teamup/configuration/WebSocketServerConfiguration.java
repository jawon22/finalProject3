package com.kh.teamup.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.kh.teamup.websocket.SockJsWebSocketServer;

@EnableWebSocket
@Configuration
public class WebSocketServerConfiguration implements WebSocketConfigurer{
	
	@Autowired
	private SockJsWebSocketServer sockJsWebSocketServer;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(sockJsWebSocketServer, "/ws/sockjs").setAllowedOriginPatterns("http://localhost:3000")
		.addInterceptors(new HttpSessionHandshakeInterceptor())
		.withSockJS();	
		}

}
