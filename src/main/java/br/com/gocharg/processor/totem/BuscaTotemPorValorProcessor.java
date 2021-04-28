package br.com.gocharg.processor.totem;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Totem;
import br.com.gocharg.repository.TotemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class BuscaTotemPorValorProcessor implements CommandProcessor<List<Totem>> {

  @Autowired private TotemRepository repository;

  @Override
  public List<Totem> process(CommandContext context) {
    UUID idValor = context.getProperty("idValor", UUID.class);

    return repository.getByValor(idValor);
  }
}
