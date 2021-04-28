package br.com.gocharg.mappers;

import br.com.gocharg.domain.*;
import br.com.gocharg.dto.TotemDTO;
import br.com.gocharg.enums.StatusTotemEnum;
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
      ValorMapper.class
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
  public abstract TotemDTO toDTO(Totem totem);

  public abstract List<TotemDTO> toDTO(List<Totem> totem);

  @Mapping(target = "status", ignore = true)
  public abstract Totem toDomain(TotemModel totemModel);

  public abstract List<Totem> toDomain(List<TotemModel> totemModel);

  @Mapping(target = "estado", source = "estado")
  @Mapping(target = "cidade", source = "cidade")
  @Mapping(target = "zona", source = "zona")
  @Mapping(target = "subZona", source = "subZona")
  @Mapping(target = "valor", source = "valor")
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "id", source = "totemDTO.id", qualifiedByName = ConverterMapper.STRING_TO_UUID)
  @Mapping(
      target = "dataHoraCadastro",
      source = "totemDTO.dataHoraCadastro",
      qualifiedByName = ConverterMapper.STRING_TO_LOCAL_DATE_TIME)
  public abstract Totem toDomain(
      TotemDTO totemDTO, Estado estado, Cidade cidade, Zona zona, SubZona subZona, Valor valor);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "dataHoraCadastro", ignore = true)
  @Mapping(target = "status", ignore = true)
  //  @Mapping(target = "estado", ignore = true)
  //  @Mapping(target = "cidade", ignore = true)
  //  @Mapping(target = "zona", ignore = true)
  //  @Mapping(target = "subZona", ignore = true)
  //  @Mapping(target = "valor", ignore = true)
  public abstract void updateFrom(Totem totem, @MappingTarget final TotemModel totemModel);

  @AfterMapping
  void afterMapping(TotemDTO totemDTO, @MappingTarget Totem totem) {
    if (Objects.nonNull(totemDTO.getStatus())) {
      totem.setStatus(StatusTotemEnum.get(totemDTO.getStatus()));
    }
  }

  @AfterMapping
  void afterMapping(Totem totem, @MappingTarget TotemModel totemModel) {
    if (Objects.nonNull(totem.getStatus())) {
      StatusTotemModel statusTotemModel = new StatusTotemModel();
      statusTotemModel.setId(totem.getStatus().getCodigo());

      totemModel.setStatus(statusTotemModel);
    }
  }

  @AfterMapping
  void afterMapping(TotemModel totemModel, @MappingTarget Totem totem) {
    totem.setStatus(StatusTotemEnum.get(totemModel.getStatus().getId()));
  }

  @AfterMapping
  void afterMapping(Totem totem, @MappingTarget TotemDTO totemDTO) {
    totemDTO.setStatus(totem.getStatus().getCodigo());
    totemDTO.setEstado(totem.getEstado().getId());
    totemDTO.setCidade(totem.getCidade().getId().toString());
    totemDTO.setZona(totem.getZona().getId().toString());
    totemDTO.setSubZona(totem.getSubZona().getId().toString());
    totemDTO.setValor(totem.getValor().getId().toString());
  }
}
