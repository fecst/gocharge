package br.com.gocharg.mappers;

import br.com.gocharg.domain.Cidade;
import br.com.gocharg.domain.Estado;
import br.com.gocharg.domain.Transacao;
import br.com.gocharg.dto.CidadeDTO;
import br.com.gocharg.model.CidadeModel;
import br.com.gocharg.model.TransacaoModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = TotemMapper.class)
public abstract class TransacaoMapper {
  public static final TransacaoMapper INSTANCE = Mappers.getMapper(TransacaoMapper.class);

  public abstract TransacaoModel toModel(Transacao transacao);

  public abstract Transacao toDomain(TransacaoModel transacaoModel);

  public abstract List<TransacaoModel> toModel(List<Transacao> transacao);

  public abstract List<Transacao> toDomain(List<TransacaoModel> transacaoModel);
}
