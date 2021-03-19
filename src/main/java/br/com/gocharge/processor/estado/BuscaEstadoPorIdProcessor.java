package br.com.gocharge.processor.estado;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Estado;
import br.com.gocharge.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BuscaEstadoPorIdProcessor implements CommandProcessor<Estado> {

  @Autowired private EstadoRepository estadoRepository;

  @Override
  public Estado process(CommandContext context) {
    UUID idEstado = context.getProperty("idEstado", UUID.class);

    return estadoRepository.getById(idEstado);
  }
}
