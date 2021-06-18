package br.com.gocharg.processor.ocpp;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Transacao;
import br.com.gocharg.dto.ocpp.json.response.OcppResponse;
import br.com.gocharg.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OcppResponseProcessor implements CommandProcessor {

  @Autowired private TransacaoRepository transacaoRepository;

  @Override
  public Object process(CommandContext context) {
    OcppResponse ocppResponse = context.getProperty("ocppResponse", OcppResponse.class);

    Transacao transacao =
        transacaoRepository.getByApelidoAndUniqueId(
            ocppResponse.getApelidoTotem(), ocppResponse.getUniqueID());

    return null;
  }
}
