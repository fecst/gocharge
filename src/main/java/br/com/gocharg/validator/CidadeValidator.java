package br.com.gocharg.validator;

import br.com.fluentvalidator.AbstractValidator;
import br.com.gocharg.dto.CidadeDTO;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

@Component
public class CidadeValidator extends AbstractValidator<CidadeDTO> {
  @Override
  public void rules() {
    ruleFor(cidadeDTO -> cidadeDTO)
        .must(not(nullValue(CidadeDTO::getDescricao)))
        .withMessage("Cidade não pode ser vazia")
        .withFieldName("cidade")

        .must(not(nullValue(CidadeDTO::getEstado)))
        .withMessage("Estado não pode ser nulo")
        .withFieldName("estado");
  }
}
