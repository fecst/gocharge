package br.com.gocharg.mappers;

import br.com.gocharg.domain.Cidade;
import br.com.gocharg.domain.Estado;
import br.com.gocharg.dto.CidadeDTO;
import br.com.gocharg.model.CidadeModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class CidadeMapper {
  public static final CidadeMapper INSTANCE = Mappers.getMapper(CidadeMapper.class);

  public abstract CidadeModel toModel(Cidade cidade);

  public abstract Cidade toDomain(CidadeModel cidadeModel);

  @Mapping(target = "estado", ignore = true)
  @Mapping(target = "id", source = "cidadeDTO.id")
  @Mapping(target = "descricao", source = "cidadeDTO.descricao")
  public abstract Cidade toDomain(CidadeDTO cidadeDTO, Estado estado);

  public abstract List<Cidade> toDomain(List<CidadeModel> cidadeModels);

  @Mapping(target = "estado", ignore = true)
  public abstract CidadeDTO toDTO(Cidade cidade);

  public abstract List<CidadeDTO> toDTO(List<Cidade> cidade);

  @AfterMapping
  void afterMapping(Cidade cidade, @MappingTarget CidadeDTO cidadeDTO) {
    cidadeDTO.setEstado(cidade.getEstado().getId());
  }

  @AfterMapping
  void afterMapping(Estado estado, @MappingTarget Cidade cidade) {
    cidade.setEstado(estado);
  }
}
