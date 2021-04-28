package br.com.gocharg.processor.valor;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Bandeira;
import br.com.gocharg.domain.Valor;
import br.com.gocharg.dto.ValorDTO;
import br.com.gocharg.mappers.ValorMapper;
import br.com.gocharg.processor.bandeira.BuscaBandeiraPorIdProcessor;
import br.com.gocharg.repository.ValorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AlteraValorProcessor implements CommandProcessor<Valor> {

    @Autowired
    private ValorRepository valorRepository;

    @Autowired
    private BuscaBandeiraPorIdProcessor buscaBandeiraPorIdProcessor;

    @Override
    public Valor process(CommandContext context) {
        ValorDTO valor = context.getProperty("valor", ValorDTO.class);

        context.put("idBandeira", UUID.fromString(valor.getBandeira()));

        Bandeira bandeira = buscaBandeiraPorIdProcessor.process(context);

        return valorRepository.update(ValorMapper.INSTANCE.toDomain(valor, bandeira));
    }
}
