package br.com.gocharge.processor.zonas;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Cidade;
import br.com.gocharge.domain.Zona;
import br.com.gocharge.repository.ZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BuscaZonaPorIdProcessor implements CommandProcessor<Zona> {

  @Autowired private ZonaRepository zonaRepository;

  @Override
  public Zona process(CommandContext context) {
    UUID idZona = context.getProperty("idZona", UUID.class);

    return zonaRepository.getById(idZona);
  }
}
