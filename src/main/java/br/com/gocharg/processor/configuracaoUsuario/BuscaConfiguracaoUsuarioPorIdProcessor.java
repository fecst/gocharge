package br.com.gocharg.processor.configuracaoUsuario;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.ConfiguracaoUsuario;
import br.com.gocharg.repository.ConfiguracaoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BuscaConfiguracaoUsuarioPorIdProcessor
        implements CommandProcessor<ConfiguracaoUsuario> {

    @Autowired
    private ConfiguracaoUsuarioRepository configuracaoUsuarioRepository;

    @Override
    public ConfiguracaoUsuario process(CommandContext context) {
        UUID id = context.getProperty("idConfiguracao", UUID.class);

        return configuracaoUsuarioRepository.getById(id);
    }
}
