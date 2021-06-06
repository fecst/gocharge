package br.com.gocharg.infrastructure;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.dto.ocpp.json.request.OcppRequest;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;


public class StompHandler extends StompSessionHandlerAdapter {
    private Logger logger = LogManager.getLogger(StompHandler.class);

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        CommandContext context = new CommandContext();

        String nomeTotem = context.getProperty("apelidoTotem", String.class);
        String messageEv = context.getProperty("messageEv", String.class);

        logger.info("New session established : " + session.getSessionId());
        session.subscribe("/ev/" + nomeTotem, this);
        logger.info("Subscribed to /ev/" + nomeTotem);
        session.send("/ev/" + nomeTotem, messageEv);
        logger.info("Message sent to websocket server: " + messageEv);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        logger.error("Got an exception", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return OcppRequest.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        OcppRequest msg = (OcppRequest) payload;
        logger.info("Received : " + msg);
    }
}
