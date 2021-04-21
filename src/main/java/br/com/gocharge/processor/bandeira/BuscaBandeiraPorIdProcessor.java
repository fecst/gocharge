package br.com.gocharge.processor.bandeira;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Bandeira;
import br.com.gocharge.repository.BandeiraRepository;
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
