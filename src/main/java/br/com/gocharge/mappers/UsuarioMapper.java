package br.com.gocharge.mappers;

import br.com.gocharge.domain.Cidade;
import br.com.gocharge.domain.Estado;
import br.com.gocharge.domain.Usuario;
import br.com.gocharge.dto.UsuarioDTO;
import br.com.gocharge.enums.StatusUsuarioEnum;
import br.com.gocharge.model.StatusUsuarioModel;
import br.com.gocharge.model.UsuarioModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

@Mapper(uses = {ConverterMapper.class})
public abstract class UsuarioMapper {
  public static final UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

  @Mapping(target = "status", ignore = true)
  @Mapping(target = "cidade", ignore = true)
  @Mapping(target = "estado", ignore = true)
  @Mapping(target = "id", source = "id", qualifiedByName = ConverterMapper.UUID_TO_STRING)
  @Mapping(
      target = "dataHoraCadastro",
      source = "dataHoraCadastro",
      qualifiedByName = ConverterMapper.LOCAL_DATE_TIME_TO_STRING)
  @Mapping(
      target = "dataHoraBloqueio",
      source = "dataHoraBloqueio",
      qualifiedByName = ConverterMapper.LOCAL_DATE_TIME_TO_STRING)
  @Mapping(
      target = "dataNascimento",
      source = "dataNascimento",
      qualifiedByName = ConverterMapper.DATE_TO_STRING)
  public abstract UsuarioDTO toDTO(Usuario usuario);

  public abstract List<UsuarioDTO> toDTO(List<Usuario> usuarioList);

  @Mapping(target = "status", ignore = true)
  public abstract Usuario toDomain(UsuarioModel usuarioModel);

  @Mapping(target = "status", ignore = true)
  @Mapping(target = "cidade", source = "cidade")
  @Mapping(target = "estado", source = "estado")
  @Mapping(
      target = "dataHoraCadastro",
      source = "usuarioDTO.dataHoraCadastro",
      qualifiedByName = ConverterMapper.STRING_TO_LOCAL_DATE_TIME)
  @Mapping(
      target = "dataHoraBloqueio",
      source = "usuarioDTO.dataHoraBloqueio",
      qualifiedByName = ConverterMapper.STRING_TO_LOCAL_DATE_TIME)
  @Mapping(
      target = "dataNascimento",
      source = "usuarioDTO.dataNascimento",
      qualifiedByName = ConverterMapper.STRING_TO_DATE)
  @Mapping(
      target = "id",
      source = "usuarioDTO.id",
      qualifiedByName = ConverterMapper.STRING_TO_UUID)
  public abstract Usuario toDomain(UsuarioDTO usuarioDTO, Estado estado, Cidade cidade);

  public abstract List<Usuario> toDomain(List<UsuarioModel> usuarioModel);

  @Mapping(target = "status", ignore = true)
  public abstract UsuarioModel toModel(Usuario usuario);

  public abstract List<UsuarioModel> toModel(List<Usuario> usuario);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "dataHoraCadastro", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "cidade", ignore = true)
  @Mapping(target = "estado", ignore = true)
  public abstract void updateFrom(
      final Usuario usuario, @MappingTarget final UsuarioModel usuarioModel);

  @AfterMapping
  void afterMapping(Usuario usuario, @MappingTarget UsuarioModel usuarioModel) {
    if (Objects.nonNull(usuario.getStatus())) {
      StatusUsuarioModel statusUsuarioModel = new StatusUsuarioModel();
      statusUsuarioModel.setId(usuario.getStatus().getCodigo());

      usuarioModel.setStatus(statusUsuarioModel);
    }
  }

  @AfterMapping
  void afterMapping(UsuarioModel usuarioModel, @MappingTarget Usuario usuario) {
    usuario.setStatus(StatusUsuarioEnum.get(usuarioModel.getStatus().getId()));
  }

  @AfterMapping
  void afterMapping(UsuarioDTO usuarioDTO, @MappingTarget Usuario usuario) {
    usuario.setStatus(StatusUsuarioEnum.get(usuarioDTO.getStatus()));
  }

  @AfterMapping
  void afterMapping(Usuario usuario, @MappingTarget UsuarioDTO usuarioDTO) {
    usuarioDTO.setStatus(StatusUsuarioEnum.get(usuario.getStatus().getCodigo()).getDescricao());

    if (Objects.nonNull(usuario.getCidade())) {
      usuarioDTO.setCidade(usuario.getCidade().getId().toString());
    }

    if (Objects.nonNull(usuario.getEstado())) {
      usuarioDTO.setEstado(usuario.getEstado().getId());
    }
  }
}
