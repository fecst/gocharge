package br.com.gocharg.processor.usuario;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Usuario;
import br.com.gocharg.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BuscaUsuarioPorIdProcessor implements CommandProcessor<Usuario> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario process(CommandContext context) {
        UUID idUsuario = context.getProperty("idUsuario", UUID.class);

        return usuarioRepository.getById(idUsuario);
    }
}
