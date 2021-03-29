package br.com.gocharge.validator;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.function.FunctionBuilder;
import br.com.fluentvalidator.predicate.PredicateBuilder;
import br.com.gocharge.dto.UsuarioDTO;
import br.com.gocharge.enums.StatusCadastroEnum;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.function.Predicate;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.*;

@Component
public class UsuarioValidator extends AbstractValidator<UsuarioDTO> {
  @Override
  public void rules() {
    ruleFor(usuarioDTO -> usuarioDTO)
        .must(stringSizeLessThanOrEqual(UsuarioDTO::getNome, 250))
        .when(not(stringEmptyOrNull(UsuarioDTO::getNome)))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 250 caracteres.")
        .withFieldName("nome")
        .withAttempedValue(UsuarioDTO::getNome)

        .must(stringSizeLessThanOrEqual(UsuarioDTO::getApelido, 250))
        .when(not(stringEmptyOrNull(UsuarioDTO::getApelido)))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 250 caracteres.")
        .withFieldName("apelido")
        .withAttempedValue(UsuarioDTO::getApelido)

        .must(not(nullValue(UsuarioDTO::getEmail)))
        .withMessage("E-mail não pode ser nulo")
        .withFieldName("email")

        .must(stringSizeLessThanOrEqual(UsuarioDTO::getEmail, 250))
        .when(not(stringEmptyOrNull(UsuarioDTO::getEmail)))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 250 caracteres.")
        .withFieldName("email")
        .withAttempedValue(UsuarioDTO::getEmail)

        .must(stringSizeLessThanOrEqual(UsuarioDTO::getCpf, 11))
        .when(not(stringEmptyOrNull(UsuarioDTO::getCpf)))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 11 caracteres.")
        .withFieldName("cpf")
        .withAttempedValue(UsuarioDTO::getCpf)

        .must(validateCPF(FunctionBuilder.of(UsuarioDTO::getCpf)))
        .when(not(stringEmptyOrNull(UsuarioDTO::getCpf)))
        .withMessage("CPF inválido")
        .withFieldName("cpf")
        .withAttempedValue(UsuarioDTO::getCpf)

        .must(isDate(FunctionBuilder.of(UsuarioDTO::getDataNascimento), "uuuu-MM-dd"))
        .when(not(stringEmptyOrNull(UsuarioDTO::getDataNascimento)))
        .withMessage("O campo deve ser uma data válida com formato 'uuuu-MM-dd'")
        .withFieldName("data_nascimento")
        .withAttempedValue(UsuarioDTO::getDataNascimento)

        .must(stringSizeLessThanOrEqual(UsuarioDTO::getTelefone, 11))
        .when(not(stringEmptyOrNull(UsuarioDTO::getTelefone)))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 11 caracteres.")
        .withFieldName("telefone")
        .withAttempedValue(UsuarioDTO::getTelefone)

        .must(stringSizeLessThanOrEqual(UsuarioDTO::getCep, 8))
        .when(not(stringEmptyOrNull(UsuarioDTO::getCep)))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 8 caracteres.")
        .withFieldName("cep")
        .withAttempedValue(UsuarioDTO::getCep)

        .must(stringSizeLessThanOrEqual(UsuarioDTO::getEndereco, 250))
        .when(not(stringEmptyOrNull(UsuarioDTO::getEndereco)))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 250 caracteres.")
        .withFieldName("endereco")
        .withAttempedValue(UsuarioDTO::getEndereco)

        .must(stringSizeLessThanOrEqual(UsuarioDTO::getNumero, 10))
        .when(not(stringEmptyOrNull(UsuarioDTO::getNumero)))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 10 caracteres.")
        .withFieldName("numero_endereco")
        .withAttempedValue(UsuarioDTO::getNumero)

        .must(stringSizeLessThanOrEqual(UsuarioDTO::getBairro, 250))
        .when(not(stringEmptyOrNull(UsuarioDTO::getBairro)))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 250 caracteres.")
        .withFieldName("bairro")
        .withAttempedValue(UsuarioDTO::getBairro)

        .must(stringSizeLessThanOrEqual(UsuarioDTO::getCidade, 36))
        .when(not(stringEmptyOrNull(UsuarioDTO::getCidade)))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 36 caracteres.")
        .withFieldName("cidade")
        .withAttempedValue(UsuarioDTO::getCidade)

        .must(stringSizeLessThanOrEqual(UsuarioDTO::getEstado, 36))
        .when(not(stringEmptyOrNull(UsuarioDTO::getEstado)))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 36 caracteres.")
        .withFieldName("cidade")
        .withAttempedValue(UsuarioDTO::getEndereco);

    ruleFor(UsuarioDTO::getStatus)
        .must(StatusCadastroEnum::contains)
        .when(not(stringEmptyOrNull()))
        .withMessage("Status só pode receber o valor 'A' ou 'I'")
        .withFieldName("status");
  }

  private static Predicate<UsuarioDTO> validateCPF(final Function<UsuarioDTO, String> source) {
    return PredicateBuilder.from(stringMatches(source, "[0-9]{11}"))
        .and(usuario -> CpfValidator.isValid(source.apply(usuario)));
  }
}
