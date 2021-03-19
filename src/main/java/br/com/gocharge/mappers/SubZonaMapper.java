package br.com.gocharge.mappers;

import br.com.gocharge.domain.SubZona;
import br.com.gocharge.model.SubZonaModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class SubZonaMapper {
  public static final SubZonaMapper INSTANCE = Mappers.getMapper(SubZonaMapper.class);

  public abstract SubZonaModel toModel(SubZona subZona);

  public abstract SubZona toDomain(SubZonaModel estadoModel);
}
