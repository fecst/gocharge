package br.com.gocharg.processor.valor;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Valor;
import br.com.gocharg.repository.ValorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BuscaValorPorIdProcessor implements CommandProcessor<Valor> {

    @Autowired
    private ValorRepository valorRepository;

    @Override
    public Valor process(CommandContext context) {
        UUID idValor = context.getProperty("idValor", UUID.class);

        return valorRepository.getById(idValor);
    }
}
