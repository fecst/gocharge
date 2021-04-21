package br.com.gocharge.processor.usuario;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Usuario;
import br.com.gocharge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ApagaUsuarioPorIdProcessor implements CommandProcessor<Usuario> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario process(CommandContext context) {
        UUID idUsuario = context.getProperty("idUsuario", UUID.class);

        usuarioRepository.delete(idUsuario);

        return null;
    }
}
