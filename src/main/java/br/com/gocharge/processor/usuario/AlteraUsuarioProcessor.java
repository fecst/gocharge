package br.com.gocharge.processor.usuario;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Usuario;
import br.com.gocharge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlteraUsuarioProcessor implements CommandProcessor<Usuario> {

  @Autowired private UsuarioRepository usuarioRepository;

  @Override
  public Usuario process(CommandContext context) {
    Usuario usuario = context.getProperty("usuario", Usuario.class);

    return usuarioRepository.update(usuario);
  }
}
