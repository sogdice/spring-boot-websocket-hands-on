package sogdice.example.spring.boot.websocket.simple;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {

    private static Logger log = LoggerFactory.getLogger(EchoHandler.class);

    private Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<String, WebSocketSession>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("established: {}", session.getId());
        sessionMap.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("closed: {}", session.getId());
        sessionMap.remove(session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        log.info("message(from={}): {}", session.getId(), textMessage.getPayload());
        for (WebSocketSession sess : sessionMap.values()) {
            sess.sendMessage(textMessage);
        }
    }
}
