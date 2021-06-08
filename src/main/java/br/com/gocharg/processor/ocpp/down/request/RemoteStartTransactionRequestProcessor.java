package br.com.gocharg.processor.ocpp.down.request;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Totem;
import br.com.gocharg.dto.ocpp.json.request.RemoteStartTransactionRequest;
import br.com.gocharg.factory.OcppResponseFactory;
import br.com.gocharg.infrastructure.StompClient;
import br.com.gocharg.repository.TotemRepository;
import br.com.gocharg.repository.TransacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoteStartTransactionRequestProcessor implements CommandProcessor {

  private static final String TRANSACTION = "RemoteStartTransaction";

  @Autowired private StompClient stompClient;
  @Autowired private OcppResponseFactory factory;
@Autowired private TransacaoRepository transacaoRepository;

  @Override
  public Object process(CommandContext context) {
    try {
      String apelidoTotem = context.getProperty("apelidoTotem", String.class);
      Integer uniqueId = transacaoRepository.getNextIdByApelidoTotem(apelidoTotem);
      RemoteStartTransactionRequest request = new RemoteStartTransactionRequest();

      request.setConnectorId(uniqueId);
      request.setIdTag(uniqueId.toString());

      String messageEv = factory.requisicao(
          uniqueId.toString(), TRANSACTION, new ObjectMapper().writeValueAsString(request));

      stompClient.open(apelidoTotem, messageEv);
    } catch (Exception e) {
      System.out.println("Erro na conversão para JSON");
    }

    return null;
  }
}
