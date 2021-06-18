package br.com.gocharg.processor.ocpp.down.request;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.dto.ocpp.json.request.CancelReservationRequest;
import br.com.gocharg.dto.ocpp.json.request.OcppRequest;
import br.com.gocharg.dto.ocpp.json.request.ResetRequest;
import br.com.gocharg.enums.ocpp.OcppFunctionsEnum;
import br.com.gocharg.enums.ocpp.OcppMessageTypeEnum;
import br.com.gocharg.enums.ocpp.OcppResetEnum;
import br.com.gocharg.factory.OcppResponseFactory;
import br.com.gocharg.infrastructure.StompClient;
import br.com.gocharg.processor.ocpp.OcppLogProcessor;
import br.com.gocharg.repository.TransacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class ResetRequestProcessor implements CommandProcessor {

  @Autowired private StompClient stompClient;
  @Autowired private OcppResponseFactory factory;
  @Autowired private TransacaoRepository transacaoRepository;
  @Autowired private OcppLogProcessor ocppLogProcessor;

  @Override
  public Object process(CommandContext context) {
    try {
      String apelidoTotem = context.getProperty("apelidoTotem", String.class);
      String tipoReset = context.getProperty("tipoReset", String.class);
      Integer uniqueId = transacaoRepository.getNextIdByApelidoTotem(apelidoTotem);
      ResetRequest request = new ResetRequest();

      request.setType(OcppResetEnum.get(tipoReset).getType());

      String messageEv =
          factory.requisicao(
              uniqueId.toString(),
              OcppFunctionsEnum.RESET.getFunction(),
              new ObjectMapper().writeValueAsString(request));

      stompClient.open(apelidoTotem, messageEv);

      OcppRequest ocppRequest = new OcppRequest();

      ocppRequest.setApelidoTotem(apelidoTotem);
      ocppRequest.setOperation(OcppMessageTypeEnum.CALL);
      ocppRequest.setAction(OcppFunctionsEnum.RESET);
      ocppRequest.setUniqueId(uniqueId);
      ocppRequest.setPayload(request);

      context.put("ocppRequest", ocppRequest);

      ocppLogProcessor.process(context);
    } catch (Exception e) {
      System.out.println("Erro na conversão para JSON");
    }

    return null;
  }
}
