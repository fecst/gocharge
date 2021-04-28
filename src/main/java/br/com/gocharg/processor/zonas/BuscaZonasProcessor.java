package br.com.gocharg.processor.zonas;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Zona;
import br.com.gocharg.repository.ZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaZonasProcessor implements CommandProcessor<List<Zona>> {

    @Autowired
    private ZonaRepository zonaRepository;

    @Override
    public List<Zona> process(CommandContext commandContext) {
        return zonaRepository.getAll();
    }
}
