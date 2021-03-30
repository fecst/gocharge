package br.com.gocharge.processor.cidade;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.command.CommandProcessor;
import br.com.gocharge.domain.Bandeira;
import br.com.gocharge.domain.Cidade;
import br.com.gocharge.domain.Estado;
import br.com.gocharge.dto.CidadeDTO;
import br.com.gocharge.enums.StatusCadastroEnum;
import br.com.gocharge.mappers.CidadeMapper;
import br.com.gocharge.processor.estado.BuscaEstadoPorIdProcessor;
import br.com.gocharge.repository.BandeiraRepository;
import br.com.gocharge.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CadastraCidadeProcessor implements CommandProcessor<Cidade> {

  @Autowired private CidadeRepository cidadeRepository;

  @Autowired private BuscaEstadoPorIdProcessor buscaEstadoPorIdProcessor;

  @Override
  public Cidade process(CommandContext context) {
    CidadeDTO cidade = context.getProperty("cidade", CidadeDTO.class);

    context.put("idEstado", cidade.getEstado());

    Estado estado = buscaEstadoPorIdProcessor.process(context);

    return cidadeRepository.create(CidadeMapper.INSTANCE.toDomain(cidade, estado));
  }
}
