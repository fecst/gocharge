package br.com.gocharge.mappers;

import br.com.gocharge.domain.ConfiguracaoUsuario;
import br.com.gocharge.model.ConfiguracaoUsuarioModel;
import org.mapstruct.factory.Mappers;

public interface ConfiguracaoUsuarioMapper {
  ConfiguracaoUsuarioMapper INSTANCE = Mappers.getMapper(ConfiguracaoUsuarioMapper.class);

  ConfiguracaoUsuarioModel toModel(ConfiguracaoUsuario configuracaoUsuario);

  ConfiguracaoUsuario toDomain(ConfiguracaoUsuarioModel configuracaoUsuarioModel);
}
