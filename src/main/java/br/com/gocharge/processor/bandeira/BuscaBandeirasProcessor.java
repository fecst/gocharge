package br.com.gocharge.processor.bandeira;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Bandeira;
import br.com.gocharge.repository.BandeiraRepository;
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
