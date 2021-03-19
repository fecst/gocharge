package br.com.gocharge.processor.bandeira;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Bandeira;
import br.com.gocharge.enums.StatusCadastroEnum;
import br.com.gocharge.repository.BandeiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CadastraBandeiraProcessor implements CommandProcessor<Bandeira> {

  @Autowired private BandeiraRepository bandeiraRepository;

  @Override
  public Bandeira process(CommandContext context) {
    Bandeira bandeira = context.getProperty("bandeira", Bandeira.class);

    bandeira.setDataHoraCadastro(LocalDateTime.now().toString());
    bandeira.setStatus(StatusCadastroEnum.ATIVO.getCodigo());

    return bandeiraRepository.create(bandeira);
  }
}
