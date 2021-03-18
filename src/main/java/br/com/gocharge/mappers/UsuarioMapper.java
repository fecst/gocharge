package br.com.gocharge.mappers;

import br.com.gocharge.domain.Usuario;
import br.com.gocharge.model.UsuarioModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public interface UsuarioMapper {
  UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

  UsuarioModel toModel(Usuario usuario);

  Usuario toDomain(UsuarioModel usuarioModel);
}
