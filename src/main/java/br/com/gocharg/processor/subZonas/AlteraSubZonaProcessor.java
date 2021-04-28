package br.com.gocharg.processor.subZonas;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Cidade;
import br.com.gocharg.domain.Estado;
import br.com.gocharg.domain.SubZona;
import br.com.gocharg.domain.Zona;
import br.com.gocharg.dto.SubZonaDTO;
import br.com.gocharg.mappers.SubZonaMapper;
import br.com.gocharg.processor.cidade.BuscaCidadePorIdProcessor;
import br.com.gocharg.processor.estado.BuscaEstadoPorIdProcessor;
import br.com.gocharg.processor.zonas.BuscaZonaPorIdProcessor;
import br.com.gocharg.repository.SubZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AlteraSubZonaProcessor implements CommandProcessor<SubZona> {

    @Autowired
    private SubZonaRepository subZonaRepository;
    @Autowired
    private BuscaEstadoPorIdProcessor buscaEstadoPorIdProcessor;
    @Autowired
    private BuscaCidadePorIdProcessor buscaCidadePorIdProcessor;
    @Autowired
    private BuscaZonaPorIdProcessor buscaZonaPorIdProcessor;

    @Override
    public SubZona process(CommandContext context) {
        SubZonaDTO subZona = context.getProperty("subZona", SubZonaDTO.class);

        context.put("idEstado", subZona.getEstado());
        context.put("idCidade", Integer.valueOf(subZona.getCidade()));
        context.put("idZona", UUID.fromString(subZona.getZona()));

        Estado estado = buscaEstadoPorIdProcessor.process(context);
        Cidade cidade = buscaCidadePorIdProcessor.process(context);
        Zona zona = buscaZonaPorIdProcessor.process(context);

        return subZonaRepository.update(SubZonaMapper.INSTANCE.toDomain(subZona, zona, cidade, estado));
    }
}
