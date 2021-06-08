package br.com.gocharg.processor.ocpp;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Transacao;
import br.com.gocharg.dto.ocpp.json.request.OcppRequest;
import br.com.gocharg.repository.TotemRepository;
import br.com.gocharg.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class OcppLogProcessor implements CommandProcessor {

  @Autowired private TransacaoRepository transacaoRepository;
  @Autowired private TotemRepository totemRepository;

  @Override
  public Object process(CommandContext context) {
    OcppRequest ocppRequest = context.getProperty("ocppRequest", OcppRequest.class);

    Transacao transacao = new Transacao();

    transacao.setAction(ocppRequest.getAction().getFunction());
    transacao.setOperation(ocppRequest.getOperation().getType());
    transacao.setDataHoraCadastro(LocalDateTime.now());
    transacao.setPayload(ocppRequest.getPayload().toString());
    transacao.setTotem(totemRepository.getByApelido(ocppRequest.getApelidoTotem()));
    transacao.setUniqueID(Integer.valueOf(ocppRequest.getUniqueID()));

    transacaoRepository.create(transacao);

    return null;
  }
}
