package br.com.gocharg.processor.ocpp.up;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Totem;
import br.com.gocharg.dto.ocpp.json.request.OcppRequest;
import br.com.gocharg.dto.ocpp.json.response.BootNotificationResponse;
import br.com.gocharg.enums.StatusTotemEnum;
import br.com.gocharg.enums.ocpp.OcppResponseStatusEnum;
import br.com.gocharg.factory.OcppResponseFactory;
import br.com.gocharg.repository.TotemRepository;
import br.com.gocharg.repository.TransacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BootNotificationProcessor implements CommandProcessor<String> {

  @Autowired private TotemRepository repository;
  @Autowired private OcppResponseFactory factory;
  @Autowired private TransacaoRepository transacaoRepository;

  @Override
  public String process(CommandContext context) {
    OcppRequest ocppRequest = context.getProperty("ocppRequest", OcppRequest.class);
    String retorno = new String();
    BootNotificationResponse response = new BootNotificationResponse();

    try {
      Totem totem = repository.getByApelido(ocppRequest.getApelidoTotem());
      totem.setStatus(StatusTotemEnum.DISPONIVEL);

      repository.update(totem);

      response.setInterval(300);
      response.setStatus(OcppResponseStatusEnum.ACCEPTED.getStatus());
      response.setCurrentTime(LocalDateTime.now().toString());

      retorno =
          factory.retorno(
              ocppRequest.getUniqueId(), new ObjectMapper().writeValueAsString(response));
    } catch (Exception e) {
      System.out.println("Erro na conversão para JSON");
    }

    transacaoRepository.deleteByApelidoTotem(ocppRequest.getApelidoTotem());

    return retorno;
  }
}
