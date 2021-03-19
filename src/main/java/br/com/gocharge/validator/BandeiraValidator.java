package br.com.gocharge.validator;

import br.com.fluentvalidator.AbstractValidator;
import br.com.gocharge.domain.Bandeira;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static br.com.fluentvalidator.predicate.CollectionPredicate.hasAny;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEquals;

@Component
public class BandeiraValidator extends AbstractValidator<Bandeira> {
    @Override
    public void rules() {
        ruleFor(Bandeira::getDescricao)
                .must(not(nullValue()))
                .withMessage("Descrição não pode ser vazia")
                .withFieldName("descricao");

//        ruleFor(Bandeira::getStatus)
//                .must(hasAny(Arrays.asList("A", "I")))
//                .when(not(stringEmptyOrNull()))
//                .withMessage("Status só pode receber o valor 'A' ou 'I'")
//                .withFieldName("status");
    }
}
