package br.com.gocharge.processor.subZonas;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Cidade;
import br.com.gocharge.domain.Estado;
import br.com.gocharge.domain.SubZona;
import br.com.gocharge.domain.Zona;
import br.com.gocharge.dto.SubZonaDTO;
import br.com.gocharge.mappers.SubZonaMapper;
import br.com.gocharge.processor.cidade.BuscaCidadePorIdProcessor;
import br.com.gocharge.processor.estado.BuscaEstadoPorIdProcessor;
import br.com.gocharge.processor.zonas.BuscaZonaPorIdProcessor;
import br.com.gocharge.repository.SubZonaRepository;
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
