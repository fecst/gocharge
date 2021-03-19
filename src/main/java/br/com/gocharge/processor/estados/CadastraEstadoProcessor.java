package br.com.gocharge.processor.estados;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Estado;
import br.com.gocharge.mappers.EstadoMapper;
import br.com.gocharge.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastraEstadoProcessor implements CommandProcessor<Estado> {

    @Autowired
    EstadoRepository estadoRepository;

    @Override
    public Estado process(CommandContext context) {
        Estado estado = context.getProperty("estado", Estado.class);

        return estadoRepository.create(estado);
    }

}
