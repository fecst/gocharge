package br.com.gocharge.validator;

import br.com.fluentvalidator.AbstractValidator;
import br.com.gocharge.dto.SubZonaDTO;
import br.com.gocharge.dto.ZonaDTO;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

@Component
public class SubZonaValidator extends AbstractValidator<SubZonaDTO> {
  @Override
  public void rules() {
    ruleFor(subZonaDTO -> subZonaDTO)
        .must(not(nullValue(SubZonaDTO::getDescricao)))
        .withMessage("Sub-Zona não pode ser vazia")
        .withFieldName("sub_zona")
        .withAttempedValue(SubZonaDTO::getDescricao)

        .must(not(nullValue(SubZonaDTO::getCidade)))
        .withMessage("Cidade não pode ser nula")
        .withFieldName("cidade")
        .withAttempedValue(SubZonaDTO::getCidade)

        .must(not(nullValue(SubZonaDTO::getEstado)))
        .withMessage("Estado não pode ser nulo")
        .withFieldName("estado")
        .withAttempedValue(SubZonaDTO::getEstado)

        .must(not(nullValue(SubZonaDTO::getZona)))
        .withMessage("Zona não pode ser nula")
        .withFieldName("zona")
        .withAttempedValue(SubZonaDTO::getZona);
  }
}
