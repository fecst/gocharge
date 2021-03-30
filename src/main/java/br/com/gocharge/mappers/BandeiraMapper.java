package br.com.gocharge.mappers;

import br.com.gocharge.domain.Bandeira;
import br.com.gocharge.dto.BandeiraDTO;
import br.com.gocharge.enums.StatusCadastroEnum;
import br.com.gocharge.model.BandeiraModel;
import br.com.gocharge.model.StatusCadastroModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ConverterMapper.class})
public abstract class BandeiraMapper {
  public static final BandeiraMapper INSTANCE = Mappers.getMapper(BandeiraMapper.class);

  @Mapping(target = "status", ignore = true)
  public abstract BandeiraModel toModel(Bandeira bandeira);

  @Mapping(target = "status", ignore = true)
  public abstract Bandeira toDomain(BandeiraModel bandeiraModel);

  public abstract List<Bandeira> toDomain(List<BandeiraModel> estadoModelList);

  @Mapping(target = "status", ignore = true)
  @Mapping(
      target = "dataHoraCadastro",
      source = "dataHoraCadastro",
      qualifiedByName = ConverterMapper.STRING_TO_LOCAL_DATE_TIME)
  @Mapping(target = "id", source = "id", qualifiedByName = ConverterMapper.STRING_TO_UUID)
  public abstract Bandeira toDomain(BandeiraDTO bandeiraDTO);

  @Mapping(target = "status", ignore = true)
  @Mapping(
      target = "dataHoraCadastro",
      source = "dataHoraCadastro",
      qualifiedByName = ConverterMapper.LOCAL_DATE_TIME_TO_STRING)
  @Mapping(target = "id", source = "id", qualifiedByName = ConverterMapper.UUID_TO_STRING)
  public abstract BandeiraDTO toDTO(Bandeira bandeira);

  public abstract List<BandeiraDTO> toDTO(List<Bandeira> bandeiras);

  @AfterMapping
  void afterMapping(Bandeira bandeira, @MappingTarget BandeiraDTO bandeiraDTO) {
    bandeiraDTO.setStatus(bandeira.getStatus().getCodigo());
  }

  @AfterMapping
  void afterMapping(BandeiraDTO bandeiraDTO, @MappingTarget Bandeira bandeira) {
    bandeira.setStatus(StatusCadastroEnum.get(bandeiraDTO.getStatus()));
  }

  @AfterMapping
  void afterMapping(Bandeira bandeira, @MappingTarget BandeiraModel bandeiraModel) {
    StatusCadastroModel statusCadastroModel = new StatusCadastroModel();
    statusCadastroModel.setId(bandeira.getStatus().getCodigo());

    bandeiraModel.setStatus(statusCadastroModel);
  }

  @AfterMapping
  void afterMapping(BandeiraModel bandeiraModel, @MappingTarget Bandeira bandeira) {
    bandeira.setStatus(StatusCadastroEnum.get(bandeiraModel.getStatus().getId()));
  }
}
