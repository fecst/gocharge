package br.com.gocharge.processor.cidade;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Bandeira;
import br.com.gocharge.domain.Cidade;
import br.com.gocharge.repository.BandeiraRepository;
import br.com.gocharge.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BuscaCidadePorIdProcessor implements CommandProcessor<Cidade> {

  @Autowired private CidadeRepository cidadeRepository;

  @Override
  public Cidade process(CommandContext context) {
    UUID id = context.getProperty("idCidade", UUID.class);

    return cidadeRepository.getById(id);
  }
}
