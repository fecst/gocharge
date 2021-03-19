package br.com.gocharge.processor.estados;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Estado;
import br.com.gocharge.mappers.EstadoMapper;
import br.com.gocharge.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ApagaEstadoPorIdProcessor implements CommandProcessor<Estado> {

  @Autowired EstadoRepository estadoRepository;

  @Autowired BuscaEstadoPorIdProcessor buscaEstadoPorIdProcessor;

  @Override
  public Estado process(CommandContext context) {
    UUID idEstado = context.getProperty("idEstado", UUID.class);

    estadoRepository.delete(idEstado);

    return null;
  }
}
