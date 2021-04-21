package br.com.gocharge.processor.veiculo;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Veiculo;
import br.com.gocharge.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BuscaVeiculoPorIdProcessor implements CommandProcessor<Veiculo> {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Override
    public Veiculo process(CommandContext context) {
        UUID idVeiculo = context.getProperty("idVeiculo", UUID.class);

        return veiculoRepository.getById(idVeiculo);
    }
}
