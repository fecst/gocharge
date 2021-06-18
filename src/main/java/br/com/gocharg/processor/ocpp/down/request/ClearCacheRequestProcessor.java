package br.com.gocharg.processor.ocpp.down.request;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.dto.ocpp.json.request.ChangeAvailabilityRequest;
import br.com.gocharg.dto.ocpp.json.request.ClearCacheRequest;
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

@Component
public class ClearCacheRequestProcessor implements CommandProcessor {

  @Autowired private StompClient stompClient;
  @Autowired private OcppResponseFactory factory;
  @Autowired private TransacaoRepository transacaoRepository;
  @Autowired private OcppLogProcessor ocppLogProcessor;

  @Override
  public Object process(CommandContext context) {
    try {
      String apelidoTotem = context.getProperty("apelidoTotem", String.class);
      Integer uniqueId = transacaoRepository.getNextIdByApelidoTotem(apelidoTotem);
      ClearCacheRequest request = new ClearCacheRequest();

      String messageEv =
          factory.requisicao(
              uniqueId.toString(),
              OcppFunctionsEnum.CLEAR_CACHE.getFunction(),
              "{}");

      stompClient.open(apelidoTotem, messageEv);

      OcppRequest ocppRequest = new OcppRequest();

      ocppRequest.setApelidoTotem(apelidoTotem);
      ocppRequest.setOperation(OcppMessageTypeEnum.CALL);
      ocppRequest.setAction(OcppFunctionsEnum.CLEAR_CACHE);
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
