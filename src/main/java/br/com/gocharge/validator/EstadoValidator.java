package br.com.gocharge.validator;

import br.com.fluentvalidator.AbstractValidator;
import br.com.gocharge.dto.EstadoDTO;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

@Component
public class EstadoValidator extends AbstractValidator<EstadoDTO> {
  @Override
  public void rules() {
    ruleFor(EstadoDTO::getDescricao)
        .must(not(nullValue()))
        .withMessage("Estado não pode ser vazio")
        .withFieldName("estado");
  }
}
