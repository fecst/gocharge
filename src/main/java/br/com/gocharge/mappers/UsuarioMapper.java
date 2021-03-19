package br.com.gocharge.mappers;

import br.com.gocharge.domain.Usuario;
import br.com.gocharge.model.UsuarioModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UsuarioMapper {
  public static final UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

  @Mapping(target = "status", ignore = true)
  @Mapping(target = "tipoPessoa", ignore = true)
  public abstract UsuarioModel toModel(Usuario usuario);

  @Mapping(target = "status", ignore = true)
  @Mapping(target = "tipoPessoa", ignore = true)
  public abstract Usuario toDomain(UsuarioModel usuarioModel);
}
