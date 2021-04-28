package br.com.gocharg.validator;

import br.com.fluentvalidator.AbstractValidator;
import br.com.gocharg.dto.VeiculoDTO;
import br.com.gocharg.enums.StatusCadastroEnum;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;

@Component
public class VeiculoValidator extends AbstractValidator<VeiculoDTO> {
    @Override
    public void rules() {
        ruleFor(VeiculoDTO::getApelido)
                .must(not(nullValue()))
                .withMessage("Apelido não pode ser vazio")
                .withFieldName("apelido");

        ruleFor(VeiculoDTO::getStatus)
                .must(StatusCadastroEnum::contains)
                .when(not(stringEmptyOrNull()))
                .withMessage("Status só pode receber o valor 'A' ou 'I'")
                .withFieldName("status");
    }
}
