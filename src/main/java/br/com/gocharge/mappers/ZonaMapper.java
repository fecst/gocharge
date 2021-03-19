package br.com.gocharge.mappers;

import br.com.gocharge.domain.Zona;
import br.com.gocharge.model.ZonaModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ZonaMapper {
  public static final ZonaMapper INSTANCE = Mappers.getMapper(ZonaMapper.class);

  public abstract ZonaModel toModel(Zona zona);

  public abstract Zona toDomain(ZonaModel zonaModel);
}
