package br.com.gocharge.processor.usuario;

import br.com.fluentvalidator.context.Error;
import br.com.fluentvalidator.context.ValidationResult;
import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Bandeira;
import br.com.gocharge.domain.Usuario;
import br.com.gocharge.enums.StatusCadastroEnum;
import br.com.gocharge.enums.StatusUsuarioEnum;
import br.com.gocharge.exceptions.BadRequestException;
import br.com.gocharge.repository.BandeiraRepository;
import br.com.gocharge.repository.UsuarioRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CadastraUsuarioProcessor implements CommandProcessor<Usuario> {

  @Autowired private UsuarioRepository usuarioRepository;

  @Override
  public Usuario process(CommandContext context) {
    Usuario usuario = context.getProperty("usuario", Usuario.class);

    usuario.setDataHoraCadastro(LocalDateTime.now().toString());
    usuario.setStatus(StatusUsuarioEnum.ATIVO.getCodigo());

    return usuarioRepository.create(usuario);
  }
}
