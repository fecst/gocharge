package br.com.gocharge.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum StatusUsuarioEnum {
  INATIVO("I", "Inativo"),
  ATIVO("A", "Ativo"),
  BLOQUEADO("B", "Bloqueado");

  private String codigo;
  private String descricao;

  StatusUsuarioEnum(String codigo, String descricao) {
    this.codigo = codigo;
    this.descricao = descricao;
  }

  public static StatusUsuarioEnum get(String codigo) {
    return Stream.of(StatusUsuarioEnum.values())
        .filter(value -> value.getCodigo().equals(codigo))
        .findFirst()
        .orElse(null);
  }
}
