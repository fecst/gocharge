package br.com.gocharge.processor.valor;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Bandeira;
import br.com.gocharge.domain.Valor;
import br.com.gocharge.dto.ValorDTO;
import br.com.gocharge.mappers.ValorMapper;
import br.com.gocharge.processor.bandeira.BuscaBandeiraPorIdProcessor;
import br.com.gocharge.repository.ValorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CadastraValorProcessor implements CommandProcessor<Valor> {

  @Autowired private ValorRepository valorRepository;

  @Autowired private BuscaBandeiraPorIdProcessor buscaBandeiraPorIdProcessor;

  @Override
  public Valor process(CommandContext context) {
    ValorDTO valor = context.getProperty("valor", ValorDTO.class);

    context.put("idBandeira", UUID.fromString(valor.getBandeira()));

    Bandeira bandeira = buscaBandeiraPorIdProcessor.process(context);

    valor.setDataHoraCadastro(LocalDateTime.now().toString());

    return valorRepository.create(ValorMapper.INSTANCE.toDomain(valor, bandeira));
  }
}
