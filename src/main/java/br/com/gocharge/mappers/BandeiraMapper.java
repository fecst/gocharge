package br.com.gocharge.mappers;

import br.com.gocharge.domain.Bandeira;
import br.com.gocharge.model.BandeiraModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public interface BandeiraMapper {
  BandeiraMapper INSTANCE = Mappers.getMapper(BandeiraMapper.class);

  BandeiraModel toModel(Bandeira bandeira);

  Bandeira toDomain(BandeiraModel bandeiraModel);
}
