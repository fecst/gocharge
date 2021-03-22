package br.com.gocharge.validator;

import br.com.fluentvalidator.AbstractValidator;
import br.com.gocharge.domain.Cidade;
import br.com.gocharge.domain.Estado;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

@Component
public class CidadeValidator extends AbstractValidator<Cidade> {
    @Override
    public void rules() {
        ruleFor(Cidade::getDescricao)
                .must(not(nullValue()))
                .withMessage("Descrição não pode ser vazia")
                .withFieldName("descricao");

        ruleFor(Cidade::getEstado)
                .must(not(nullValue()))
                .withMessage("Estadp não pode ser nulo")
                .withFieldName("estado");
    }
}
