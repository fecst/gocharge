package br.com.gocharge.mappers;

import br.com.gocharge.domain.Totem;
import br.com.gocharge.model.TotemModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class TotemMapper {
    public static final TotemMapper INSTANCE = Mappers.getMapper(TotemMapper.class);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "valor.bandeira.status", ignore = true)
    public abstract TotemModel toModel(Totem totem);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "valor.bandeira.status", ignore = true)
    public abstract Totem toDomain(TotemModel totemModel);
}
