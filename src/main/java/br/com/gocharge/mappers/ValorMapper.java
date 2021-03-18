package br.com.gocharge.mappers;

import br.com.gocharge.domain.Valor;
import br.com.gocharge.model.ValorModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public interface ValorMapper {
  ValorMapper INSTANCE = Mappers.getMapper(ValorMapper.class);

  ValorModel toModel(Valor valor);

  Valor toDomain(ValorModel valorModel);
}
