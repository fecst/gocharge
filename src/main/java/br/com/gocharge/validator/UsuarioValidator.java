package br.com.gocharge.validator;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.predicate.PredicateBuilder;
import br.com.gocharge.domain.Usuario;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.function.Predicate;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.*;

@Component
public class UsuarioValidator extends AbstractValidator<Usuario> {
  @Override
  public void rules() {
    ruleFor(Usuario::getNome)
        //
        // Validação obrigatoriedade
        .must(not(nullValue()))
        .withMessage("Nome não pode ser nulo")
        .withFieldName("nome")
        //
        // Validação tamanho do campo
        .must(stringSizeLessThanOrEqual(250))
        .when(not(stringEmptyOrNull()))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 250 caracteres.")
        .withFieldName("nome")
        .withAttempedValue(Usuario::getNome);

    ruleFor(Usuario::getApelido)
        //
        // Validação obrigatoriedade
        .must(not(nullValue()))
        .withMessage("Apelido não pode ser nulo")
        .withFieldName("apelido")
        //
        // Validação tamanho do campo
        .must(stringSizeLessThanOrEqual(250))
        .when(not(stringEmptyOrNull()))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 250 caracteres.")
        .withFieldName("apelido")
        .withAttempedValue(Usuario::getApelido);

    ruleFor(Usuario::getEmail)
        //
        // Validação obrigatoriedade
        .must(not(nullValue()))
        .withMessage("E-mail não pode ser nulo")
        .withFieldName("email")
        //
        // Validação tamanho do campo
        .must(stringSizeLessThanOrEqual(250))
        .when(not(stringEmptyOrNull()))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 250 caracteres.")
        .withFieldName("email")
        .withAttempedValue(Usuario::getEmail);

    ruleFor(Usuario::getCpf)
        //
        // Validação obrigatoriedade
        .must(not(nullValue()))
        .withMessage("CPF não pode ser nulo")
        .withFieldName("cpf")
        //
        // Validação tamanho do campo
        .must(stringSizeLessThanOrEqual(11))
        .when(not(stringEmptyOrNull()))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 11 caracteres.")
        .withFieldName("cpf")
        .withAttempedValue(Usuario::getCpf);
    //
    // Validação de CPF válido
    //    .must(validateCPF(Usuario::getCpf))
    //    .when(not(stringEmptyOrNull()))
    //    .withMessage("CPF inválido")
    //    .withFieldName("cpf")
    //    .withAttempedValue(Usuario::getCpf);

    ruleFor(Usuario::getDataNascimento)
        //
        // Validação obrigatoriedade
        .must(not(nullValue()))
        .withMessage("Data de nascimento não pode ser nula")
        .withFieldName("data_nascimento");
    //
    // Validação tipo do campo
    //            .must(isDate(Usuario::getDataNascimento, "uuuu-MM-dd"))
    //            .when(not(stringEmptyOrNull()))
    //            .withMessage(
    //                    "O campo deve ser uma data válida com formato 'uuuu-MM-dd'")
    //            .withFieldName("data_nascimento")
    //            .withAttempedValue(Usuario::getDataNascimento);

    //        ruleFor(Usuario::getSenha)
    //                .must(not(nullValue()))
    //                .when(isTrue(FunctionBuilder.of(Usuario::getIsCreate)))
    //                .withMessage("Senha não pode ser nula")
    //                .withFieldName("senha");

    ruleFor(Usuario::getTelefone)
        //
        // Validação obrigatoriedade
        .must(not(nullValue()))
        .withMessage("Telefone não pode ser nulo")
        .withFieldName("telefone")
        //
        // Validação tamanho do campo
        .must(stringSizeLessThanOrEqual(11))
        .when(not(stringEmptyOrNull()))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 11 caracteres.")
        .withFieldName("telefone")
        .withAttempedValue(Usuario::getTelefone);

    ruleFor(Usuario::getCep)
        //
        // Validação obrigatoriedade
        .must(not(nullValue()))
        .withMessage("CEP não pode ser nulo")
        .withFieldName("cep")
        //
        // Validação tamanho do campo
        .must(stringSizeLessThanOrEqual(8))
        .when(not(stringEmptyOrNull()))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 8 caracteres.")
        .withFieldName("cep")
        .withAttempedValue(Usuario::getCep);
    ;

    ruleFor(Usuario::getEndereco)
        //
        // Validação obrigatoriedade
        .must(not(nullValue()))
        .withMessage("Endereco não pode ser nulo")
        .withFieldName("endereco")
        //
        // Validação tamanho do campo
        .must(stringSizeLessThanOrEqual(250))
        .when(not(stringEmptyOrNull()))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 250 caracteres.")
        .withFieldName("endereco")
        .withAttempedValue(Usuario::getEndereco);
    ;

    ruleFor(Usuario::getNumero)
        //
        // Validação obrigatoriedade
        .must(not(nullValue()))
        .withMessage("Numero não pode ser nulo")
        .withFieldName("numero_endereco")
        //
        // Validação tamanho do campo
        .must(stringSizeLessThanOrEqual(10))
        .when(not(stringEmptyOrNull()))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 10 caracteres.")
        .withFieldName("numero_endereco")
        .withAttempedValue(Usuario::getNumero);

    ruleFor(Usuario::getBairro)
        //
        // Validação obrigatoriedade
        .must(not(nullValue()))
        .withMessage("Bairro não pode ser nulo")
        .withFieldName("bairro")
        //
        // Validação tamanho do campo
        .must(stringSizeLessThanOrEqual(250))
        .when(not(stringEmptyOrNull()))
        .withMessage(
            "Tamanho máximo do campo permito excedido. O campo deve ter no máximo 250 caracteres.")
        .withFieldName("bairro")
        .withAttempedValue(Usuario::getBairro);

    ruleFor(Usuario::getCidade)
        //
        // Validação obrigatoriedade
        .must(not(nullValue()))
        .withMessage("Cidade não pode ser nula")
        .withFieldName("cidade");

    ruleFor(Usuario::getEstado)
        //
        // Validação obrigatoriedade
        .must(not(nullValue()))
        .withMessage("Estado não pode ser nulo")
        .withFieldName("estado");

    //        ruleFor(Bandeira::getStatus)
    //                .must(hasAny(Arrays.asList("A", "I")))
    //                .when(not(stringEmptyOrNull()))
    //                .withMessage("Status só pode receber o valor 'A' ou 'I'")
    //                .withFieldName("status");
  }

  private static Predicate<Usuario> validateCPF(final Function<Usuario, String> source) {
    return PredicateBuilder.from(stringMatches(source, "[0-9]{11}"))
        .and(usuario -> CpfValidator.isValid(source.apply(usuario)));
  }
}
