package br.com.gocharg.processor.ocpp.up;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.dto.ocpp.json.request.BootNotificationRequest;
import br.com.gocharg.dto.ocpp.json.request.MeterValuesRequest;
import br.com.gocharg.dto.ocpp.json.request.OcppRequest;
import br.com.gocharg.dto.ocpp.json.response.BootNotificationResponse;
import br.com.gocharg.dto.ocpp.json.response.MeterValuesResponse;
import br.com.gocharg.enums.ocpp.OcppResponseStatusEnum;
import br.com.gocharg.factory.OcppResponseFactory;
import br.com.gocharg.repository.TotemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MeterValueProcessor implements CommandProcessor<String> {

  @Autowired private TotemRepository repository;
  @Autowired private OcppResponseFactory factory;

  @Override
  public String process(CommandContext context) {
    OcppRequest ocppRequest = context.getProperty("ocppRequest", OcppRequest.class);

    MeterValuesRequest request = (MeterValuesRequest) ocppRequest.getPayload();
    String retorno = new String();
    MeterValuesResponse response = new MeterValuesResponse();

    try {
      retorno =
          factory.retorno(
              ocppRequest.getUniqueID(), "{}");
    } catch (Exception e) {
      System.out.println("Erro na conversão para JSON");
    }

    return retorno;
  }
}
