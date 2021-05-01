package br.com.gocharg.processor.ocpp;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.dto.ocpp.json.request.BootNotificationRequest;
import br.com.gocharg.dto.ocpp.json.request.OcppRequest;
import br.com.gocharg.dto.ocpp.json.response.BootNotificationResponse;
import br.com.gocharg.enums.ocpp.OcppResponseStatusEnum;
import br.com.gocharg.factory.OcppResponseFactory;
import br.com.gocharg.repository.TotemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Component
public class ProcessaBootNotificationProcessor implements CommandProcessor<String> {

  @Autowired private TotemRepository repository;
  @Autowired private OcppResponseFactory factory;

  @Override
  public String process(CommandContext context) {
    OcppRequest ocppRequest = context.getProperty("ocppRequest", OcppRequest.class);

    BootNotificationRequest request = (BootNotificationRequest) ocppRequest.getPayload();
    String retorno = new String();
    BootNotificationResponse response = new BootNotificationResponse();

    try {
      // TODO Atualizar Tabela TOTEM

      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

      response.setInterval(300);
      response.setStatus(OcppResponseStatusEnum.ACCEPTED.getStatus());
      response.setCurrentTime(LocalDateTime.now().toString());

      retorno =
          factory.retorno(
              ocppRequest.getUniqueID(), new ObjectMapper().writeValueAsString(response));
    } catch (Exception e) {
      System.out.println("Erro na conversão para JSON");
    }

    return retorno;
  }
}
