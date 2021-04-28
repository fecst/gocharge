package br.com.gocharg.processor.veiculo;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Veiculo;
import br.com.gocharg.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaVeiculosProcessor implements CommandProcessor<List<Veiculo>> {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Override
    public List<Veiculo> process(CommandContext commandContext) {
        return veiculoRepository.getAll();
    }
}
