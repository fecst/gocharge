package br.com.gocharg.processor.usuario;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Usuario;
import br.com.gocharg.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaUsuariosProcessor implements CommandProcessor<List<Usuario>> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> process(CommandContext commandContext) {
        return usuarioRepository.getAll();
    }
}
