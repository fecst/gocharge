package br.com.gocharg.processor.subZonas;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.SubZona;
import br.com.gocharg.repository.SubZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaSubZonasProcessor implements CommandProcessor<List<SubZona>> {

    @Autowired
    private SubZonaRepository subZonaRepository;

    @Override
    public List<SubZona> process(CommandContext commandContext) {
        return subZonaRepository.getAll();
    }
}
