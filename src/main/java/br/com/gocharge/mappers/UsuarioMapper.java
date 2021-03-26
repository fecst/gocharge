package br.com.gocharge.mappers;

import br.com.gocharge.domain.Usuario;
import br.com.gocharge.enums.StatusCadastroEnum;
import br.com.gocharge.model.StatusUsuarioModel;
import br.com.gocharge.model.UsuarioModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class UsuarioMapper {
  public static final UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

  @Mapping(target = "status", ignore = true)
  @Mapping(
      target = "dataHoraCadastro",
      source = "dataHoraCadastro",
      qualifiedByName = ConverterMapper.STRING_TO_LOCAL_DATE_TIME)
  @Mapping(
      target = "dataHoraBloqueio",
      source = "dataHoraBloqueio",
      qualifiedByName = ConverterMapper.STRING_TO_LOCAL_DATE_TIME)
  @Mapping(
      target = "dataNascimento",
      source = "dataNascimento",
      qualifiedByName = ConverterMapper.STRING_TO_DATE)
  public abstract UsuarioModel toModel(Usuario usuario);

  @Mapping(target = "status", ignore = true)
  public abstract Usuario toDomain(UsuarioModel usuarioModel);

  public abstract List<Usuario> toDomain(List<UsuarioModel> usuarioModel);

  public abstract List<UsuarioModel> toModel(List<Usuario> usuario);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "dataHoraCadastro", ignore = true)
  @Mapping(target = "cpf", ignore = true)
  @Mapping(target = "dataNascimento", ignore = true)
  @Mapping(target = "status", ignore = true)
  public abstract void updateFrom(
      final Usuario usuario, @MappingTarget final UsuarioModel usuarioModel);

  @AfterMapping
  void afterMapping(Usuario usuario, @MappingTarget UsuarioModel usuarioModel) {
    StatusUsuarioModel statusUsuarioModel = new StatusUsuarioModel();
    statusUsuarioModel.setId(usuario.getStatus());

    usuarioModel.setStatus(statusUsuarioModel);
  }

  @AfterMapping
  void afterMapping(UsuarioModel usuarioModel, @MappingTarget Usuario usuario) {
    usuario.setStatus(StatusCadastroEnum.get(usuarioModel.getStatus().getId()).getDescricao());
  }
}
