package br.com.gocharg.processor.veiculo;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Usuario;
import br.com.gocharg.domain.Veiculo;
import br.com.gocharg.dto.VeiculoDTO;
import br.com.gocharg.mappers.VeiculoMapper;
import br.com.gocharg.processor.usuario.BuscaUsuarioPorIdProcessor;
import br.com.gocharg.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AlteraVeiculoProcessor implements CommandProcessor<Veiculo> {

    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private BuscaUsuarioPorIdProcessor buscaUsuarioPorIdProcessor;
    @Autowired
    private BuscaVeiculoPorIdProcessor buscaVeiculoPorIdProcessor;

    @Override
    public Veiculo process(CommandContext context) {
        VeiculoDTO veiculo = context.getProperty("veiculo", VeiculoDTO.class);

        context.put("idUsuario", UUID.fromString(veiculo.getUsuario()));
        context.put("idVeiculo", UUID.fromString(veiculo.getId()));

        Usuario usuario = buscaUsuarioPorIdProcessor.process(context);

        veiculoRepository.update(VeiculoMapper.INSTANCE.toDomain(veiculo, usuario));

        return buscaVeiculoPorIdProcessor.process(context);
    }
}
