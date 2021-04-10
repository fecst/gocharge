package br.com.gocharge.mappers;

import br.com.gocharge.domain.Usuario;
import br.com.gocharge.domain.Veiculo;
import br.com.gocharge.dto.VeiculoDTO;
import br.com.gocharge.enums.StatusCadastroEnum;
import br.com.gocharge.model.StatusCadastroModel;
import br.com.gocharge.model.UsuarioModel;
import br.com.gocharge.model.VeiculoModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ConverterMapper.class})
public abstract class VeiculoMapper {
    public static final VeiculoMapper INSTANCE = Mappers.getMapper(VeiculoMapper.class);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    public abstract VeiculoModel toModel(Veiculo veiculo);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    public abstract Veiculo toDomain(VeiculoModel veiculoModel);

    public abstract List<Veiculo> toDomain(List<VeiculoModel> veiculoModels);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "usuario", source = "usuario")
    @Mapping(
            target = "dataHoraCadastro",
            source = "veiculoDTO.dataHoraCadastro",
            qualifiedByName = ConverterMapper.STRING_TO_LOCAL_DATE_TIME)
    @Mapping(target = "id", source = "veiculoDTO.id", qualifiedByName = ConverterMapper.STRING_TO_UUID)
    public abstract Veiculo toDomain(VeiculoDTO veiculoDTO, Usuario usuario);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(
            target = "dataHoraCadastro",
            source = "dataHoraCadastro",
            qualifiedByName = ConverterMapper.LOCAL_DATE_TIME_TO_STRING)
    @Mapping(target = "id", source = "id", qualifiedByName = ConverterMapper.UUID_TO_STRING)
    public abstract VeiculoDTO toDTO(Veiculo veiculo);

    public abstract List<VeiculoDTO> toDTO(List<Veiculo> veiculos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataHoraCadastro", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    public abstract void updateFrom(
            final Veiculo veiculo, @MappingTarget final VeiculoModel veiculoModel);

    @AfterMapping
    void afterMapping(Veiculo veiculo, @MappingTarget VeiculoDTO veiculoDTO) {
        veiculoDTO.setStatus(veiculo.getStatus().getCodigo());
        veiculoDTO.setUsuario(veiculo.getUsuario().getId().toString());
    }

    @AfterMapping
    void afterMapping(VeiculoDTO veiculoDTO, @MappingTarget Veiculo veiculo) {
        veiculo.setStatus(StatusCadastroEnum.get(veiculoDTO.getStatus()));
    }

    @AfterMapping
    void afterMapping(Veiculo veiculo, @MappingTarget VeiculoModel veiculoModel) {
        StatusCadastroModel statusCadastroModel = new StatusCadastroModel();
        statusCadastroModel.setId(veiculo.getStatus().getCodigo());

        veiculoModel.setStatus(statusCadastroModel);
        veiculoModel.setUsuario(UsuarioMapper.INSTANCE.toModel(veiculo.getUsuario()));
    }

    @AfterMapping
    void afterMapping(VeiculoModel veiculoModel, @MappingTarget Veiculo veiculo) {
        veiculo.setStatus(StatusCadastroEnum.get(veiculoModel.getStatus().getId()));
        veiculo.setUsuario(UsuarioMapper.INSTANCE.toDomain(veiculoModel.getUsuario()));
    }
}
