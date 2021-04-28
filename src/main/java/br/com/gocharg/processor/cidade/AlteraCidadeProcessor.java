package br.com.gocharg.processor.cidade;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Cidade;
import br.com.gocharg.domain.Estado;
import br.com.gocharg.dto.CidadeDTO;
import br.com.gocharg.mappers.CidadeMapper;
import br.com.gocharg.processor.estado.BuscaEstadoPorIdProcessor;
import br.com.gocharg.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlteraCidadeProcessor implements CommandProcessor<Cidade> {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private BuscaEstadoPorIdProcessor buscaEstadoPorIdProcessor;

    @Override
    public Cidade process(CommandContext context) {
        CidadeDTO cidade = context.getProperty("cidade", CidadeDTO.class);

        context.put("idEstado", cidade.getEstado());

        Estado estado = buscaEstadoPorIdProcessor.process(context);

        return cidadeRepository.update(CidadeMapper.INSTANCE.toDomain(cidade, estado));
    }
}
