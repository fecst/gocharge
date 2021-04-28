package br.com.gocharg.processor.fabricante;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Fabricante;
import br.com.gocharg.dto.FabricanteDTO;
import br.com.gocharg.mappers.FabricanteMapper;
import br.com.gocharg.repository.FabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlteraFabricanteProcessor implements CommandProcessor<Fabricante> {

  @Autowired private FabricanteRepository repository;

  @Override
  public Fabricante process(CommandContext context) {
    FabricanteDTO fabricante = context.getProperty("fabricante", FabricanteDTO.class);

    return repository.update(FabricanteMapper.INSTANCE.toDomain(fabricante));
  }
}
