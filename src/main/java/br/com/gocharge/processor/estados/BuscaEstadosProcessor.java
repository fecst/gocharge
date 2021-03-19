package br.com.gocharge.processor.estados;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Estado;
import br.com.gocharge.exceptions.NoContentException;
import br.com.gocharge.mappers.EstadoMapper;
import br.com.gocharge.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaEstadosProcessor implements CommandProcessor<List<Estado>> {

  @Autowired EstadoRepository estadoRepository;

  @Override
  public List<Estado> process(CommandContext commandContext) {
    return estadoRepository.getAll();
  }
}
