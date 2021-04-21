package br.com.gocharge.processor.zonas;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Cidade;
import br.com.gocharge.domain.Estado;
import br.com.gocharge.domain.Zona;
import br.com.gocharge.dto.ZonaDTO;
import br.com.gocharge.mappers.ZonaMapper;
import br.com.gocharge.processor.cidade.BuscaCidadePorIdProcessor;
import br.com.gocharge.processor.estado.BuscaEstadoPorIdProcessor;
import br.com.gocharge.repository.ZonaRepository;
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
