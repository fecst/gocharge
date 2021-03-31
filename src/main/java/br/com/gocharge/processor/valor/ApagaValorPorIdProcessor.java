package br.com.gocharge.processor.valor;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Valor;
import br.com.gocharge.repository.ValorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ApagaValorPorIdProcessor implements CommandProcessor<Valor> {

  @Autowired private ValorRepository valorRepository;

  @Override
  public Valor process(CommandContext context) {
    UUID idValor = context.getProperty("idValor", UUID.class);

    valorRepository.delete(idValor);

    return null;
  }
}
