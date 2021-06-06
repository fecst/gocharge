package br.com.gocharg.processor.totem;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.command.CommandProcessor;
import br.com.gocharg.domain.*;
import br.com.gocharg.dto.TotemDTO;
import br.com.gocharg.enums.StatusTotemEnum;
import br.com.gocharg.mappers.TotemMapper;
import br.com.gocharg.processor.cidade.BuscaCidadePorIdProcessor;
import br.com.gocharg.processor.estado.BuscaEstadoPorIdProcessor;
import br.com.gocharg.processor.fabricante.BuscaFabricantePorIdProcessor;
import br.com.gocharg.processor.subZonas.BuscaSubZonaPorIdProcessor;
import br.com.gocharg.processor.usuario.BuscaUsuarioPorIdProcessor;
import br.com.gocharg.processor.valor.BuscaValorPorIdProcessor;
import br.com.gocharg.processor.zonas.BuscaZonaPorIdProcessor;
import br.com.gocharg.repository.TotemRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CadastraTotemProcessor implements CommandProcessor<Totem> {

  @Autowired private TotemRepository repository;
  @Autowired private BuscaEstadoPorIdProcessor buscaEstadoPorIdProcessor;
  @Autowired private BuscaCidadePorIdProcessor buscaCidadePorIdProcessor;
  @Autowired private BuscaZonaPorIdProcessor buscaZonaPorIdProcessor;
  @Autowired private BuscaSubZonaPorIdProcessor buscaSubZonaPorIdProcessor;
  @Autowired private BuscaValorPorIdProcessor buscaValorPorIdProcessor;
  @Autowired private BuscaFabricantePorIdProcessor buscaFabricantePorIdProcessor;
  @Autowired private BuscaUsuarioPorIdProcessor buscaUsuarioPorIdProcessor;

  @Override
  public Totem process(CommandContext context) {
    TotemDTO totem = context.getProperty("totem", TotemDTO.class);

    totem.setDataHoraCadastro(LocalDateTime.now().toString());
    totem.setStatus(StatusTotemEnum.DESLIGADO.getCodigo().toString());

    context.put("idEstado", totem.getEstado());
    context.put("idCidade", Integer.valueOf(totem.getCidade()));
    context.put("idZona", UUID.fromString(totem.getZona()));
    context.put("idSubZona", UUID.fromString(totem.getSubZona()));
    context.put("idValor", UUID.fromString(totem.getValor()));
    context.put("idFabricante", UUID.fromString(totem.getFabricante()));
    context.put("idTotem", totem.getId());

    Estado estado = buscaEstadoPorIdProcessor.process(context);
    Cidade cidade = buscaCidadePorIdProcessor.process(context);
    Zona zona = buscaZonaPorIdProcessor.process(context);
    SubZona subZona = buscaSubZonaPorIdProcessor.process(context);
    Valor valor = buscaValorPorIdProcessor.process(context);
    Fabricante fabricante = buscaFabricantePorIdProcessor.process(context);
    Usuario proprietario = null;

    if (StringUtils.isNotEmpty(totem.getProprietario())) {
      context.put("idUsuario", UUID.fromString(totem.getProprietario()));
      proprietario = buscaUsuarioPorIdProcessor.process(context);
    }

    return repository.create(
        TotemMapper.INSTANCE.toDomain(
            totem, estado, cidade, zona, subZona, valor, fabricante, proprietario));
  }
}
