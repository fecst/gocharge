package br.com.gocharg.processor.bandeira;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.Bandeira;
import br.com.gocharg.dto.BandeiraDTO;
import br.com.gocharg.mappers.BandeiraMapper;
import br.com.gocharg.repository.BandeiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlteraBandeiraProcessor implements CommandProcessor<Bandeira> {

    @Autowired
    private BandeiraRepository bandeiraRepository;

    @Override
    public Bandeira process(CommandContext context) {
        BandeiraDTO bandeira = context.getProperty("bandeira", BandeiraDTO.class);

        return bandeiraRepository.update(BandeiraMapper.INSTANCE.toDomain(bandeira));
    }
}
