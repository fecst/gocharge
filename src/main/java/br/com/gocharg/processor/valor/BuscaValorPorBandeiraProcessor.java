package br.com.gocharg.processor.valor;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Valor;
import br.com.gocharg.repository.ValorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class BuscaValorPorBandeiraProcessor implements CommandProcessor<List<Valor>> {

    @Autowired
    private ValorRepository valorRepository;

    @Override
    public List<Valor> process(CommandContext context) {
        UUID idBandeira = context.getProperty("idBandeira", UUID.class);

        return valorRepository.getByBandeira(idBandeira);
    }
}
