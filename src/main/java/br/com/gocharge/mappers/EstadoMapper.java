package br.com.gocharge.mappers;

import br.com.gocharge.domain.Estado;
import br.com.gocharge.model.EstadoModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface EstadoMapper {

  EstadoMapper INSTANCE = Mappers.getMapper(EstadoMapper.class);

  EstadoModel toModel(Estado estado);

  Estado toDomain(EstadoModel estadoModel);

  List<Estado> toDomain(List<EstadoModel> estadoModelList);
}
