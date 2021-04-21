package br.com.gocharge.mappers;

import br.com.gocharge.domain.Bandeira;
import br.com.gocharge.domain.Valor;
import br.com.gocharge.dto.ValorDTO;
import br.com.gocharge.model.ValorModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ConverterMapper.class})
public abstract class ValorMapper {
    public static final ValorMapper INSTANCE = Mappers.getMapper(ValorMapper.class);

    @Mapping(target = "bandeira", ignore = true)
    public abstract ValorModel toModel(Valor valor);

    @Mapping(target = "bandeira", ignore = true)
    public abstract Valor toDomain(ValorModel valorModel);

    @Mapping(target = "id", source = "valorDTO.id", qualifiedByName = ConverterMapper.STRING_TO_UUID)
    @Mapping(target = "bandeira", source = "bandeira")
    @Mapping(target = "descricao", source = "valorDTO.descricao")
    @Mapping(
            target = "dataHoraCadastro",
            source = "valorDTO.dataHoraCadastro",
            qualifiedByName = ConverterMapper.STRING_TO_LOCAL_DATE_TIME)
    public abstract Valor toDomain(ValorDTO valorDTO, Bandeira bandeira);

    @Mapping(target = "id", source = "id", qualifiedByName = ConverterMapper.UUID_TO_STRING)
    @Mapping(target = "bandeira", ignore = true)
    @Mapping(
            target = "dataHoraCadastro",
            source = "dataHoraCadastro",
            qualifiedByName = ConverterMapper.LOCAL_DATE_TIME_TO_STRING)
    public abstract ValorDTO toDTO(Valor valor);

    public abstract List<Valor> toDomain(List<ValorModel> valorModels);

    public abstract List<ValorDTO> toDTO(List<Valor> valors);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bandeira", ignore = true)
    public abstract void updateFrom(final Valor valor, @MappingTarget final ValorModel valorModel);

    @AfterMapping
    void afterMapping(Valor valor, @MappingTarget ValorDTO valorDTO) {
        valorDTO.setBandeira(valor.getBandeira().getId().toString());
    }

    @AfterMapping
    void afterMapping(Valor valor, @MappingTarget ValorModel valorModel) {
        valorModel.setBandeira(BandeiraMapper.INSTANCE.toModel(valor.getBandeira()));
    }

    @AfterMapping
    void afterMapping(ValorModel valorModel, @MappingTarget Valor valor) {
        valor.setBandeira(BandeiraMapper.INSTANCE.toDomain(valorModel.getBandeira()));
    }
}
