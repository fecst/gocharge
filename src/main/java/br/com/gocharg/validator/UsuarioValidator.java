package br.com.gocharg.validator;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.function.FunctionBuilder;
import br.com.fluentvalidator.predicate.PredicateBuilder;
import br.com.gocharg.dto.UsuarioDTO;
import br.com.gocharg.enums.CategoriaUsuarioEnum;
import br.com.gocharg.enums.StatusCadastroEnum;
import br.com.gocharg.enums.TipoUsuarioEnum;
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

        .must(stringSizeLessThanOrEqual(UsuarioDTO::getLogin, 250))
        .when(not(stringEmptyOrNull(UsuarioDTO::getLogin)))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 250 caracteres.")
        .withFieldName("login")
        .withAttempedValue(UsuarioDTO::getLogin)

        .must(not(nullValue(UsuarioDTO::getEmail)))
        .withMessage("E-mail não pode ser nulo")
        .withFieldName("email")

        .must(stringSizeLessThanOrEqual(UsuarioDTO::getEmail, 250))
        .when(not(stringEmptyOrNull(UsuarioDTO::getEmail)))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 250 caracteres.")
        .withFieldName("email")
        .withAttempedValue(UsuarioDTO::getEmail)

        .must(stringSize(UsuarioDTO::getCpf, 11))
        .when(not(stringEmptyOrNull(UsuarioDTO::getCpf)))
        .withMessage(
            "Tamanho do campo permito inválido. O campo deve ter 11 caracteres.")
        .withFieldName("cpf")
        .withAttempedValue(UsuarioDTO::getCpf)

        .must(validateCPF(FunctionBuilder.of(UsuarioDTO::getCpf)))
        .when(not(stringEmptyOrNull(UsuarioDTO::getCpf)))
        .withMessage("CPF inválido")
        .withFieldName("cpf")
        .withAttempedValue(UsuarioDTO::getCpf)

        .must(stringSizeLessThanOrEqual(UsuarioDTO::getCnpj, 14))
        .when(not(stringEmptyOrNull(UsuarioDTO::getCnpj)))
        .withMessage(
                "Tamanho do campo inválido. O campo deve ter 14 caracteres.")
        .withFieldName("cnpj")
        .withAttempedValue(UsuarioDTO::getCnpj)

        .must(validateCNPJ(FunctionBuilder.of(UsuarioDTO::getCnpj)))
        .when(not(stringEmptyOrNull(UsuarioDTO::getCnpj)))
        .withMessage("CNPJ inválido")
        .withFieldName("cnpj")
        .withAttempedValue(UsuarioDTO::getCnpj)

        .must(isDate(FunctionBuilder.of(UsuarioDTO::getDataNascimento), "uuuu-MM-dd"))
        .when(not(stringEmptyOrNull(UsuarioDTO::getDataNascimento)))
        .withMessage("O campo deve ser uma data válida com formato 'uuuu-MM-dd'")
        .withFieldName("data_nascimento")
        .withAttempedValue(UsuarioDTO::getDataNascimento)

        .must(stringSizeLessThanOrEqual(UsuarioDTO::getTelefone1, 11))
        .when(not(stringEmptyOrNull(UsuarioDTO::getTelefone1)))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 11 caracteres.")
        .withFieldName("telefone_1")
        .withAttempedValue(UsuarioDTO::getTelefone1)

        .must(stringSizeLessThanOrEqual(UsuarioDTO::getTelefone2, 11))
        .when(not(stringEmptyOrNull(UsuarioDTO::getTelefone2)))
        .withMessage(
                "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 11 caracteres.")
        .withFieldName("telefone_2")
        .withAttempedValue(UsuarioDTO::getTelefone2)

        .must(stringSizeLessThanOrEqual(UsuarioDTO::getTelefone3, 11))
        .when(not(stringEmptyOrNull(UsuarioDTO::getTelefone3)))
        .withMessage(
                "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 11 caracteres.")
        .withFieldName("telefone_3")
            .withAttempedValue(UsuarioDTO::getTelefone3)

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
        .withFieldName("numero")
        .withAttempedValue(UsuarioDTO::getNumero)

        .must(stringSizeLessThanOrEqual(UsuarioDTO::getComplemento, 250))
        .when(not(stringEmptyOrNull(UsuarioDTO::getComplemento)))
        .withMessage(
                "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 250 caracteres.")
        .withFieldName("complemento")
        .withAttempedValue(UsuarioDTO::getComplemento)

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
        .withFieldName("estado")
        .withAttempedValue(UsuarioDTO::getEstado)

        .must(stringSizeLessThanOrEqual(UsuarioDTO::getPais, 250))
        .when(not(stringEmptyOrNull(UsuarioDTO::getPais)))
        .withMessage(
                "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 250 caracteres.")
        .withFieldName("pais")
        .withAttempedValue(UsuarioDTO::getPais);

    ruleFor(UsuarioDTO::getStatus)
        .must(StatusCadastroEnum::contains)
        .when(not(stringEmptyOrNull()))
        .withMessage("Status só pode receber o valor 'A' ou 'I'")
        .withFieldName("status");

    ruleFor(UsuarioDTO::getTipoUsuario)
        .must(TipoUsuarioEnum::contains)
        .when(not(stringEmptyOrNull()))
        .withMessage("Código informado inválido")
        .withFieldName("tipo_usuario");

    ruleFor(UsuarioDTO::getCategoriaUsuario)
        .must(CategoriaUsuarioEnum::contains)
        .when(not(stringEmptyOrNull()))
        .withMessage("Código informado inválido")
        .withFieldName("categoria_usuario");
  }

  private static Predicate<UsuarioDTO> validateCPF(final Function<UsuarioDTO, String> source) {
    return PredicateBuilder.from(stringMatches(source, "[0-9]{11}"))
            .and(usuario -> CpfValidator.isValid(source.apply(usuario)));
  }

  private static Predicate<UsuarioDTO> validateCNPJ(final Function<UsuarioDTO, String> source) {
    return PredicateBuilder.from(stringMatches(source, "[0-9]{14}"))
            .and(usuario -> CnpjValidator.isValid(source.apply(usuario)));
  }
}
