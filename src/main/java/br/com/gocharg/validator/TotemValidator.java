package br.com.gocharg.validator;

import br.com.fluentvalidator.AbstractValidator;
import br.com.gocharg.dto.TotemDTO;
import br.com.gocharg.dto.UsuarioDTO;
import br.com.gocharg.enums.CategoriaEnum;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.ComparablePredicate.equalTo;
import static br.com.fluentvalidator.predicate.LogicalPredicate.isFalse;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEquals;

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
        .withAttempedValue(TotemDTO::getFabricante)

        .must(not(nullValue(TotemDTO::getApelido)))
        .withMessage("Apelido não pode ser nulo")
        .withFieldName("apelido")
        .withAttempedValue(TotemDTO::getApelido)

        .must(not(nullValue(TotemDTO::getMaps)))
        .withMessage("Maps não pode ser nulo")
        .withFieldName("maps")
        .withAttempedValue(TotemDTO::getMaps)

        .must(not(nullValue(TotemDTO::getCategoria)))
        .withMessage("Categoria não pode ser nulo")
        .withFieldName("categoria")
        .withAttempedValue(TotemDTO::getCategoria)

        .must(not(nullValue(TotemDTO::getQrCode)))
        .withMessage("QrCode não pode ser nulo")
        .withFieldName("qr_code")
        .withAttempedValue(TotemDTO::getQrCode)

        .must(not(nullValue(TotemDTO::getPropriedadeGoCharg)))
        .withMessage("Propriedade GoCharg não pode ser nulo")
        .withFieldName("propriedade_go_charg")
        .withAttempedValue(TotemDTO::getPropriedadeGoCharg)

        .must(not(stringEmptyOrNull(TotemDTO::getProprietario)))
        .when(stringEquals(TotemDTO::getPropriedadeGoCharg, "false"))
        .withMessage("Informe o proprietario")
        .withFieldName("proprietario")
        .withAttempedValue(TotemDTO::getProprietario)

        .must(stringEmptyOrNull(TotemDTO::getStatus))
        .withMessage("Status não pode ser alterado por essa rota")
        .withFieldName("status")
        .withAttempedValue(TotemDTO::getStatus);

    ruleFor(TotemDTO::getCategoria)
        .must(CategoriaEnum::contains)
        .when(not(stringEmptyOrNull()))
        .withMessage("Valor inválido para categoria. Valores aceitos 'A', 'B', 'C', 'D', 'E'")
        .withFieldName("categoria");
  }
}
