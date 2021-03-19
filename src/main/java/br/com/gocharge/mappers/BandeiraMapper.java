package br.com.gocharge.mappers;

import br.com.gocharge.domain.Bandeira;
import br.com.gocharge.model.BandeiraModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class BandeiraMapper {
  public static final BandeiraMapper INSTANCE = Mappers.getMapper(BandeiraMapper.class);

  @Mapping(target = "status", ignore = true)
  public abstract BandeiraModel toModel(Bandeira bandeira);

  @Mapping(target = "status", ignore = true)
  public abstract Bandeira toDomain(BandeiraModel bandeiraModel);
}
