package br.com.gocharg.processor.zonas;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Cidade;
import br.com.gocharg.domain.Estado;
import br.com.gocharg.domain.Zona;
import br.com.gocharg.dto.ZonaDTO;
import br.com.gocharg.mappers.ZonaMapper;
import br.com.gocharg.processor.cidade.BuscaCidadePorIdProcessor;
import br.com.gocharg.processor.estado.BuscaEstadoPorIdProcessor;
import br.com.gocharg.repository.ZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlteraZonaProcessor implements CommandProcessor<Zona> {

    @Autowired
    private ZonaRepository zonaRepository;
    @Autowired
    private BuscaEstadoPorIdProcessor buscaEstadoPorIdProcessor;
    @Autowired
    private BuscaCidadePorIdProcessor buscaCidadePorIdProcessor;

    @Override
    public Zona process(CommandContext context) {
        ZonaDTO zona = context.getProperty("zona", ZonaDTO.class);

        context.put("idEstado", zona.getEstado());
        context.put("idCidade", Integer.valueOf(zona.getCidade()));

        Estado estado = buscaEstadoPorIdProcessor.process(context);
        Cidade cidade = buscaCidadePorIdProcessor.process(context);

        return zonaRepository.update(ZonaMapper.INSTANCE.toDomain(zona, cidade, estado));
    }
}
