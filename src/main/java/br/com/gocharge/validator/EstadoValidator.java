package br.com.gocharge.validator;

import br.com.fluentvalidator.AbstractValidator;
import br.com.gocharge.domain.Estado;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

@Component
public class EstadoValidator extends AbstractValidator<Estado> {
    @Override
    public void rules() {
        ruleFor(Estado::getDescricao)
                .must(not(nullValue()))
                .withMessage("Descrição não pode ser vazia")
                .withFieldName("descricao");
    }
}
