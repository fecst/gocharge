package br.com.gocharge.processor.valor;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Valor;
import br.com.gocharge.repository.ValorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaValoresProcessor implements CommandProcessor<List<Valor>> {

    @Autowired
    private ValorRepository valorRepository;

    @Override
    public List<Valor> process(CommandContext commandContext) {
        return valorRepository.getAll();
    }
}
