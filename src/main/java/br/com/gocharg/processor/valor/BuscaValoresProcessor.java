package br.com.gocharg.processor.valor;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Valor;
import br.com.gocharg.repository.ValorRepository;
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
