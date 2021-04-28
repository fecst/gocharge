package br.com.gocharg.processor.estado;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Estado;
import br.com.gocharg.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaEstadosProcessor implements CommandProcessor<List<Estado>> {

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public List<Estado> process(CommandContext commandContext) {
        return estadoRepository.getAll();
    }
}
