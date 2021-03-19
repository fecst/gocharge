package br.com.gocharge.processor.cidade;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Bandeira;
import br.com.gocharge.domain.Cidade;
import br.com.gocharge.enums.StatusCadastroEnum;
import br.com.gocharge.repository.BandeiraRepository;
import br.com.gocharge.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CadastraCidadeProcessor implements CommandProcessor<Cidade> {

  @Autowired private CidadeRepository cidadeRepository;

  @Override
  public Cidade process(CommandContext context) {
    Cidade cidade = context.getProperty("cidade", Cidade.class);

    return cidadeRepository.create(cidade);
  }
}
