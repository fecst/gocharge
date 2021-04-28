package br.com.gocharg.processor.subZonas;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.SubZona;
import br.com.gocharg.repository.SubZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class BuscaSubZonaPorZonaProcessor implements CommandProcessor<List<SubZona>> {

    @Autowired
    private SubZonaRepository subZonaRepository;

    @Override
    public List<SubZona> process(CommandContext context) {
        UUID idZona = context.getProperty("idZona", UUID.class);

        return subZonaRepository.getByZona(idZona);
    }
}
