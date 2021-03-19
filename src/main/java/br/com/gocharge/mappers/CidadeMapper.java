package br.com.gocharge.mappers;

import br.com.gocharge.domain.Cidade;
import br.com.gocharge.model.CidadeModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class CidadeMapper {
  public static final CidadeMapper INSTANCE = Mappers.getMapper(CidadeMapper.class);

  public abstract CidadeModel toModel(Cidade cidade);

  public abstract Cidade toDomain(CidadeModel cidadeModel);

  public abstract List<Cidade> toDomain(List<CidadeModel> cidadeModels);
}
