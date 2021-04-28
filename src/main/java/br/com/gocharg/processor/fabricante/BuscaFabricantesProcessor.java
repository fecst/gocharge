package br.com.gocharg.processor.fabricante;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Fabricante;
import br.com.gocharg.repository.FabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaFabricantesProcessor implements CommandProcessor<List<Fabricante>> {

  @Autowired private FabricanteRepository repository;

  @Override
  public List<Fabricante> process(CommandContext commandContext) {
    return repository.getAll();
  }
}
