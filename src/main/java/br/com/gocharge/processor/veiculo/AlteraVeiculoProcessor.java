package br.com.gocharge.processor.veiculo;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Usuario;
import br.com.gocharge.domain.Veiculo;
import br.com.gocharge.dto.VeiculoDTO;
import br.com.gocharge.mappers.VeiculoMapper;
import br.com.gocharge.processor.usuario.BuscaUsuarioPorIdProcessor;
import br.com.gocharge.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AlteraVeiculoProcessor implements CommandProcessor<Veiculo> {

    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private BuscaUsuarioPorIdProcessor buscaUsuarioPorIdProcessor;

    @Override
    public Veiculo process(CommandContext context) {
        VeiculoDTO veiculo = context.getProperty("veiculo", VeiculoDTO.class);

        context.put("idUsuario", UUID.fromString(veiculo.getUsuario()));

        Usuario usuario = buscaUsuarioPorIdProcessor.process(context);

        return veiculoRepository.update(VeiculoMapper.INSTANCE.toDomain(veiculo, usuario));
    }
}
