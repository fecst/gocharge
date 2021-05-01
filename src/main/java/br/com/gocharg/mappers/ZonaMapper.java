package br.com.gocharg.mappers;

import br.com.gocharg.domain.Cidade;
import br.com.gocharg.domain.Estado;
import br.com.gocharg.domain.Zona;
import br.com.gocharg.dto.ZonaDTO;
import br.com.gocharg.model.ZonaModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ConverterMapper.class, EstadoMapper.class, CidadeMapper.class})
public abstract class ZonaMapper {
  public static final ZonaMapper INSTANCE = Mappers.getMapper(ZonaMapper.class);

  @Mapping(target = "cidade", ignore = true)
  @Mapping(target = "estado", ignore = true)
  public abstract ZonaModel toModel(Zona zona);

  @Mapping(target = "cidade", ignore = true)
  @Mapping(target = "estado", ignore = true)
  public abstract Zona toDomain(ZonaModel zonaModel);

  @Mapping(target = "id", source = "zonaDTO.id", qualifiedByName = ConverterMapper.STRING_TO_UUID)
  @Mapping(target = "cidade", source = "cidade")
  @Mapping(target = "estado", source = "estado")
  @Mapping(target = "descricao", source = "zonaDTO.descricao")
  public abstract Zona toDomain(ZonaDTO zonaDTO, Cidade cidade, Estado estado);

  @Mapping(target = "id", source = "id", qualifiedByName = ConverterMapper.UUID_TO_STRING)
  @Mapping(target = "cidade", ignore = true)
  @Mapping(target = "estado", ignore = true)
  public abstract ZonaDTO toDTO(Zona zona);

  public abstract List<Zona> toDomain(List<ZonaModel> zonas);

  public abstract List<ZonaDTO> toDTO(List<Zona> zonas);

  @Mapping(target = "id", ignore = true)
  public abstract void updateFrom(final Zona zona, @MappingTarget final ZonaModel zonaModel);

  @AfterMapping
  void afterMapping(Zona zona, @MappingTarget ZonaDTO zonaDTO) {
    zonaDTO.setCidade(zona.getCidade().getId().toString());
    zonaDTO.setEstado(zona.getEstado().getId());
  }

  @AfterMapping
  void afterMapping(Zona zona, @MappingTarget ZonaModel zonaModel) {
    zonaModel.setCidade(CidadeMapper.INSTANCE.toModel(zona.getCidade()));
    zonaModel.setEstado(EstadoMapper.INSTANCE.toModel(zona.getEstado()));
  }

  @AfterMapping
  void afterMapping(ZonaModel zonaModel, @MappingTarget Zona zona) {
    zona.setCidade(CidadeMapper.INSTANCE.toDomain(zonaModel.getCidade()));
    zona.setEstado(EstadoMapper.INSTANCE.toDomain(zonaModel.getEstado()));
  }
}
