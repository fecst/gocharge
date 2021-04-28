package br.com.gocharg.validator;

import br.com.fluentvalidator.AbstractValidator;
import br.com.gocharg.dto.BandeiraDTO;
import br.com.gocharg.dto.FabricanteDTO;
import br.com.gocharg.enums.StatusCadastroEnum;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;

@Component
public class FabricanteValidator extends AbstractValidator<FabricanteDTO> {
  @Override
  public void rules() {
    ruleFor(FabricanteDTO::getDescricao)
        .must(not(nullValue()))
        .withMessage("Descrição não pode ser vazia")
        .withFieldName("descricao");

    ruleFor(FabricanteDTO::getStatus)
        .must(StatusCadastroEnum::contains)
        .when(not(stringEmptyOrNull()))
        .withMessage("Status só pode receber o valor 'A' ou 'I'")
        .withFieldName("status");
  }
}
