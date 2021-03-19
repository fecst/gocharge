package br.com.gocharge.processor.estado;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Estado;
import br.com.gocharge.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaEstadosProcessor implements CommandProcessor<List<Estado>> {

  @Autowired private EstadoRepository estadoRepository;

  @Override
  public List<Estado> process(CommandContext commandContext) {
    return estadoRepository.getAll();
  }
}
