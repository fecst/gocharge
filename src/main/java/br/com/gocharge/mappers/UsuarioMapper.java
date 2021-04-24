package br.com.gocharge.mappers;

import br.com.gocharge.domain.Cidade;
import br.com.gocharge.domain.Estado;
import br.com.gocharge.domain.Usuario;
import br.com.gocharge.dto.UsuarioDTO;
import br.com.gocharge.enums.CategoriaUsuarioEnum;
import br.com.gocharge.enums.StatusUsuarioEnum;
import br.com.gocharge.enums.TipoUsuarioEnum;
import br.com.gocharge.model.*;
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
    @Mapping(target = "tipoUsuario", ignore = true)
    @Mapping(target = "categoriaUsuario", ignore = true)
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
    @Mapping(target = "tipoUsuario", ignore = true)
    @Mapping(target = "categoriaUsuario", ignore = true)
    public abstract Usuario toDomain(UsuarioModel usuarioModel);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "cidade", source = "cidade")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "tipoUsuario", ignore = true)
    @Mapping(target = "categoriaUsuario", ignore = true)
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
    @Mapping(target = "tipoUsuario", ignore = true)
    @Mapping(target = "categoriaUsuario", ignore = true)
    public abstract UsuarioModel toModel(Usuario usuario);

    public abstract List<UsuarioModel> toModel(List<Usuario> usuario);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataHoraCadastro", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "cidade", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "tipoUsuario", ignore = true)
    @Mapping(target = "categoriaUsuario", ignore = true)
    public abstract void updateFrom(
            final Usuario usuario, @MappingTarget final UsuarioModel usuarioModel);

    @AfterMapping
    void afterMapping(Usuario usuario, @MappingTarget UsuarioModel usuarioModel) {
        if (Objects.nonNull(usuario.getStatus())) {
            StatusUsuarioModel statusUsuarioModel = new StatusUsuarioModel();
            statusUsuarioModel.setId(usuario.getStatus().getCodigo());

            usuarioModel.setStatus(statusUsuarioModel);
        }

        if (Objects.nonNull(usuario.getCidade())) {
            CidadeModel cidadeModel = new CidadeModel();
            cidadeModel.setId(usuario.getCidade().getId());
            cidadeModel.setDescricao(usuario.getCidade().getDescricao());
            cidadeModel.setEstado(EstadoMapper.INSTANCE.toModel(usuario.getCidade().getEstado()));

            usuarioModel.setCidade(cidadeModel);
        }

        if (Objects.nonNull(usuario.getEstado())) {
            EstadoModel estadoModel = new EstadoModel();
            estadoModel.setId(usuario.getEstado().getId());
            estadoModel.setDescricao(usuario.getEstado().getDescricao());

            usuarioModel.setEstado(estadoModel);
        }

        if (Objects.nonNull(usuario.getTipoUsuario())) {
            TipoUsuarioModel tipoUsuarioModel = new TipoUsuarioModel();
            tipoUsuarioModel.setId(usuario.getTipoUsuario().getId());

            usuarioModel.setTipoUsuario(tipoUsuarioModel);
        } else {
            usuarioModel.setTipoUsuario(null);
        }

        if (Objects.nonNull(usuario.getCategoriaUsuario())) {
            CategoriaUsuarioModel categoriaUsuarioModel = new CategoriaUsuarioModel();
            categoriaUsuarioModel.setId(usuario.getCategoriaUsuario().getId());

            usuarioModel.setCategoriaUsuario(categoriaUsuarioModel);
        } else {
            usuarioModel.setCategoriaUsuario(null);
        }
    }

    @AfterMapping
    void afterMapping(UsuarioModel usuarioModel, @MappingTarget Usuario usuario) {
        usuario.setStatus(StatusUsuarioEnum.get(usuarioModel.getStatus().getId()));

        if (Objects.nonNull(usuarioModel.getTipoUsuario())) {
            usuario.setTipoUsuario(TipoUsuarioEnum.get(usuarioModel.getTipoUsuario().getId()));
        }

        if (Objects.nonNull(usuarioModel.getCategoriaUsuario())) {
            usuario.setCategoriaUsuario(
                    CategoriaUsuarioEnum.get(usuarioModel.getCategoriaUsuario().getId()));
        }
    }

    @AfterMapping
    void afterMapping(UsuarioDTO usuarioDTO, @MappingTarget Usuario usuario) {
        usuario.setStatus(StatusUsuarioEnum.get(usuarioDTO.getStatus()));

        if (Objects.nonNull(usuarioDTO.getTipoUsuario())) {
            usuario.setTipoUsuario(TipoUsuarioEnum.get(Integer.valueOf(usuarioDTO.getTipoUsuario())));
        }

        if (Objects.nonNull(usuarioDTO.getCategoriaUsuario())) {
            usuario.setCategoriaUsuario(CategoriaUsuarioEnum.get(usuarioDTO.getCategoriaUsuario()));
        }
    }

    @AfterMapping
    void afterMapping(Usuario usuario, @MappingTarget UsuarioDTO usuarioDTO) {
        usuarioDTO.setStatus(StatusUsuarioEnum.get(usuario.getStatus().getCodigo()).getCodigo());

        if (Objects.nonNull(usuario.getCidade())) {
            usuarioDTO.setCidade(usuario.getCidade().getId().toString());
        }

        if (Objects.nonNull(usuario.getEstado())) {
            usuarioDTO.setEstado(usuario.getEstado().getId());
        }

        if (Objects.nonNull(usuario.getTipoUsuario())) {
            usuarioDTO.setTipoUsuario(
                    TipoUsuarioEnum.get(Integer.valueOf(usuario.getTipoUsuario().getId()))
                            .getId()
                            .toString());
        }

        if (Objects.nonNull(usuario.getCategoriaUsuario())) {
            usuarioDTO.setCategoriaUsuario(
                    CategoriaUsuarioEnum.get(usuario.getCategoriaUsuario().getId()).getId());
        }
    }
}
