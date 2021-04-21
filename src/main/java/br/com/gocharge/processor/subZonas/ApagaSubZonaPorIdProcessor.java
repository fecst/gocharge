package br.com.gocharge.processor.subZonas;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.SubZona;
import br.com.gocharge.repository.SubZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ApagaSubZonaPorIdProcessor implements CommandProcessor<SubZona> {

    @Autowired
    private SubZonaRepository subZonaRepository;

    @Override
    public SubZona process(CommandContext context) {
        UUID idSubZona = context.getProperty("idSubZona", UUID.class);

        subZonaRepository.delete(idSubZona);

        return null;
    }
}
