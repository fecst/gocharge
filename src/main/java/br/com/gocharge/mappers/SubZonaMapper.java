package br.com.gocharge.mappers;

import br.com.gocharge.domain.Cidade;
import br.com.gocharge.domain.Estado;
import br.com.gocharge.domain.SubZona;
import br.com.gocharge.domain.Zona;
import br.com.gocharge.dto.SubZonaDTO;
import br.com.gocharge.model.SubZonaModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ConverterMapper.class, EstadoMapper.class, CidadeMapper.class, ZonaMapper.class})
public abstract class SubZonaMapper {
    public static final SubZonaMapper INSTANCE = Mappers.getMapper(SubZonaMapper.class);

    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "cidade", ignore = true)
    @Mapping(target = "zona", ignore = true)
    public abstract SubZonaModel toModel(SubZona subZona);

    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "cidade", ignore = true)
    @Mapping(target = "zona", ignore = true)
    public abstract SubZona toDomain(SubZonaModel estadoModel);

    @Mapping(
            target = "id",
            source = "subZonaDTO.id",
            qualifiedByName = ConverterMapper.STRING_TO_UUID)
    @Mapping(target = "cidade", source = "cidade")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "zona", source = "zona")
    @Mapping(target = "descricao", source = "subZonaDTO.descricao")
    public abstract SubZona toDomain(
            SubZonaDTO subZonaDTO, Zona zona, Cidade cidade, Estado estado);

    @Mapping(target = "id", source = "id", qualifiedByName = ConverterMapper.UUID_TO_STRING)
    @Mapping(target = "cidade", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "zona", ignore = true)
    public abstract SubZonaDTO toDTO(SubZona subZona);

    public abstract List<SubZona> toDomain(List<SubZonaModel> subZonasModel);

    public abstract List<SubZonaDTO> toDTO(List<SubZona> subZonas);

    @Mapping(target = "id", ignore = true)
    public abstract void updateFrom(
            final SubZona subZona, @MappingTarget final SubZonaModel subZonaModel);

    @AfterMapping
    void afterMapping(SubZona subZona, @MappingTarget SubZonaDTO subZonaDTO) {
        subZonaDTO.setCidade(subZona.getCidade().getId().toString());
        subZonaDTO.setEstado(subZona.getEstado().getId());
        subZonaDTO.setZona(subZona.getZona().getId().toString());
    }

    @AfterMapping
    void afterMapping(SubZona subZona, @MappingTarget SubZonaModel subZonaModel) {
        subZonaModel.setCidade(CidadeMapper.INSTANCE.toModel(subZona.getCidade()));
        subZonaModel.setEstado(EstadoMapper.INSTANCE.toModel(subZona.getEstado()));
        subZonaModel.setZona(ZonaMapper.INSTANCE.toModel(subZona.getZona()));
    }

    @AfterMapping
    void afterMapping(SubZonaModel subZonaModel, @MappingTarget SubZona subZona) {
        subZona.setCidade(CidadeMapper.INSTANCE.toDomain(subZonaModel.getCidade()));
        subZona.setEstado(EstadoMapper.INSTANCE.toDomain(subZonaModel.getEstado()));
        subZona.setZona(ZonaMapper.INSTANCE.toDomain(subZonaModel.getZona()));
    }
}
