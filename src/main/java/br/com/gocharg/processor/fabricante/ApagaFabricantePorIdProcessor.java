package br.com.gocharg.processor.fabricante;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Fabricante;
import br.com.gocharg.repository.FabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ApagaFabricantePorIdProcessor implements CommandProcessor<Fabricante> {

  @Autowired private FabricanteRepository repository;

  @Override
  public Fabricante process(CommandContext context) {
    UUID idFabricante = context.getProperty("idFabricante", UUID.class);

    repository.delete(idFabricante);

    return null;
  }
}
