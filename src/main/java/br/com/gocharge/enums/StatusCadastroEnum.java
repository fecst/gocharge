package br.com.gocharge.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Stream;

@Getter
public enum StatusCadastroEnum {
  INATIVO("I", "Inativo"),
  ATIVO("A", "Ativo");

  private String codigo;
  private String descricao;

  StatusCadastroEnum(String codigo, String descricao) {
    this.codigo = codigo;
    this.descricao = descricao;
  }

  public static StatusCadastroEnum get(String codigo) {
    return Stream.of(StatusCadastroEnum.values())
        .filter(value -> value.getCodigo().equals(codigo))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(StatusCadastroEnum.values())
            .anyMatch(status -> status.codigo.equals(codigo));
  }
}
