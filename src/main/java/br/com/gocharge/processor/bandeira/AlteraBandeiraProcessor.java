package br.com.gocharge.processor.bandeira;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Bandeira;
import br.com.gocharge.dto.BandeiraDTO;
import br.com.gocharge.mappers.BandeiraMapper;
import br.com.gocharge.repository.BandeiraRepository;
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
