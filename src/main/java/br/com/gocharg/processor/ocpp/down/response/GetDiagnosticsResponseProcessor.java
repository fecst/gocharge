package br.com.gocharg.processor.ocpp.down.response;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.dto.ocpp.json.request.OcppRequest;
import br.com.gocharg.dto.ocpp.json.response.CancelReservationResponse;
import br.com.gocharg.dto.ocpp.json.response.GetDiagnosticsResponse;
import br.com.gocharg.factory.OcppResponseFactory;
import br.com.gocharg.repository.TotemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetDiagnosticsResponseProcessor implements CommandProcessor {

  @Autowired private TotemRepository repository;
  @Autowired private OcppResponseFactory factory;

  @Override
  public Object process(CommandContext context) {
    OcppRequest ocppRequest = context.getProperty("ocppRequest", OcppRequest.class);

    GetDiagnosticsResponse response = (GetDiagnosticsResponse) ocppRequest.getPayload();

    // TODO Inserir na tabela de transação

    return null;
  }
}
