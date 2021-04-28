package br.com.gocharg.processor.estado;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Estado;
import br.com.gocharg.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlteraEstadoProcessor implements CommandProcessor<Estado> {

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public Estado process(CommandContext context) {
        Estado estado = context.getProperty("estado", Estado.class);

        return estadoRepository.update(estado);
    }
}
