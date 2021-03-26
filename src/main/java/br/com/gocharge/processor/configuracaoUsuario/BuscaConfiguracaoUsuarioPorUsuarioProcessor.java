package br.com.gocharge.processor.configuracaoUsuario;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.ConfiguracaoUsuario;
import br.com.gocharge.repository.ConfiguracaoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BuscaConfiguracaoUsuarioPorUsuarioProcessor
    implements CommandProcessor<ConfiguracaoUsuario> {

  @Autowired private ConfiguracaoUsuarioRepository configuracaoUsuarioRepository;

  @Override
  public ConfiguracaoUsuario process(CommandContext context) {
    UUID idUsuario = context.getProperty("idUsuario", UUID.class);

    return configuracaoUsuarioRepository.getByUsuario(idUsuario);
  }
}
