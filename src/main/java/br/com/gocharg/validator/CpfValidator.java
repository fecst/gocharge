package br.com.gocharg.validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class CpfValidator {

  private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

  private static final Set<String> valoresInvalidosCPF =
      new HashSet<>(
          Arrays.asList(
              "11111111111",
              "22222222222",
              "33333333333",
              "44444444444",
              "55555555555",
              "66666666666",
              "77777777777",
              "88888888888",
              "99999999999",
              "00000000000"));

  private CpfValidator() {
    super();
  }

  private static int calculaDigito(final String str, final int[] peso) {
    int soma = 0;
    for (int indice = str.length() - 1; indice >= 0; indice--) {
      final Integer digito = Integer.parseInt(str.substring(indice, indice + 1));
      soma += digito * peso[(peso.length - str.length()) + indice];
    }
    soma = 11 - (soma % 11);
    return soma > 9 ? 0 : soma;
  }

  public static boolean isValid(final String cpf) {
    if (Objects.isNull(cpf) || cpf.length() != 11 || valoresInvalidosCPF.contains(cpf)) {
      return false;
    }

    final Integer digito1 = calculaDigito(cpf.substring(0, 9), pesoCPF);
    final Integer digito2 = calculaDigito(cpf.substring(0, 9) + digito1, pesoCPF);

    return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
  }
}
