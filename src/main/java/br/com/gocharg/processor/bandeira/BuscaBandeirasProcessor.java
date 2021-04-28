package br.com.gocharg.processor.bandeira;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Bandeira;
import br.com.gocharg.repository.BandeiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaBandeirasProcessor implements CommandProcessor<List<Bandeira>> {

    @Autowired
    private BandeiraRepository bandeiraRepository;

    @Override
    public List<Bandeira> process(CommandContext commandContext) {
        return bandeiraRepository.getAll();
    }
}
