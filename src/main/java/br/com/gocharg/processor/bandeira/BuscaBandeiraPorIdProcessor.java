package br.com.gocharg.processor.bandeira;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Bandeira;
import br.com.gocharg.repository.BandeiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BuscaBandeiraPorIdProcessor implements CommandProcessor<Bandeira> {

    @Autowired
    private BandeiraRepository bandeiraRepository;

    @Override
    public Bandeira process(CommandContext context) {
        UUID idBandeira = context.getProperty("idBandeira", UUID.class);

        return bandeiraRepository.getById(idBandeira);
    }
}
