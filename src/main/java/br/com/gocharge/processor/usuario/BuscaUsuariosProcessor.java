package br.com.gocharge.processor.usuario;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Usuario;
import br.com.gocharge.dto.UsuarioDTO;
import br.com.gocharge.mappers.UsuarioMapper;
import br.com.gocharge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaUsuariosProcessor implements CommandProcessor<List<UsuarioDTO>> {

  @Autowired private UsuarioRepository usuarioRepository;

  @Override
  public List<UsuarioDTO> process(CommandContext commandContext) {
    return UsuarioMapper.INSTANCE.toDTO(usuarioRepository.getAll());
  }
}
