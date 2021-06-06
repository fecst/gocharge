package br.com.gocharg.mappers;

import br.com.gocharg.domain.*;
import br.com.gocharg.dto.TotemDTO;
import br.com.gocharg.enums.CategoriaEnum;
import br.com.gocharg.enums.StatusTotemEnum;
import br.com.gocharg.model.CategoriaModel;
import br.com.gocharg.model.StatusTotemModel;
import br.com.gocharg.model.TotemModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

@Mapper(
    uses = {
      ConverterMapper.class,
      EstadoMapper.class,
      CidadeMapper.class,
      ZonaMapper.class,
      SubZonaMapper.class,
      ValorMapper.class,
      FabricanteMapper.class,
      UsuarioMapper.class
    })
public abstract class TotemMapper {
  public static final TotemMapper INSTANCE = Mappers.getMapper(TotemMapper.class);

  @Mapping(target = "status", ignore = true)
  public abstract TotemModel toModel(Totem totem);

  public abstract List<TotemModel> toModel(List<Totem> totem);

  @Mapping(target = "status", ignore = true)
  @Mapping(target = "estado", ignore = true)
  @Mapping(target = "cidade", ignore = true)
  @Mapping(target = "zona", ignore = true)
  @Mapping(target = "subZona", ignore = true)
  @Mapping(target = "valor", ignore = true)
  @Mapping(target = "fabricante", ignore = true)
  @Mapping(target = "proprietario", ignore = true)
  @Mapping(target = "categoria", ignore = true)
  @Mapping(target = "id", source = "id", qualifiedByName = ConverterMapper.UUID_TO_STRING)
  public abstract TotemDTO toDTO(Totem totem);

  public abstract List<TotemDTO> toDTO(List<Totem> totem);

  @Mapping(target = "status", ignore = true)
  @Mapping(target = "categoria", ignore = true)
  public abstract Totem toDomain(TotemModel totemModel);

  public abstract List<Totem> toDomain(List<TotemModel> totemModel);

  @Mapping(target = "estado", source = "estado")
  @Mapping(target = "cidade", source = "cidade")
  @Mapping(target = "zona", source = "zona")
  @Mapping(target = "subZona", source = "subZona")
  @Mapping(target = "valor", source = "valor")
  @Mapping(target = "fabricante", source = "fabricante")
  @Mapping(target = "proprietario", source = "proprietario")
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "categoria", ignore = true)
  @Mapping(target = "id", source = "totemDTO.id", qualifiedByName = ConverterMapper.STRING_TO_UUID)
  @Mapping(
      target = "maps",
      source = "totemDTO.maps",
      qualifiedByName = ConverterMapper.STRING_TO_BOOLEAN)
  @Mapping(
      target = "propriedadeGoCharg",
      source = "totemDTO.propriedadeGoCharg",
      qualifiedByName = ConverterMapper.STRING_TO_BOOLEAN)
  @Mapping(
      target = "dataHoraCadastro",
      source = "totemDTO.dataHoraCadastro",
      qualifiedByName = ConverterMapper.STRING_TO_LOCAL_DATE_TIME)
  public abstract Totem toDomain(
      TotemDTO totemDTO,
      Estado estado,
      Cidade cidade,
      Zona zona,
      SubZona subZona,
      Valor valor,
      Fabricante fabricante,
      Usuario proprietario);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "dataHoraCadastro", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "categoria", ignore = true)
  public abstract void updateFrom(Totem totem, @MappingTarget final TotemModel totemModel);

  @AfterMapping
  void afterMapping(TotemDTO totemDTO, @MappingTarget Totem totem) {
    totem.setStatus(StatusTotemEnum.get(Integer.valueOf(totemDTO.getStatus())));
    if (Objects.nonNull(totemDTO.getCategoria())) {
      totem.setCategoria(CategoriaEnum.get(totemDTO.getCategoria()));
    }
  }

  @AfterMapping
  void afterMapping(Totem totem, @MappingTarget TotemModel totemModel) {
    if (Objects.nonNull(totem.getStatus())) {
      StatusTotemModel statusTotemModel = new StatusTotemModel();
      statusTotemModel.setId(totem.getStatus().getCodigo());

      totemModel.setStatus(statusTotemModel);
    }

    if (Objects.nonNull(totem.getCategoria())) {
      CategoriaModel categoriaModel = new CategoriaModel();
      categoriaModel.setId(totem.getCategoria().getId());

      totemModel.setCategoria(categoriaModel);
    }
  }

  @AfterMapping
  void afterMapping(TotemModel totemModel, @MappingTarget Totem totem) {
    totem.setStatus(StatusTotemEnum.get(totemModel.getStatus().getId()));
    totem.setCategoria(CategoriaEnum.get(totemModel.getCategoria().getId()));
  }

  @AfterMapping
  void afterMapping(Totem totem, @MappingTarget TotemDTO totemDTO) {
    totemDTO.setStatus(totem.getStatus().getCodigo().toString());
    totemDTO.setEstado(totem.getEstado().getId());
    totemDTO.setCidade(totem.getCidade().getId().toString());
    totemDTO.setZona(totem.getZona().getId().toString());
    totemDTO.setSubZona(totem.getSubZona().getId().toString());
    totemDTO.setValor(totem.getValor().getId().toString());
    totemDTO.setFabricante(totem.getFabricante().getId().toString());
    totemDTO.setCategoria(totem.getCategoria().getId());
    totemDTO.setProprietario(totem.getProprietario().getId().toString());
  }
}
