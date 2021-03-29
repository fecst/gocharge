package br.com.gocharge.mappers;

import br.com.gocharge.domain.Estado;
import br.com.gocharge.dto.EstadoDTO;
import br.com.gocharge.model.EstadoModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class EstadoMapper {
  public static final EstadoMapper INSTANCE = Mappers.getMapper(EstadoMapper.class);

  public abstract EstadoModel toModel(Estado estado);

  public abstract Estado toDomain(EstadoModel estadoModel);

  public abstract Estado toDomain(EstadoDTO estadoModel);

  public abstract EstadoDTO toDTO(Estado estado);

  public abstract List<Estado> toDomain(List<EstadoModel> estadoModelList);

  public abstract List<EstadoDTO> toDTO(List<Estado> estado);
}
