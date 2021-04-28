package br.com.gocharg.processor.estado;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Estado;
import br.com.gocharg.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuscaEstadoPorIdProcessor implements CommandProcessor<Estado> {

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public Estado process(CommandContext context) {
        String idEstado = context.getProperty("idEstado", String.class);

        return estadoRepository.getById(idEstado);
    }
}
