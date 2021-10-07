package playground.websocket.stomp;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // websocket 메세지 처리 활성화
public class WebSocketBrokerConfig implements WebSocketMessageBrokerConfigurer { // 브로커 구성

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // 메모리 기반 메세지 브로커. broker target destination
        registry.setApplicationDestinationPrefixes("/app"); // 모든 messageMapping 앞에 붙음. application target destination
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").withSockJS(); // websocket 사용할 수 없는 경우 대체 전송
    }
}
