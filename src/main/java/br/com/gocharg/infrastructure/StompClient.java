package br.com.gocharg.infrastructure;

import org.kaazing.netx.ws.MessageWriter;
import org.kaazing.netx.ws.WebSocket;
import org.kaazing.netx.ws.WebSocketFactory;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class StompClient {

  private static String URL = "ws://localhost:10001/ev/";

  public void open(String apelidoTotem, String messageEv) throws URISyntaxException {
    //        WebSocketClient client = new StandardWebSocketClient();
    //        WebSocketStompClient stompClient = new WebSocketStompClient(client);
    //
    //        StompSessionHandler sessionHandler = new StompHandler();
    //        stompClient.connect(URL + apelidoTotem, sessionHandler);
    //
    //        stompClient.
    //
    //        new Scanner(System.in).nextLine(); // Don't close immediately.

    try {
      WebSocketFactory wsFactory = WebSocketFactory.newInstance();
      WebSocket ws = wsFactory.createWebSocket(URI.create(URL + apelidoTotem));
      ws.connect();

      MessageWriter writer = ws.getMessageWriter();
      writer.writeFully(messageEv.toCharArray());

      ws.close();
    } catch (Exception e) {

    }
  }
}
