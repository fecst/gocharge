package br.com.gocharge.processor.cidade;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Cidade;
import br.com.gocharge.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuscaCidadePorIdProcessor implements CommandProcessor<Cidade> {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Override
    public Cidade process(CommandContext context) {
        Integer id = context.getProperty("idCidade", Integer.class);

        return cidadeRepository.getById(id);
    }
}
