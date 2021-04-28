package br.com.gocharg.mappers;

import br.com.gocharg.domain.Fabricante;
import br.com.gocharg.dto.FabricanteDTO;
import br.com.gocharg.enums.StatusCadastroEnum;
import br.com.gocharg.model.FabricanteModel;
import br.com.gocharg.model.StatusCadastroModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ConverterMapper.class})
public abstract class FabricanteMapper {
  public static final FabricanteMapper INSTANCE = Mappers.getMapper(FabricanteMapper.class);

  @Mapping(target = "status", ignore = true)
  public abstract FabricanteModel toModel(Fabricante fabricante);

  @Mapping(target = "status", ignore = true)
  public abstract Fabricante toDomain(FabricanteModel fabricanteModel);

  public abstract List<Fabricante> toDomain(List<FabricanteModel> fabricanteModels);

  @Mapping(target = "status", ignore = true)
  @Mapping(
      target = "dataHoraCadastro",
      source = "dataHoraCadastro",
      qualifiedByName = ConverterMapper.STRING_TO_LOCAL_DATE_TIME)
  @Mapping(target = "id", source = "id", qualifiedByName = ConverterMapper.STRING_TO_UUID)
  public abstract Fabricante toDomain(FabricanteDTO fabricanteDTO);

  @Mapping(target = "status", ignore = true)
  @Mapping(
      target = "dataHoraCadastro",
      source = "dataHoraCadastro",
      qualifiedByName = ConverterMapper.LOCAL_DATE_TIME_TO_STRING)
  @Mapping(target = "id", source = "id", qualifiedByName = ConverterMapper.UUID_TO_STRING)
  public abstract FabricanteDTO toDTO(Fabricante fabricante);

  public abstract List<FabricanteDTO> toDTO(List<Fabricante> fabricantes);

  @AfterMapping
  void afterMapping(Fabricante fabricante, @MappingTarget FabricanteDTO fabricanteDTO) {
    fabricanteDTO.setStatus(fabricante.getStatus().getCodigo());
  }

  @AfterMapping
  void afterMapping(FabricanteDTO fabricanteDTO, @MappingTarget Fabricante fabricante) {
    fabricante.setStatus(StatusCadastroEnum.get(fabricanteDTO.getStatus()));
  }

  @AfterMapping
  void afterMapping(Fabricante fabricante, @MappingTarget FabricanteModel fabricanteModel) {
    StatusCadastroModel statusCadastroModel = new StatusCadastroModel();
    statusCadastroModel.setId(fabricante.getStatus().getCodigo());

    fabricanteModel.setStatus(statusCadastroModel);
  }

  @AfterMapping
  void afterMapping(FabricanteModel fabricanteModel, @MappingTarget Fabricante fabricante) {
    fabricante.setStatus(StatusCadastroEnum.get(fabricanteModel.getStatus().getId()));
  }
}
