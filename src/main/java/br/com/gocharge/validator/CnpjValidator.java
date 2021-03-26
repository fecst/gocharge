package br.com.gocharge.validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class CnpjValidator {

  private static final int[] pesoCNPJ = {6,5,4,3,2,9,8,7,6,5,4,3,2};

  private static final Set<String> valoresInvalidosCNPJ =
      new HashSet<>(
          Arrays.asList(
              "11111111111111",
              "22222222222222",
              "33333333333333",
              "44444444444444",
              "55555555555555",
              "66666666666666",
              "77777777777777",
              "88888888888888",
              "99999999999999",
              "00000000000000"));

  private CnpjValidator() {
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

  public static boolean isValid(final String cnpj) {
    if (Objects.isNull(cnpj) || cnpj.length() != 14 || valoresInvalidosCNPJ.contains(cnpj)) {
      return false;
    }

    final Integer digito1 = calculaDigito(cnpj.substring(0, 12), pesoCNPJ);
    final Integer digito2 = calculaDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);

    return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
  }
}
