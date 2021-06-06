package br.com.gocharg.processor.ocpp.up;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Totem;
import br.com.gocharg.dto.ocpp.json.request.BootNotificationRequest;
import br.com.gocharg.dto.ocpp.json.request.OcppRequest;
import br.com.gocharg.dto.ocpp.json.request.StatusNotificationRequest;
import br.com.gocharg.dto.ocpp.json.response.BootNotificationResponse;
import br.com.gocharg.dto.ocpp.json.response.StatusNotificationResponse;
import br.com.gocharg.enums.StatusTotemEnum;
import br.com.gocharg.enums.ocpp.OcppResponseStatusEnum;
import br.com.gocharg.factory.OcppResponseFactory;
import br.com.gocharg.repository.TotemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Component
public class StatusNotificationProcessor implements CommandProcessor<String> {

  @Autowired private TotemRepository repository;
  @Autowired private OcppResponseFactory factory;

  @Override
  public String process(CommandContext context) {
    OcppRequest ocppRequest = context.getProperty("ocppRequest", OcppRequest.class);

    StatusNotificationRequest request = (StatusNotificationRequest) ocppRequest.getPayload();
    String retorno = new String();
    StatusNotificationResponse response = new StatusNotificationResponse();

    try {
      Totem totem = repository.getByApelido(ocppRequest.getApelidoTotem());
      totem.setStatus(StatusTotemEnum.DISPONIVEL);

      repository.update(totem);

      retorno =
          factory.retorno(
              ocppRequest.getUniqueID(), "{}");
    } catch (Exception e) {
      System.out.println("Erro na conversão para JSON");
    }

    return retorno;
  }
}
