package br.com.gocharge.mappers;

import br.com.gocharge.domain.Veiculo;
import br.com.gocharge.model.VeiculoModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public interface VeiculoMapper {
  VeiculoMapper INSTANCE = Mappers.getMapper(VeiculoMapper.class);

  VeiculoModel toModel(Veiculo veiculo);

  Veiculo toDomain(VeiculoModel veiculoModel);
}
