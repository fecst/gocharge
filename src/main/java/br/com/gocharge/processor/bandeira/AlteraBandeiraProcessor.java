package br.com.gocharge.processor.bandeira;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Bandeira;
import br.com.gocharge.domain.Estado;
import br.com.gocharge.repository.BandeiraRepository;
import br.com.gocharge.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlteraBandeiraProcessor implements CommandProcessor<Bandeira> {

    @Autowired private BandeiraRepository bandeiraRepository;

    @Override
    public Bandeira process(CommandContext context) {
        Bandeira bandeira = context.getProperty("bandeira", Bandeira.class);

        return bandeiraRepository.update(bandeira);
    }
}
