package br.com.gocharg.validator;

import br.com.fluentvalidator.AbstractValidator;
import br.com.gocharg.dto.TotemDTO;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;

@Component
public class TotemValidator extends AbstractValidator<TotemDTO> {
  @Override
  public void rules() {
    ruleFor(totemDTO -> totemDTO)
        .must(not(nullValue(TotemDTO::getValor)))
        .withMessage("Valor não pode ser nulo")
        .withFieldName("valor")
        .withAttempedValue(TotemDTO::getZona)

        .must(not(nullValue(TotemDTO::getZona)))
        .withMessage("Zona não pode ser vazia")
        .withFieldName("zona")
        .withAttempedValue(TotemDTO::getZona)

        .must(not(nullValue(TotemDTO::getSubZona)))
        .withMessage("SubZona não pode ser vazia")
        .withFieldName("subzona")
        .withAttempedValue(TotemDTO::getZona)

        .must(not(nullValue(TotemDTO::getCidade)))
        .withMessage("Cidade não pode ser nulo")
        .withFieldName("cidade")
        .withAttempedValue(TotemDTO::getCidade)

        .must(not(nullValue(TotemDTO::getEstado)))
        .withMessage("Estado não pode ser nulo")
        .withFieldName("estado")
        .withAttempedValue(TotemDTO::getEstado)

        .must(not(nullValue(TotemDTO::getFabricante)))
        .withMessage("Fabricante não pode ser nulo")
        .withFieldName("fabricante")
        .withAttempedValue(TotemDTO::getFabricante);

    ruleFor(TotemDTO::getStatus)
            .must(stringEmptyOrNull())
            .withMessage("Status não pode ser alterado por essa rota")
            .withFieldName("status");
  }
}
