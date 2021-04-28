package br.com.gocharg.processor.zonas;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Zona;
import br.com.gocharg.repository.ZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaZonaPorCidadeProcessor implements CommandProcessor<List<Zona>> {

    @Autowired
    private ZonaRepository zonaRepository;

    @Override
    public List<Zona> process(CommandContext context) {
        Integer idCidade = context.getProperty("idCidade", Integer.class);

        return zonaRepository.getByCidade(idCidade);
    }
}
