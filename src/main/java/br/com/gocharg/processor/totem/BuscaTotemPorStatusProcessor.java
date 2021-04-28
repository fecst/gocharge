package br.com.gocharg.processor.totem;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Totem;
import br.com.gocharg.enums.StatusTotemEnum;
import br.com.gocharg.repository.TotemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaTotemPorStatusProcessor implements CommandProcessor<List<Totem>> {

  @Autowired private TotemRepository repository;

  @Override
  public List<Totem> process(CommandContext context) {
    return repository.getByStatus(StatusTotemEnum.DISPONIVEL.getCodigo());
  }
}
