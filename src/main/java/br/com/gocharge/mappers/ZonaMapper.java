package br.com.gocharge.mappers;

import br.com.gocharge.domain.Zona;
import br.com.gocharge.model.ZonaModel;
import org.mapstruct.factory.Mappers;

public interface ZonaMapper {
  ZonaMapper INSTANCE = Mappers.getMapper(ZonaMapper.class);

  ZonaModel toModel(Zona zona);

  Zona toDomain(ZonaModel zonaModel);
}
