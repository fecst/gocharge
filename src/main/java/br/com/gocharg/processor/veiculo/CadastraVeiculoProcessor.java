package br.com.gocharg.processor.veiculo;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Usuario;
import br.com.gocharg.domain.Veiculo;
import br.com.gocharg.dto.VeiculoDTO;
import br.com.gocharg.enums.StatusCadastroEnum;
import br.com.gocharg.mappers.VeiculoMapper;
import br.com.gocharg.processor.usuario.BuscaUsuarioPorIdProcessor;
import br.com.gocharg.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CadastraVeiculoProcessor implements CommandProcessor<Veiculo> {

    @Autowired
    private VeiculoRepository repository;
    @Autowired
    private BuscaUsuarioPorIdProcessor buscaUsuarioPorIdProcessor;

    @Override
    public Veiculo process(CommandContext context) {
        VeiculoDTO veiculo = context.getProperty("veiculo", VeiculoDTO.class);

        veiculo.setDataHoraCadastro(LocalDateTime.now().toString());
        veiculo.setStatus(StatusCadastroEnum.ATIVO.getCodigo());

        context.put("idUsuario", UUID.fromString(veiculo.getUsuario()));

        Usuario usuario = buscaUsuarioPorIdProcessor.process(context);

        return repository.create(VeiculoMapper.INSTANCE.toDomain(veiculo, usuario));
    }
}
