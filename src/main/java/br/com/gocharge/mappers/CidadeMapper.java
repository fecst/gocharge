package br.com.gocharge.mappers;

import br.com.gocharge.domain.Cidade;
import br.com.gocharge.model.CidadeModel;
import org.mapstruct.factory.Mappers;

public interface CidadeMapper {
  CidadeMapper INSTANCE = Mappers.getMapper(CidadeMapper.class);

  CidadeModel toModel(Cidade cidade);

  Cidade toDomain(CidadeModel cidadeModel);
}
