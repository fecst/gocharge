package br.com.gocharge.mappers;

import br.com.gocharge.domain.Valor;
import br.com.gocharge.model.ValorModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ValorMapper {
  public static final ValorMapper INSTANCE = Mappers.getMapper(ValorMapper.class);

  @Mapping(target = "bandeira.status", ignore = true)
  public abstract ValorModel toModel(Valor valor);

  @Mapping(target = "bandeira.status", ignore = true)
  public abstract Valor toDomain(ValorModel valorModel);
}
