package br.com.gocharg.processor.cidade;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Cidade;
import br.com.gocharg.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApagaCidadePorIdProcessor implements CommandProcessor<Cidade> {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Override
    public Cidade process(CommandContext context) {
        Integer idCidade = context.getProperty("idCidade", Integer.class);

        cidadeRepository.delete(idCidade);

        return null;
    }
}
