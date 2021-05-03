package br.com.gocharg.processor.ocpp;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.dto.ocpp.json.request.BootNotificationRequest;
import br.com.gocharg.dto.ocpp.json.request.OcppRequest;
import br.com.gocharg.dto.ocpp.json.request.StartTransactionRequest;
import br.com.gocharg.dto.ocpp.json.response.BootNotificationResponse;
import br.com.gocharg.dto.ocpp.json.response.StartTransactionResponse;
import br.com.gocharg.dto.ocpp.json.response.TagInfo;
import br.com.gocharg.enums.ocpp.OcppResponseStatusEnum;
import br.com.gocharg.factory.OcppResponseFactory;
import br.com.gocharg.repository.TotemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProcessaStartTransactionProcessor implements CommandProcessor<String> {

  @Autowired private TotemRepository repository;
  @Autowired private OcppResponseFactory factory;

  @Override
  public String process(CommandContext context) {
    OcppRequest ocppRequest = context.getProperty("ocppRequest", OcppRequest.class);

    StartTransactionRequest request = (StartTransactionRequest) ocppRequest.getPayload();
    String retorno = new String();
    StartTransactionResponse response = new StartTransactionResponse();

    try {
      // TODO Inserir na tabela de transação

      TagInfo tagInfo = new TagInfo();

      tagInfo.setStatus(OcppResponseStatusEnum.ACCEPTED.getStatus());
      tagInfo.setParentIdTag(request.getIdTag());

      //trocar por id da tabela de transação
      response.setTransactionId(1);
      response.setIdTagInfo(tagInfo);

      retorno =
          factory.retorno(
              ocppRequest.getUniqueID(), new ObjectMapper().writeValueAsString(response));
    } catch (Exception e) {
      System.out.println("Erro na conversão para JSON");
    }

    return retorno;
  }
}
