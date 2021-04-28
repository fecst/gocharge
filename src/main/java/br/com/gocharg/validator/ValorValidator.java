package br.com.gocharg.validator;

import br.com.fluentvalidator.AbstractValidator;
import br.com.gocharg.dto.ValorDTO;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSize;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeLessThanOrEqual;

@Component
public class ValorValidator extends AbstractValidator<ValorDTO> {
  @Override
  public void rules() {
    ruleFor(valorDTO -> valorDTO)
        .must(not(nullValue(ValorDTO::getValorKwH)))
        .withMessage("Valor por quilowatt-hora não pode ser nulo")
        .withFieldName("valorKwH")
        .withAttempedValue(ValorDTO::getValorKwH)

        .must(not(nullValue(ValorDTO::getBandeira)))
        .withMessage("Bandeira não pode ser nulo")
        .withFieldName("bandeira")
        .withAttempedValue(ValorDTO::getBandeira)

        .must(stringSize(ValorDTO::getBandeira, 36))
        .when(not(nullValue(ValorDTO::getBandeira)))
        .withMessage("Bandeira deve ter 36 caracteres")
        .withFieldName("bandeira")
        .withAttempedValue(ValorDTO::getBandeira)

        .must(not(nullValue(ValorDTO::getPercentualAdicional)))
        .withMessage("Percentual adicional não pode ser nulo")
        .withFieldName("percentual_adicional")
        .withAttempedValue(ValorDTO::getPercentualAdicional)

        .must(not(nullValue(ValorDTO::getValorAdicional)))
        .withMessage("Valor adicional não pode ser nulo")
        .withFieldName("valor_adicional")
        .withAttempedValue(ValorDTO::getValorAdicional)

        .must(not(nullValue(ValorDTO::getDescricao)))
        .withMessage("Descrição não pode ser nulo")
        .withFieldName("descricao")
        .withAttempedValue(ValorDTO::getDescricao)

        .must(stringSizeLessThanOrEqual(ValorDTO::getDescricao, 250))
        .when(not(nullValue(ValorDTO::getDescricao)))
        .withMessage("Descrição não pode ser nulo")
        .withFieldName("descricao")
        .withAttempedValue(ValorDTO::getDescricao);
  }
}
