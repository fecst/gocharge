package br.com.gocharge.mappers;

import br.com.gocharge.domain.SubZona;
import br.com.gocharge.model.SubZonaModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public interface SubZonaMapper {
  SubZonaMapper INSTANCE = Mappers.getMapper(SubZonaMapper.class);

  SubZonaModel toModel(SubZona subZona);

  SubZona toDomain(SubZonaModel estadoModel);
}
