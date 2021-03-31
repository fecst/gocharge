package br.com.gocharge.validator;

import br.com.fluentvalidator.AbstractValidator;
import br.com.gocharge.dto.ZonaDTO;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

@Component
public class ZonaValidator extends AbstractValidator<ZonaDTO> {
  @Override
  public void rules() {
    ruleFor(zonaDTO -> zonaDTO)
        .must(not(nullValue(ZonaDTO::getDescricao)))
        .withMessage("Zona não pode ser vazia")
        .withFieldName("zona")
        .withAttempedValue(ZonaDTO::getDescricao)

        .must(not(nullValue(ZonaDTO::getCidade)))
        .withMessage("Cidade não pode ser nulo")
        .withFieldName("cidade")
        .withAttempedValue(ZonaDTO::getCidade)

        .must(not(nullValue(ZonaDTO::getEstado)))
        .withMessage("Estado não pode ser nulo")
        .withFieldName("estado")
        .withAttempedValue(ZonaDTO::getEstado);
  }
}
