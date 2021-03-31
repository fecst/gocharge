package br.com.gocharge.processor.subZonas;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.SubZona;
import br.com.gocharge.repository.SubZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class BuscaSubZonaPorZonaProcessor implements CommandProcessor<List<SubZona>> {

  @Autowired private SubZonaRepository subZonaRepository;

  @Override
  public List<SubZona> process(CommandContext context) {
    UUID idZona = context.getProperty("idZona", UUID.class);

    return subZonaRepository.getByZona(idZona);
  }
}
