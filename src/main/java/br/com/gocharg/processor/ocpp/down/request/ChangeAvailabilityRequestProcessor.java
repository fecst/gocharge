package br.com.gocharg.processor.ocpp.down.request;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.dto.ocpp.json.request.CancelReservationRequest;
import br.com.gocharg.dto.ocpp.json.request.ChangeAvailabilityRequest;
import br.com.gocharg.dto.ocpp.json.request.OcppRequest;
import br.com.gocharg.enums.ocpp.OcppAvailabilityEnum;
import br.com.gocharg.enums.ocpp.OcppFunctionsEnum;
import br.com.gocharg.enums.ocpp.OcppMessageTypeEnum;
import br.com.gocharg.factory.OcppResponseFactory;
import br.com.gocharg.infrastructure.StompClient;
import br.com.gocharg.processor.ocpp.OcppLogProcessor;
import br.com.gocharg.repository.TransacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class ChangeAvailabilityRequestProcessor implements CommandProcessor {

  @Autowired private StompClient stompClient;
  @Autowired private OcppResponseFactory factory;
  @Autowired private TransacaoRepository transacaoRepository;
  @Autowired private OcppLogProcessor ocppLogProcessor;

  @Override
  public Object process(CommandContext context) {
    try {
      String apelidoTotem = context.getProperty("apelidoTotem", String.class);
      String disponibilidade = context.getProperty("disponibilidade", String.class);
      Integer uniqueId = transacaoRepository.getNextIdByApelidoTotem(apelidoTotem);
      ChangeAvailabilityRequest request = new ChangeAvailabilityRequest();

      request.setConnectorId(0);
      request.setType(OcppAvailabilityEnum.get(disponibilidade).getStatus());

      String messageEv =
          factory.requisicao(
              uniqueId.toString(),
              OcppFunctionsEnum.CHANGE_AVAILABILITY.getFunction(),
              new ObjectMapper().writeValueAsString(request));

      stompClient.open(apelidoTotem, messageEv);

      OcppRequest ocppRequest = new OcppRequest();

      ocppRequest.setApelidoTotem(apelidoTotem);
      ocppRequest.setOperation(OcppMessageTypeEnum.CALL);
      ocppRequest.setAction(OcppFunctionsEnum.CHANGE_AVAILABILITY);
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
