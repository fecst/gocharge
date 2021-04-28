package br.com.gocharg.processor.subZonas;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.SubZona;
import br.com.gocharg.repository.SubZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BuscaSubZonaPorIdProcessor implements CommandProcessor<SubZona> {

    @Autowired
    private SubZonaRepository subZonaRepository;

    @Override
    public SubZona process(CommandContext context) {
        UUID idSubZona = context.getProperty("idSubZona", UUID.class);

        return subZonaRepository.getById(idSubZona);
    }
}
