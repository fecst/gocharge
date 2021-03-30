package br.com.gocharge.processor.cidade;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Cidade;
import br.com.gocharge.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaCidadesPorEstadoProcessor implements CommandProcessor<List<Cidade>> {

  @Autowired private CidadeRepository cidadeRepository;

  @Override
  public List<Cidade> process(CommandContext context) {
    String idEstado = context.getProperty("idEstado", String.class);

    return cidadeRepository.getByEstado(idEstado);
  }
}
