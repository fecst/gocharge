package br.com.gocharge.validator;

import br.com.fluentvalidator.AbstractValidator;
import br.com.gocharge.dto.BandeiraDTO;
import br.com.gocharge.enums.StatusCadastroEnum;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;

@Component
public class BandeiraValidator extends AbstractValidator<BandeiraDTO> {
  @Override
  public void rules() {
    ruleFor(BandeiraDTO::getDescricao)
        .must(not(nullValue()))
        .withMessage("Descrição não pode ser vazia")
        .withFieldName("descricao");

    ruleFor(BandeiraDTO::getStatus)
        .must(StatusCadastroEnum::contains)
        .when(not(stringEmptyOrNull()))
        .withMessage("Status só pode receber o valor 'A' ou 'I'")
        .withFieldName("status");
  }
}
