package br.com.gocharg.processor.ocpp;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.dto.ocpp.json.request.OcppRequest;
import br.com.gocharg.dto.ocpp.json.request.StatusNotificationRequest;
import br.com.gocharg.dto.ocpp.json.response.HeartbeatResponse;
import br.com.gocharg.dto.ocpp.json.response.StatusNotificationResponse;
import br.com.gocharg.factory.OcppResponseFactory;
import br.com.gocharg.repository.TotemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class ProcessaHeartbeatProcessor implements CommandProcessor<String> {

  @Autowired private OcppResponseFactory factory;

  @Override
  public String process(CommandContext context) {
    OcppRequest ocppRequest = context.getProperty("ocppRequest", OcppRequest.class);

    String retorno = new String();
    HeartbeatResponse response = new HeartbeatResponse();

    try {
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
