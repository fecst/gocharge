package br.com.gocharge.processor.usuario;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Bandeira;
import br.com.gocharge.domain.Usuario;
import br.com.gocharge.dto.UsuarioDTO;
import br.com.gocharge.mappers.UsuarioMapper;
import br.com.gocharge.repository.BandeiraRepository;
import br.com.gocharge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BuscaUsuarioPorIdProcessor implements CommandProcessor<UsuarioDTO> {

  @Autowired private UsuarioRepository usuarioRepository;

  @Override
  public UsuarioDTO process(CommandContext context) {
    UUID idUsuario = context.getProperty("idUsuario", UUID.class);

    return UsuarioMapper.INSTANCE.toDTO(usuarioRepository.getById(idUsuario));
  }
}
