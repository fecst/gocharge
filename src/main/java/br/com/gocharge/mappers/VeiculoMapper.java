package br.com.gocharge.mappers;

import br.com.gocharge.domain.Veiculo;
import br.com.gocharge.model.VeiculoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class VeiculoMapper {
  public static final VeiculoMapper INSTANCE = Mappers.getMapper(VeiculoMapper.class);

  @Mapping(target = "status", ignore = true)
  public abstract VeiculoModel toModel(Veiculo veiculo);

  @Mapping(target = "status", ignore = true)
  public abstract Veiculo toDomain(VeiculoModel veiculoModel);
}
