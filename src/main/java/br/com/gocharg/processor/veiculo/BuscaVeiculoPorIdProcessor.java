package br.com.gocharg.processor.veiculo;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Veiculo;
import br.com.gocharg.repository.VeiculoRepository;
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
