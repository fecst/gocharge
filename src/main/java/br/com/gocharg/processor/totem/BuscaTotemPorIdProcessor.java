package br.com.gocharg.processor.totem;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Totem;
import br.com.gocharg.repository.TotemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BuscaTotemPorIdProcessor implements CommandProcessor<Totem> {

  @Autowired private TotemRepository repository;

  @Override
  public Totem process(CommandContext context) {
    UUID idTotem = context.getProperty("idTotem", UUID.class);

    return repository.getById(idTotem);
  }
}
