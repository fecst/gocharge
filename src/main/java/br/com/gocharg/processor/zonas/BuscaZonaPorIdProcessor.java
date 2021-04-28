package br.com.gocharg.processor.zonas;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Zona;
import br.com.gocharg.repository.ZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BuscaZonaPorIdProcessor implements CommandProcessor<Zona> {

    @Autowired
    private ZonaRepository zonaRepository;

    @Override
    public Zona process(CommandContext context) {
        UUID idZona = context.getProperty("idZona", UUID.class);

        return zonaRepository.getById(idZona);
    }
}
