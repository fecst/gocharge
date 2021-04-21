package br.com.gocharge.mappers;

import br.com.gocharge.domain.ConfiguracaoUsuario;
import br.com.gocharge.model.ConfiguracaoUsuarioModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ConfiguracaoUsuarioMapper {
    public static final ConfiguracaoUsuarioMapper INSTANCE =
            Mappers.getMapper(ConfiguracaoUsuarioMapper.class);

    public abstract ConfiguracaoUsuarioModel toModel(ConfiguracaoUsuario configuracaoUsuario);

    public abstract ConfiguracaoUsuario toDomain(ConfiguracaoUsuarioModel configuracaoUsuarioModel);
}
