package br.com.gocharge.mappers;

import br.com.gocharge.domain.Totem;
import br.com.gocharge.model.TotemModel;
import org.mapstruct.factory.Mappers;

public interface TotemMapper {

  TotemMapper INSTANCE = Mappers.getMapper(TotemMapper.class);

  TotemModel toModel(Totem totem);

  Totem toDomain(TotemModel totemModel);
}
